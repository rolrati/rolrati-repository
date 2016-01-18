package roland.rati.training.service.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;

import roland.rati.training.converter.RoleConverter;
import roland.rati.training.core.dao.RoleDao;
import roland.rati.training.service.RoleService;
import roland.rati.training.service.vo.RoleVo;

public class RoleServiceImpl implements RoleService, Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	RoleDao roleDao;
	
	@Override
	public RoleVo findRoleByName(String name) throws Exception {
		return RoleConverter.toVO(roleDao.findRoleByName(name));
	}

	@Override
	public RoleVo findRoleById(Long id) throws Exception {
		return RoleConverter.toVO(roleDao.findRoleById(id));
	}

	@Override
	public void addRole(RoleVo role) throws Exception {
		roleDao.save(RoleConverter.toEntity(role));
	}

	@Override
	public void deleteRole(RoleVo role) throws Exception {
		roleDao.delete(RoleConverter.toEntity(role));
	}

	@Override
	public int countRoles() throws Exception {
		return (int) roleDao.count();
	}

}
