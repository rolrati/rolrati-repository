package roland.rati.training.service.vo;

import java.io.Serializable;

public class MessageVo implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private UserVo sender;
	private UserVo recipient;
	private String message;
	
	public MessageVo(){
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserVo getSender() {
		return sender;
	}

	public void setSender(UserVo sender) {
		this.sender = sender;
	}

	public UserVo getRecipient() {
		return recipient;
	}

	public void setRecipient(UserVo recipient) {
		this.recipient = recipient;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "MessageVo [id=" + id + ", sender=" + sender + ", recipient="
				+ recipient + ", message=" + message + "]";
	}
	
}
