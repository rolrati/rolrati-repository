package roland.rati.training.converter;

import java.util.LinkedList;
import java.util.List;

import roland.rati.training.core.entity.Role;
import roland.rati.training.core.entity.User;
import roland.rati.training.service.vo.RoleVo;
import roland.rati.training.service.vo.UserVo;

public class UserConverter {

	public static UserVo toVO(User dto) {
		if (dto == null) {
			return null;
		}

		UserVo vo = new UserVo();

		vo.setId(dto.getId());
		vo.setUsername(dto.getUsername());
		vo.setPassword(dto.getPassword());

		if (dto.getRoles() != null) {
			List<RoleVo> roleVos = new LinkedList<RoleVo>();
			for (Role role : dto.getRoles()) {
				roleVos.add(RoleConverter.toVO(role));
			}
			vo.setRoles(roleVos);
		}

		return vo;
	}

	public static List<UserVo> toVO(List<User> dtos) {

		List<UserVo> vos = new LinkedList<UserVo>();

		if (dtos == null) {
			return null;
		}

		for (User dto : dtos) {
			vos.add(toVO(dto));
		}

		return vos;
	}

	public static User toEntity(UserVo vo) {
		if (vo == null) {
			return null;
		}

		User dto = new User();

		dto.setId(vo.getId());
		dto.setUsername(vo.getUsername());
		dto.setPassword(vo.getPassword());

		if (vo.getRoles() != null) {
			List<Role> dtos = new LinkedList<Role>();
			for (RoleVo role : vo.getRoles()) {
				dtos.add(RoleConverter.toEntity(role));
			}
			dto.setRoles(dtos);
		}

		return dto;
	}

	public static List<User> toEntity(List<UserVo> vos) {

		List<User> dtos = new LinkedList<User>();

		if (vos == null) {
			return null;
		}

		for (UserVo vo : vos) {
			dtos.add(toEntity(vo));
		}

		return dtos;
	}
}
