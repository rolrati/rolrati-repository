package roland.rati.training.core;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import roland.rati.training.core.dao.RoleDao;
import roland.rati.training.core.dao.UserDao;
import roland.rati.training.core.entity.Role;
import roland.rati.training.core.entity.User;

@Component
@Transactional
public class CreateShema {
	private static final Logger logger = Logger.getLogger(CreateShema.class);

	@Autowired
	public UserDao userDAO;

	@Autowired
	public RoleDao roleDao;

	public void insertDefaultRoles() {
		Role dto = null;
		try {
			if (roleDao.findRoleByName("ROLE_ADMIN") == null) {
				dto = new Role();
				dto.setName("ROLE_ADMIN");
				roleDao.save(dto);
			}
			if (roleDao.findRoleByName("ROLE_USER") == null) {
				dto = new Role();
				dto.setName("ROLE_USER");
				roleDao.save(dto);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	public void insertAdmin() {
		User dto = null;

		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		String encPassword = bCryptPasswordEncoder.encode("admin");

		try {
			if (userDAO.findUserByUsername("admin") == null) {
				dto = new User();
				dto.setUsername("admin");
				dto.setPassword(encPassword);
				userDAO.save(dto);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	public void insertUsers(String username) {
		User dto = null;

		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		String encPassword = bCryptPasswordEncoder.encode("welcome");

		try {
			dto = new User();
			dto.setUsername(username);
			dto.setPassword(encPassword);
			userDAO.save(dto);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	public void addRoleToAdmin() {
		try {
			userDAO.addRoleToUser(userDAO.findUserByUsername("admin").getId(),
					roleDao.findRoleByName("ROLE_ADMIN").getId());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	public void addRoleToUser(String username) {
		try {
			userDAO.addRoleToUser(userDAO.findUserByUsername(username).getId(),
					roleDao.findRoleByName("ROLE_USER").getId());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
}
