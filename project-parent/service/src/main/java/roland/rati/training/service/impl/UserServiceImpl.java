package roland.rati.training.service.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;

import roland.rati.training.converter.UserConverter;
import roland.rati.training.core.dao.UserDao;
import roland.rati.training.service.UserService;
import roland.rati.training.service.vo.UserVO;

public class UserServiceImpl implements UserService, Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	UserDao userDAO;

	public UserServiceImpl() {
	}

	@Override
	public UserVO findUserByName(String name) throws Exception {
		return UserConverter.toVO(userDAO.findUserByUsername(name));
	}

	@Override
	public void registrateUser(UserVO user) throws Exception {
		userDAO.save(UserConverter.toEntity(user));

	}

	@Override
	public int countUsers() throws Exception {
		return (int) userDAO.count();
	}

	@Override
	public void modifyUser(String username, UserVO user) throws Exception {
//		UserVO modUser = UserConverter.toVO(userDAO.findUserByName(username));
//
//		modUser.setPassword(user.getPassword());
//		modUser.setUsername(user.getUsername());
//		
//		userDAO.save(UserConverter.toEntity(modUser));
	}

}
