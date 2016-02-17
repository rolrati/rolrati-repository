package roland.rati.training.service.vo;

import java.io.Serializable;

public class PersonVo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private String firstname;
	private String lastname;
	private String sex;

	private String email;
	
	private UserVo user;
	
	private AddressVo address;
	
	public PersonVo() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UserVo getUser() {
		return user;
	}

	public void setUser(UserVo user) {
		this.user = user;
	}

	public AddressVo getAddress() {
		return address;
	}

	public void setAddress(AddressVo address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "PersonVo [id=" + id + ", firstname=" + firstname
				+ ", lastname=" + lastname + ", sex=" + sex + ", email="
				+ email + ", user=" + user + ", address=" + address + "]";
	}
	
}
