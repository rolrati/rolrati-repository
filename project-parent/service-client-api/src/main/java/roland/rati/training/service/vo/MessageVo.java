package roland.rati.training.service.vo;

import java.io.Serializable;

public class MessageVo implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private UserVo sender;
	private UserVo recipient;
	private String message;
	private String date;
	private boolean viewed;

	public MessageVo() {
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
				+ recipient + ", message=" + message + "], viewed=" + viewed;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MessageVo other = (MessageVo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public boolean isViewed() {
		return viewed;
	}

	public void setViewed(boolean viewed) {
		this.viewed = viewed;
	}
}
