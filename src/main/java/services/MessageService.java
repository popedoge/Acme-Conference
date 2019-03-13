
package services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.MessageRepository;
import domain.Actor;
import domain.Message;
import domain.MessageBox;

@Service
@Transactional
public class MessageService {

	@Autowired
	private MessageRepository	messageRepository;

	@Autowired
	MessageBoxService			messageBoxService;

	@Autowired
	ActorService				actorService;

	@Autowired
	SiteConfigurationService	siteService;


	public List<Message> findByMessageBox(final MessageBox messageBox) {
		return this.messageRepository.findByMessageBox(messageBox.getId());
	}

	public Message findById(final int id) {
		return this.messageRepository.findOne(id);
	}

	public Message create() {
		final Actor actor = this.actorService.findPrincipal();
		final MessageBox mb = this.messageBoxService.findByCategory("OUTBOX");
		final List<MessageBox> container = new ArrayList<>();
		final List<Actor> recipients = new ArrayList<>();
		container.add(mb);

		final Message m = new Message();
		m.setRecipients(recipients);
		m.setContainer(container);
		m.setSender(actor);

		return m;
	}

	public Message send(final Message message, final String alias) {
		//check for author is the one sending the message
		//access constraint
		final Actor actor = this.actorService.findPrincipal();
		Assert.isTrue(message.getSender().equals(actor), "Error on send: Owner inconsistency");
		message.setSenderAlias(alias);
		message.setDeliveryDate(new Date());
		final String destinedCategory = "INBOX";
		//for each recipient, update the message containers to include their INBOX
		for (final Actor recipient : message.getRecipients()) {
			final MessageBox inbox = this.messageBoxService.findByCategory(recipient, destinedCategory);
			List<MessageBox> newRelation = message.getContainer();
			if (newRelation == null)
				newRelation = new ArrayList<MessageBox>();
			newRelation.add(inbox);
			message.setContainer(newRelation);
		}
		return this.save(message);
	}

	public Message send(final Message message) {
		//check for author is the one sending the message
		//access constraint
		final Actor actor = this.actorService.findPrincipal();
		Assert.isTrue(message.getSender().equals(actor), "Error on send: Owner inconsistency");
		if (message.getSenderAlias() == null)
			message.setSenderAlias(actor.getName() + " " + actor.getSurname());

		message.setDeliveryDate(new Date());
		final String destinedCategory = "INBOX";
		//for each recipient, update the message containers to include their INBOX
		for (final Actor recipient : message.getRecipients()) {
			final MessageBox inbox = this.messageBoxService.findByCategory(recipient, destinedCategory);
			List<MessageBox> newRelation = message.getContainer();
			if (newRelation == null)
				newRelation = new ArrayList<MessageBox>();
			newRelation.add(inbox);
			message.setContainer(newRelation);
		}
		return this.save(message);
	}

	public Message remove(final Message message, final MessageBox messageBox) {
		//check user owns messageBox
		//access constraint
		final Actor actor = this.actorService.findPrincipal();
		Assert.isTrue(messageBox.getOwner().equals(actor), "Error on remove: Owner inconsistency");

		final List<MessageBox> newRelation = message.getContainer();
		newRelation.remove(messageBox);
		if (messageBox.getCategory() == "TRASHBOX")
			message.setContainer(newRelation);
		else {
			final MessageBox trashBox = this.messageBoxService.findByCategory(actor, "TRASHBOX");
			newRelation.add(trashBox);
			message.setContainer(newRelation);
		}

		return this.save(message);
	}

	Message save(final Message message) {
		return this.messageRepository.save(message);
	}

	public Message move(final Message message, final MessageBox from, final MessageBox to) {
		//check if user owns both messageBoxes
		//access constraint
		final Actor actor = this.actorService.findPrincipal();
		Assert.isTrue(from.getOwner().equals(actor), "Error on move: Owner inconsistency");
		Assert.isTrue(to.getOwner().equals(actor), "Error on move: Owner inconsistency");

		final List<MessageBox> newRelation = message.getContainer();
		newRelation.remove(from);
		newRelation.add(to);
		message.setContainer(newRelation);

		return this.save(message);
	}
}
