package controllers;

import javax.faces.bean.ManagedBean;

@ManagedBean(name = "loginController")
public class Login {
	private String username;
	private String password;
	
	public void loginButtonAction(){
		System.out.println(username + "-" + password);
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}