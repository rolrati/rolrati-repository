package roland.rati.training.service;

import roland.rati.training.service.vo.RoleVo;

public interface RoleService {
	
	public RoleVo findRoleByName(String name) throws Exception;
	
	public RoleVo findRoleById(Long id) throws Exception;
	
	public void addRole(RoleVo role) throws Exception;
	
	public void deleteRole(RoleVo role) throws Exception;
	
	public int countRoles() throws Exception;

}
