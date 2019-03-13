
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.MessageBoxRepository;
import domain.Actor;
import domain.Message;
import domain.MessageBox;

@Service
@Transactional
public class MessageBoxService {

	@Autowired
	private MessageBoxRepository	messageBoxRepository;

	@Autowired
	private ActorService			actorService;

	@Autowired
	private MessageService			messageService;


	public MessageBox create() {
		MessageBox messageBox = new MessageBox();
		messageBox.setOwner(this.actorService.findPrincipal());
		messageBox.setCategory("USERBOX");
		return messageBox;
	}

	public MessageBox findById(int id) {
		return this.messageBoxRepository.findOne(id);
	}

	public MessageBox save(MessageBox messageBox) {
		Actor actor = this.actorService.findPrincipal();
		messageBox.setOwner(actor);
		Assert.isTrue(messageBox.getOwner().equals(actor), "Error on save: Owner inconsistency");
		Assert.isTrue(messageBox.getCategory().equals("USERBOX"), "Error on save: Cannot modify system box");

		return this.messageBoxRepository.save(messageBox);
	}

	public List<MessageBox> createDefaultBoxes(Actor actor) {
		List<MessageBox> boxes = new ArrayList<>();
		boxes.add(this.systemCreate("Inbox", actor));
		boxes.add(this.systemCreate("Outbox", actor));
		boxes.add(this.systemCreate("Spambox", actor));
		boxes.add(this.systemCreate("Trashbox", actor));

		return this.messageBoxRepository.save(boxes);
	}

	private MessageBox systemCreate(String name, Actor owner) {
		MessageBox res = new MessageBox();
		res.setCategory(name.toUpperCase());
		res.setName(name);
		res.setOwner(owner);
		return res;
	}

	//finds messagebox by parent
	public List<MessageBox> findByParent(MessageBox parent) {
		List<MessageBox> boxList = new ArrayList<>();

		boxList = this.messageBoxRepository.findByParent(parent.getId());

		return boxList;
	}

	//loads boxes in root folder
	public List<MessageBox> loadRoot() {
		MessageBox parent = this.findByCategory("ROOT");
		List<MessageBox> boxList = new ArrayList<>();
		boxList = this.findByParent(parent);
		return boxList;
	}

	//gives a list of children given a messageBox
	private List<MessageBox> findParentChain(MessageBox parent) {
		List<MessageBox> chain = new ArrayList<>();
		List<MessageBox> onHold = new ArrayList<>();
		onHold.add(parent);
		while (!onHold.isEmpty()) {
			chain.addAll(onHold);
			List<MessageBox> children = new ArrayList<>();
			for (int i = onHold.size() - 1; i >= 0; i--) {
				children.addAll(this.messageBoxRepository.findByParent(onHold.get(i).getId()));
			}
			onHold = new ArrayList<>();
			onHold.addAll(children);
		}
		return chain;
	}

	public void deleteAll(MessageBox messageBox) {
		Actor actor = this.actorService.findPrincipal();

		Assert.isTrue(messageBox.getCategory().equals("USERBOX"), "Error on delete: System boxes cannot be deleted");
		Assert.isTrue(messageBox.getOwner().equals(actor), "Error on delete: Owner inconsistency");

		//for each messageBox in the chain (messageBox + all of its children) delete all message and the messagebox
		List<MessageBox> chain = this.findParentChain(messageBox);
		for (int i = chain.size() - 1; i >= 0; i--) {
			Assert.isTrue(chain.get(i).getOwner().equals(actor), "Error on delete: Owner inconsistency");
			Collection<Message> messages = this.messageService.findByMessageBox(chain.get(i));
			for (Message message : messages) {
				this.messageService.save(this.messageService.remove(message, chain.get(i)));
			}
			this.delete(chain.get(i));
		}
	}

	//can only be accessed from deleteall method
	private void delete(MessageBox messageBox) {

		this.messageBoxRepository.delete(messageBox.getId());
	}

	public List<MessageBox> findAllByActor(Actor actor) {
		return this.messageBoxRepository.findByActor(actor.getId());
	}

	public List<MessageBox> findByPrincipal() {
		Actor actor = this.actorService.findPrincipal();
		List<MessageBox> res = this.messageBoxRepository.findByActor(actor.getId());
		return res;
	}

	public MessageBox findByCategory(String category) {
		return this.messageBoxRepository.findByCategory(this.actorService.findPrincipal().getId(), category).get(0);
	}

	public MessageBox findByCategory(Actor actor, String category) {
		return this.messageBoxRepository.findByCategory(actor.getId(), category).get(0);
	}

	public MessageBox move(MessageBox mb, MessageBox to) {
		MessageBox res = null;
		Actor actor = this.actorService.findPrincipal();

		Assert.isTrue(mb.getOwner().equals(actor), "Error on move: Owner inconsistency");

		if (to == null) {
			mb.setParent(null);
			this.messageBoxRepository.save(mb);
		} else if (!this.findParentChain(mb).contains(to)) {
			Assert.isTrue(to.getOwner().equals(actor), "Error on move: Owner inconsistency");
			mb.setParent(to);
			this.messageBoxRepository.save(mb);
		}

		return res;
	}
}
