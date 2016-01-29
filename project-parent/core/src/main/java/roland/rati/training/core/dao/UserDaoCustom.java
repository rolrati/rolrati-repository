package roland.rati.training.core.dao;

import java.util.List;

import roland.rati.training.core.entity.User;

public interface UserDaoCustom {

	void removeRoleFromUser(Long userId, Long roleId) throws Exception;
	
	void removeAllRoleFromUser(Long userId) throws Exception;
	
	void addRoleToUser(Long userId, Long roleId) throws Exception;
	
	List<User> findUsersByRole(Long roleId) throws Exception;
}
