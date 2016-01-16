package roland.rati.training.converter;

import java.util.LinkedList;
import java.util.List;

import roland.rati.training.core.entity.User;
import roland.rati.training.service.vo.UserVO;

public class UserConverter {

	public static UserVO toVO(User dto) {

		UserVO vo = new UserVO();

		if (dto == null) {
			return null;
		}

		vo.setId(dto.getId());
		vo.setUsername(dto.getUsername());
		vo.setPassword(dto.getPassword());

		return vo;
	}

	public static List<UserVO> toVO(List<User> dtos) {

		List<UserVO> vos = new LinkedList<UserVO>();

		if (dtos == null) {
			return null;
		}

		for (User dto : dtos) {
			vos.add(toVO(dto));
		}

		return vos;
	}

	public static User toEntity(UserVO vo) {
		User dto = new User();

		if (vo == null) {
			return null;
		}
		
		dto.setId(vo.getId());
		dto.setUsername(vo.getUsername());
		dto.setPassword(vo.getPassword());

		return dto;
	}
	
	public static List<User> toEntity(List<UserVO> vos){
		
		List<User> dtos = new LinkedList<User>();
		
		if(vos == null){
			return null;
		}
		
		for(UserVO vo : vos){
			dtos.add(toEntity(vo));
		}
		
		return dtos;
	}
}
