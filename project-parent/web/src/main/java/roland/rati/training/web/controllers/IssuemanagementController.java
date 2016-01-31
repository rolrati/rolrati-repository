package roland.rati.training.web.controllers;

import java.io.Serializable;
import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.event.ToggleEvent;
import org.primefaces.model.Visibility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import roland.rati.training.service.MessageService;
import roland.rati.training.service.RoleService;
import roland.rati.training.service.UserService;
import roland.rati.training.service.vo.MessageVo;
import roland.rati.training.service.vo.RoleVo;
import roland.rati.training.service.vo.UserVo;

@Component
@SessionScoped
@ManagedBean(name = "issuemanagementController")
public class IssuemanagementController implements Serializable {

	private static final long serialVersionUID = 1L;

	private String text;
	private UserVo toUser;
	private List<UserVo> users;

	private int newMessageNumber;

	private List<MessageVo> recieved;
	private List<MessageVo> sended;

	private MessageVo selectedMessage;

	@Autowired
	UserService userService;

	@Autowired
	RoleService roleService;

	@Autowired
	MessageService messageService;

	public void info(Severity severity, String summary, String details) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(severity, summary, details));
	}

	public void setMessageStatus() {
		try {
			MessageVo vo = messageService.findMessageById(selectedMessage
					.getId());
			messageService.updateMessageStatus(vo.getId(),
					selectedMessage.isViewed());
			
			if (selectedMessage.isViewed() == true) {
				newMessageNumber--;
			}else{
				newMessageNumber++;
			}

			for (MessageVo messageVo : recieved) {
				if (messageVo.getId() == selectedMessage.getId()) {
					messageVo.setViewed(selectedMessage.isViewed());
				}
			}
			selectedMessage = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void initOrderList(ToggleEvent event) {
		setUsers(new LinkedList<UserVo>());

		if (event.getVisibility() == Visibility.HIDDEN) {
			toUser = null;
		} else {
			try {
				UserVo authenticatedUser = getAuthenticatedUser();
				RoleVo authenticatedUserRole = authenticatedUser.getRoles()
						.get(0);

				if (authenticatedUserRole.getName().equals("ROLE_USER")) {
					users = userService.findUsersByRole(roleService
							.findRoleByName("ROLE_ADMIN").getId());
				} else {
					users = userService.findAllUser();
					users.remove(authenticatedUser);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void removeMessageSelect() {
		selectedMessage = null;
	}

	public void deleteMessage() {
		if (selectedMessage != null) {
			try {
				messageService.deleteMessage(selectedMessage);
				if (sended.contains(selectedMessage)) {
					sended.remove(selectedMessage);
				} else if (recieved.contains(selectedMessage)) {
					recieved.remove(selectedMessage);
					if (selectedMessage.isViewed() == false) {
						newMessageNumber--;
					}
				}

				info(FacesMessage.SEVERITY_INFO, "Információ", "Üzenet törölve");
				selectedMessage = null;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void refreshMessageBox() {
		recieved = new LinkedList<MessageVo>();
		sended = new LinkedList<MessageVo>();

		List<MessageVo> vos = new LinkedList<MessageVo>();

		newMessageNumber = 0;
		selectedMessage = null;

		try {
			UserVo authenticatedUser = getAuthenticatedUser();
			if (authenticatedUser != null) {
				vos = messageService.findMessageByUser(authenticatedUser
						.getId());

				for (MessageVo messageVo : vos) {
					if (messageVo.getSender().getId() == authenticatedUser
							.getId()) {
						sended.add(messageVo);
					} else if (messageVo.getRecipient().getId() == authenticatedUser
							.getId()) {
						recieved.add(messageVo);
						if (messageVo.isViewed() == false) {
							newMessageNumber++;
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void infoNewMessage() {
		if (newMessageNumber != 0) {
			info(FacesMessage.SEVERITY_INFO, "Információ", newMessageNumber
					+ " olvasatlan üzenet");
		}
	}

	public void getTextValue() {
		if (text.length() >= 3) {
			try {
				Date date = new Date();
				DateFormat dateFormat = new SimpleDateFormat(
						"yyyy/mm/dd HH:MM:ss");
				System.out.println(dateFormat.format(date));
				messageService.addMessage(text, getAuthenticatedUser(), toUser,
						dateFormat.format(date));

				info(FacesMessage.SEVERITY_INFO, "Információ",
						"Üzenet elküldve");

				refreshMessageBox();
				text = null;
				toUser = null;
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			info(FacesMessage.SEVERITY_ERROR, "Hiba", "Üzenet hossza túl rövid");
		}
	}

	public UserVo getAuthenticatedUser() {
		UserVo user = null;

		HttpServletRequest request = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
		Principal principal = request.getUserPrincipal();

		if (principal != null) {
			user = new UserVo();
			try {
				user = userService.findUserByName(principal.getName());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return user;
	}

	public IssuemanagementController() {
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<UserVo> getUsers() {
		return users;
	}

	public void setUsers(List<UserVo> users) {
		this.users = users;
	}

	public UserVo getToUser() {
		return toUser;
	}

	public void setToUser(UserVo toUser) {
		this.toUser = toUser;
	}

	public List<MessageVo> getRecieved() {
		return recieved;
	}

	public void setRecieved(List<MessageVo> recieved) {
		this.recieved = recieved;
	}

	public List<MessageVo> getSended() {
		return sended;
	}

	public void setSended(List<MessageVo> sended) {
		this.sended = sended;
	}

	public MessageVo getSelectedMessage() {
		return selectedMessage;
	}

	public void setSelectedMessage(MessageVo selectedMessage) {
		this.selectedMessage = selectedMessage;
	}

	public int getNewMessageNumber() {
		return newMessageNumber;
	}

	public void setNewMessageNumber(int newMessageNumber) {
		this.newMessageNumber = newMessageNumber;
	}

}
