package roland.rati.training.core.dao.impl;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;

import roland.rati.training.core.dao.RoleDao;
import roland.rati.training.core.dao.UserDao;
import roland.rati.training.core.dao.UserDaoCustom;
import roland.rati.training.core.entity.Role;
import roland.rati.training.core.entity.User;

public class UserDaoImpl implements UserDaoCustom  {
	
	@Autowired
	RoleDao roleDao;
	
	@Autowired
	UserDao userDao;
	
	@PersistenceContext
	EntityManager entityManager;

	@Override
	public void removeRoleFromUser(Long userId, Long roleId) throws Exception {
		User user = userDao.findUserById(userId);
		user.getRoles().remove(roleDao.findRoleById(roleId));
		userDao.save(user);
		
	}

	@Override
	public void removeAllRoleFromUser(Long userId) throws Exception {
		User user = userDao.findUserById(userId);
		user.getRoles().clear();
	}

	@Override
	public void addRoleToUser(Long userId, Long roleId) throws Exception {
		User user;
		Role role;
		if((user = userDao.findUserById(userId)) != null && (role = roleDao.findRoleById(roleId)) != null){
			user.setRole(role);
			userDao.save(user);
		}
	}

	@Override
	public List<User> findUsersByRole(Long roleId) throws Exception {
		List<User> users = new LinkedList<User>();
		List<User> rv = null;

		Role role = roleDao.findRoleById(roleId);
		users = userDao.findAll();

		if (role != null) {
			rv = new LinkedList<User>();
			for (User user : users) {
				List<Role> roles = new LinkedList<Role>();
				roles = user.getRoles();

				if (roles.contains(role)) {
					rv.add(user);
				}
			}
		}
		return rv;
	}
}
