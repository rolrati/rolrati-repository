package roland.rati.training.web.controllers;

import java.io.Serializable;
import java.security.Principal;
import java.util.LinkedList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import roland.rati.training.service.RoleService;
import roland.rati.training.service.UserService;
import roland.rati.training.service.vo.RoleVo;
import roland.rati.training.service.vo.UserVo;

@Component
@ViewScoped
@ManagedBean(name = "issuemanagementController")
public class IssuemanagementController implements Serializable {

	private static final long serialVersionUID = 1L;

	private String text;
	private UserVo toUser;
	private List<UserVo> users;

	@Autowired
	UserService userService;

	@Autowired
	RoleService roleService;

	public void initOrderList() {
		setUsers(new LinkedList<UserVo>());

		try {
			UserVo authenticatedUser = getAuthenticatedUser();
			RoleVo authenticatedUserRole = authenticatedUser.getRoles().get(0);

			if (authenticatedUserRole.getName().equals("ROLE_USER")) {
				users = userService.findUsersByRole(roleService.findRoleByName(
						"ROLE_ADMIN").getId());
			} else {
				users = userService.findAllUser();
				users.remove(authenticatedUser);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getTextValue() {
		System.out.println("--------> TEXT" + text);
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

	public void asd() {
		System.out.println(text);
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
}
