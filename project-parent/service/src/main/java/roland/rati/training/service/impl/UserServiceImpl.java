package roland.rati.training.service.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import roland.rati.training.core.dao.UserDao;
import roland.rati.training.service.UserService;
import roland.rati.training.service.converter.UserConverter;
import roland.rati.training.service.vo.UserVo;

@Service("userService")
@Transactional(propagation=Propagation.REQUIRED)
public class UserServiceImpl implements UserService, Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	UserDao userDAO;

	public UserServiceImpl() {
	}

	@Override
	public UserVo findUserByName(String name) throws Exception {
		return UserConverter.toVO(userDAO.findUserByUsername(name));
	}

	@Override
	public void addUser(UserVo user) throws Exception {
		userDAO.save(UserConverter.toEntity(user));

	}

	@Override
	public int countUsers() throws Exception {
		return (int) userDAO.count();
	}

	@Override
	public void modifyUser(String username, UserVo user) throws Exception {
//		UserVO modUser = UserConverter.toVO(userDAO.findUserByName(username));
//
//		modUser.setPassword(user.getPassword());
//		modUser.setUsername(user.getUsername());
//		
//		userDAO.save(UserConverter.toEntity(modUser));
	}

	@Override
	public UserVo findUserById(Long id) throws Exception {
		 return UserConverter.toVO(userDAO.findUserById(id));
	}

	@Override
	public void deleteUser(UserVo user) throws Exception {
		userDAO.delete(UserConverter.toEntity(user));
	}

	@Override
	public void addRoleToUser(Long userId, Long roleId) throws Exception {
		userDAO.addRoleToUser(userId, roleId);
	}

	@Override
	public void removeRoleFromUser(Long userId, Long roleId) throws Exception {
		userDAO.removeRoleFromUser(userId, roleId);
	}

	@Override
	public void removeAllRoleFromUser(Long userId) throws Exception {
		userDAO.removeAllRoleFromUser(userId);
	}

}
