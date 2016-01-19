package roland.rati.training.service.converter;

import java.util.LinkedList;
import java.util.List;

import roland.rati.training.core.entity.Role;
import roland.rati.training.service.vo.RoleVo;

public class RoleConverter {

	public static RoleVo toVO(Role dto) {
		if (dto == null) {
			return null;
		}

		RoleVo vo = new RoleVo();
		vo.setId(dto.getId());
		vo.setName(dto.getName());

		return vo;
	}
	
	public static List<RoleVo> toVO(List<Role> dtos){
		if (dtos == null) {
			return null;
		}
		
		List<RoleVo> vos = new LinkedList<RoleVo>();
		
		for (Role role : dtos) {
			vos.add(toVO(role));
		}
		
		return vos;
	}
	
	public static Role toEntity(RoleVo vo){
		if (vo == null) {
			return null;
		}
		
		Role dto = new Role();
		
		dto.setId(vo.getId());
		dto.setName(vo.getName());
		
		return dto;		
	}
	
	public static List<Role> toEntity(List<RoleVo> vos){
		if (vos == null) {
			return null;
		}
		
		List<Role> dtos = new LinkedList<Role>();
		
		for (RoleVo vo : vos) {
			dtos.add(toEntity(vo));
		}
		
		return dtos;
	}
}
