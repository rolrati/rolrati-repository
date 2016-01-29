package roland.rati.training.core.entity;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="message")
public class Message extends Base {
	
	private static final long serialVersionUID = 1L;
	
	@OneToOne
	private User sender;
	
	@OneToOne
	private User recipient;
	
	@Lob
	@Basic(fetch=FetchType.LAZY)
	private String message;
	
	
	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public User getRecipient() {
		return recipient;
	}

	public void setRecipient(User recipient) {
		this.recipient = recipient;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
