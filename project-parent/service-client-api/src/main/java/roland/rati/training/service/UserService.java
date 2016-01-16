package roland.rati.training.service;

import roland.rati.training.service.vo.UserVO;

public interface UserService {
	
	public UserVO findUserByName(String name) throws Exception;
	
	public void registrateUser(UserVO user) throws Exception;
	
	public void modifyUser(String username, UserVO user) throws Exception;
	
	public int countUsers() throws Exception;

}
