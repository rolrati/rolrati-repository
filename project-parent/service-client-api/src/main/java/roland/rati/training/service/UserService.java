package roland.rati.training.service;

import java.util.List;

import roland.rati.training.service.vo.UserVo;

public interface UserService {
	
	public UserVo findUserByName(String name) throws Exception;
	
	public UserVo findUserById(Long id) throws Exception;
	
	public void addUser(UserVo user) throws Exception;
	
	public void modifyUser(String username, UserVo user) throws Exception;
	
	public void deleteUser(UserVo user) throws Exception;
	
	public void addRoleToUser(Long userId, Long roleId) throws Exception;
	
	public void removeRoleFromUser(Long userId, Long roleId) throws Exception;
	
	public void removeAllRoleFromUser(Long userId) throws Exception;
	
	public int countUsers() throws Exception;

	public List<UserVo> findUsersByRole(Long id) throws Exception;

	public List<UserVo> findAllUser() throws Exception;

}
