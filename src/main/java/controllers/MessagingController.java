
package controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.MessageBoxService;
import services.MessageService;
import domain.Actor;
import domain.Message;
import domain.MessageBox;
import forms.MessageForm;

@Controller
@RequestMapping(value = "/messaging")
public class MessagingController {

	@Autowired
	private MessageBoxService	messageBoxService;
	@Autowired
	private MessageService		messageService;
	@Autowired
	private ActorService		actorService;


	@RequestMapping(value = "/message", method = RequestMethod.GET)
	public ModelAndView message() {
		ModelAndView res;
		MessageForm mail = new MessageForm();
		mail.setLock(false);
		res = this.createMessageEditModelAndView(mail);
		return res;
	}

	@RequestMapping(value = "/send", method = RequestMethod.POST)
	public ModelAndView send(MessageForm mail, BindingResult binding) {
		ModelAndView res;

		if (binding.hasErrors()) {
			res = this.createMessageEditModelAndView(mail);

		} else {
			try {
				Message message = this.messageService.create();
				String[] ids = mail.getRecipients().split(",");
				for (int i = 0; i < ids.length; i++) {
					List<Actor> recipients = message.getRecipients();
					Actor newActor = this.actorService.findById(Integer.valueOf(ids[i].trim()));
					recipients.add(newActor);
					message.setRecipients(recipients);
				}
				message.setSubject(mail.getSubject());
				message.setBody(mail.getBody());
				message.setPriority(mail.getPriority());
				this.messageService.send(message);
				res = new ModelAndView("redirect:view.do");
			} catch (Exception e) {
				res = this.createMessageEditModelAndView(mail, "message.commit.error");
			}
		}
		return res;
	}

	@RequestMapping(value = "/fetch", produces = "application/json")
	public @ResponseBody
	Integer findActor(@RequestParam String username) {
		Integer res = 0;
		if (!username.isEmpty()) {
			Actor actor = this.actorService.findByUsername(username);
			if (actor != null) {
				res = actor.getId();
			}
		}
		return res;
	}

	@RequestMapping(value = "/remove", method = RequestMethod.GET)
	public ModelAndView removeMessage(@RequestParam int messageId, @RequestParam int boxId) {
		ModelAndView res;
		res = new ModelAndView("redirect:view.do?" + boxId);
		try {
			Message message = this.messageService.findById(messageId);
			MessageBox messageBox = this.messageBoxService.findById(boxId);

			this.messageService.remove(message, messageBox);
		} catch (Exception e) {
			res.addObject("message", "messageBox.delete.error");
		}
		return res;
	}

	@RequestMapping(value = "/reply", method = RequestMethod.GET)
	public ModelAndView reply(@RequestParam int id) {
		ModelAndView res;
		MessageForm mail = new MessageForm();
		Message message = this.messageService.findById(id);
		mail.setRecipients(String.valueOf(message.getSender().getId()));
		mail.setLock(true);
		mail.setSubject("Re: " + message.getSubject());
		mail.setBody("\n" + "\n" + "\n" + "Quote:\n" + message.getBody());
		res = this.createMessageEditModelAndView(mail);
		return res;
	}
	//MESSAGEBOX

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam(required = false) final Integer id) {
		ModelAndView res;
		MessageBox messageBox = this.messageBoxService.create();
		if (id != null) {
			MessageBox parent = this.messageBoxService.findById(id);
			messageBox.setParent(parent);
		}
		res = this.createEditModelAndView(messageBox);
		return res;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam final int id) {
		ModelAndView res;
		res = new ModelAndView("redirect:view.do");
		try {
			MessageBox messageBox = this.messageBoxService.findById(id);
			this.messageBoxService.deleteAll(messageBox);
		} catch (Exception e) {
			res.addObject("message", "messageBox.delete.error");
		}
		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int id) {
		ModelAndView res;
		MessageBox messageBox = this.messageBoxService.findById(id);
		Assert.notNull(messageBox);
		res = this.createEditModelAndView(messageBox);
		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid MessageBox messageBox, BindingResult binding) {
		ModelAndView res;
		if (binding.hasErrors()) {
			res = this.createEditModelAndView(messageBox);

		} else {
			try {
				this.messageBoxService.save(messageBox);
				res = new ModelAndView("redirect:view.do");
			} catch (Exception e) {
				res = this.createEditModelAndView(messageBox, "messagebox.commit.error");
			}
		}
		return res;
	}

	@RequestMapping(value = "/viewmessage", method = RequestMethod.GET)
	public ModelAndView viewMessage(@RequestParam final Integer id, @RequestParam final Integer box) {
		ModelAndView res;
		MessageBox container = this.messageBoxService.findById(box);
		Message message = this.messageService.findById(id);
		Assert.isTrue(message.getContainer().contains(container), "Message could be loaded: bad request");
		res = new ModelAndView("messaging/message");
		res.addObject("mail", message);
		res.addObject("box", container);
		return res;
	}

	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public ModelAndView boxView(@RequestParam(required = false) final Integer id) {
		ModelAndView res;
		List<MessageBox> boxes = new ArrayList<>();
		List<Message> messages = new ArrayList<>();
		MessageBox box;
		if (id == null) {
			box = null;
			boxes = this.messageBoxService.loadRoot();
		} else {
			box = this.messageBoxService.findById(id);
			boxes = this.messageBoxService.findByParent(box);
			messages = this.messageService.findByMessageBox(box);
		}
		res = new ModelAndView("messaging/messagebox");
		res.addObject("messageBoxes", boxes);
		res.addObject("messageBox", box);
		res.addObject("messages", messages);
		res.addObject("boxRequestURI", "/messaging/view");
		res.addObject("messageRequestURI", "/messaging/view");
		return res;
	}

	protected ModelAndView createEditModelAndView(MessageBox messageBox) {
		ModelAndView res;
		res = this.createEditModelAndView(messageBox, null);
		return res;
	}

	protected ModelAndView createEditModelAndView(MessageBox messageBox, String messageCode) {
		ModelAndView res;
		List<MessageBox> boxes = this.messageBoxService.findByPrincipal();

		res = new ModelAndView("messaging/edit");
		res.addObject("messageBoxes", boxes);
		res.addObject("messageBox", messageBox);
		res.addObject("message", messageCode);
		return res;
	}

	protected ModelAndView createMessageEditModelAndView(MessageForm mail) {
		ModelAndView res;
		res = this.createMessageEditModelAndView(mail, null);
		return res;
	}

	protected ModelAndView createMessageEditModelAndView(MessageForm mail, String messageCode) {
		ModelAndView res;
		List<String> priorities = new ArrayList<>();
		priorities.add("HIGH");
		priorities.add("NEUTRAL");
		priorities.add("LOW");

		res = new ModelAndView("messaging/write");
		res.addObject("mail", mail);
		res.addObject("priorities", priorities);
		res.addObject("message", messageCode);
		return res;
	}
}
