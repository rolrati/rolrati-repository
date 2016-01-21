package roland.rati.training.web.controllers;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import roland.rati.training.service.RoleService;
import roland.rati.training.service.UserService;
import roland.rati.training.service.vo.UserVo;

@Component
@RequestScoped
@ManagedBean(name = "registrationController")
public class RegistrationController implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger
			.getLogger(RegistrationController.class.getName());

	private String password;
	private String confPassword;
	private String username;
	
	private boolean registrationSuccess;

	@Autowired
	UserService userService;

	@Autowired
	RoleService roleService;

	public RegistrationController() {
	}

	
	public void doRegistration() {
		FacesContext context = FacesContext.getCurrentInstance();

		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

		if (username != null && password != null && confPassword != null) {
			try {
				if (userService.findUserByName(username) != null) {
					context.addMessage(null, new FacesMessage(
							FacesMessage.SEVERITY_ERROR, "Error",
							"This user already exist!"));
				} else {
					UserVo newUser = new UserVo();

					if (!password.equals(confPassword)) {
						context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Passwords doesn't match!"));
					} else {
						newUser.setUsername(username);
						newUser.setPassword(bCryptPasswordEncoder
								.encode(password));

						userService.addUser(newUser);

						userService
								.addRoleToUser(
										userService.findUserByName(username)
												.getId(), roleService
												.findRoleByName("ROLE_USER")
												.getId());
						
						context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info!", "Successful registration."));

						username = "";
						password = "";
						confPassword = "";
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage(), e);
			}
		}
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfPassword() {
		return confPassword;
	}

	public void setConfPassword(String confPassword) {
		this.confPassword = confPassword;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public boolean isRegistrationSuccess() {
		return registrationSuccess;
	}

	public void setRegistrationSuccess(boolean registrationSuccess) {
		this.registrationSuccess = registrationSuccess;
	}
}
