package roland.rati.training.core;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import roland.rati.training.core.dao.UserDAO;
import roland.rati.training.core.entity.User;

@Component
@Transactional
public class CreateShema {
	private static final Logger logger = Logger.getLogger(CreateShema.class);
	
	@Autowired
	public UserDAO userDAO;
	
	public void insertAdmin(){
		User dto = null;
		try{
			if (userDAO.findUserByName("admin") == null) {
				dto = new User();
				dto.setUsername("admin");
				dto.setPassword("admin");
				userDAO.save(dto);
			}
		}catch(Exception e){
			logger.error(e.getMessage(), e);
		}
	}

}
