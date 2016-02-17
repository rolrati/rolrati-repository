package roland.rati.training.web.controllers;

import java.io.Serializable;
import java.security.Principal;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import roland.rati.training.service.AddressService;
import roland.rati.training.service.PersonService;
import roland.rati.training.service.UserService;
import roland.rati.training.service.vo.AddressVo;
import roland.rati.training.service.vo.PersonVo;
import roland.rati.training.service.vo.UserVo;

@Component
@RequestScoped
@ManagedBean(name = "usermanagementController", eager = true)
public class UsermanagementController implements Serializable {

	private static final long serialVersionUID = 1L;

	private UserVo authenticatedUser;

	private String oldPassword;
	private String newPassword;
	private String newPasswordConfirmation;

	private PersonVo authenticatedUserPersonProperty;

	private List<String> sex;

	@Autowired
	AddressService addressService;

	@Autowired
	PersonService personService;

	@PostConstruct
	public void init() {
		sex = new LinkedList<String>();
		sex.add("Nő");
		sex.add("Férfi");
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getNewPasswordConfirmation() {
		return newPasswordConfirmation;
	}

	public void setNewPasswordConfirmation(String newPasswordConfirmation) {
		this.newPasswordConfirmation = newPasswordConfirmation;
	}

	public UsermanagementController() {
	}

	@Autowired
	UserService userService;

	public void saveUserDatas() {
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++");

		try {
			AddressVo address = new AddressVo();
			address.setZipcode(4546);
			AddressVo rv;
			rv = addressService.saveAndFlushAddress(address);
			System.out.println("------>" + rv);
			authenticatedUserPersonProperty = personService
					.addUserAndAddressToPerson(authenticatedUser, rv, "Ráti",
							"Roland", null, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void info(Severity severity, String summary, String details) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(severity, summary, details));
	}

	public void loadAuthenticatedUserDatas() {
		authenticatedUser = getAuthenticatedUserObject();
		authenticatedUserPersonProperty = new PersonVo();

		if (authenticatedUser != null) {
			try {
				authenticatedUserPersonProperty = personService
						.findPersonByUserId(authenticatedUser);

				System.out.println(authenticatedUserPersonProperty
						+ "-------------------------");
				if (authenticatedUserPersonProperty != null) {
					// authenticatedUserAddressProperty =
					// addressService.findAddressById(authenticatedUserPersonProperty.getId());
					// System.out.println("++++++++"+au);
				}
				// authenticatedUserAddressProperty = addressService
				// .findAddressById(authenticatedUserPersonProperty
				// .getAddress().getId());

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void changePassword() {
		if (authenticatedUser != null) {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			if (encoder.matches(oldPassword, authenticatedUser.getPassword())) {
				if (newPassword.equals(newPasswordConfirmation)) {
					try {
						userService.changePassword(authenticatedUser.getId(),
								encoder.encode(newPassword));
						info(FacesMessage.SEVERITY_INFO, "",
								"Sikeres jelszócsere");
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					info(FacesMessage.SEVERITY_ERROR, "",
							"Az újonnan adott jelszavak nem egyeznek meg");
				}
			} else {
				info(FacesMessage.SEVERITY_ERROR, "", "Hibás régi jelszó");
			}
		}
	}

	public UserVo getAuthenticatedUserObject() {
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

	public void setAuthenticatedUser(UserVo authenticatedUser) {
		this.authenticatedUser = authenticatedUser;
	}

	public UserVo getAuthenticatedUser() {
		return authenticatedUser;
	}

	public List<String> getSex() {
		return sex;
	}

	public void setSex(List<String> sex) {
		this.sex = sex;
	}

	public PersonVo getAuthenticatedUserPersonProperty() {
		return authenticatedUserPersonProperty;
	}

	public void setAuthenticatedUserPersonProperty(
			PersonVo authenticatedUserPersonProperty) {
		this.authenticatedUserPersonProperty = authenticatedUserPersonProperty;
	}
}
