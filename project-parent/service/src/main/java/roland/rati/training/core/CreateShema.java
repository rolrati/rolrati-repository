package roland.rati.training.core;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
		try {
			if (userDAO.findUserByUsername("admin") == null) {
				dto = new User();
				dto.setUsername("admin");
				dto.setPassword("admin");
				userDAO.save(dto);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	public void addRoleToAdmin(){
		User dto = null;
		try{
			if((dto = userDAO.findUserByUsername("admin")) != null){
				dto.setRole(roleDao.findRoleByName("ROLE_ADMIN"));
//				dto.setRole(roleDao.findRoleByName("ROLE_USER"));
				userDAO.save(dto);
			}
		}catch(Exception e){
			logger.error(e.getMessage(), e);
		}
	}
}
