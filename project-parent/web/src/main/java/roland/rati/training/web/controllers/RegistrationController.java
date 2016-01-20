package roland.rati.training.web.controllers;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import roland.rati.training.service.UserService;
import roland.rati.training.service.vo.UserVo;

@Component
@ViewScoped
@ManagedBean(name = "registrationController")
public class RegistrationController implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger
			.getLogger(RegistrationController.class.getName());

	private String password;
	private String confPassword;
	private String username;

	@Autowired
	UserService userService;
	
	public RegistrationController() {
	}

	public void doRegistration() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

//		System.out.println(username + "-" + password);

		if (username != null && password != null && confPassword != null) {
			try {
				if (userService.findUserByName(username) != null) {
					System.out.println("már létezik ilyen felhasználó");
				} else {
					UserVo newUser = new UserVo();

					if (!password.equals(confPassword)) {
						System.out.println("A ket jelszo nem egyezik meg");
					} else {
						newUser.setUsername(username);
						newUser.setPassword(bCryptPasswordEncoder
								.encode(password));
						System.out.println("EDDIG MINDEN ZSIR");
						userService.addUser(newUser);

						username = null;
						password = null;
						confPassword = null;
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

}
