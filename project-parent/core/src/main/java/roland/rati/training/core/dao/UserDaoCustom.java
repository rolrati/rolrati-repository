package roland.rati.training.core.dao;


public interface UserDaoCustom {

	void removeRoleFromUser(Long userId, Long roleId) throws Exception;
	
	void removeAllRoleFromUser(Long userId) throws Exception;
}
