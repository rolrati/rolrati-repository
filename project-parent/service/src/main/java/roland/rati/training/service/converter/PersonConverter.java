package roland.rati.training.service.converter;

import java.util.LinkedList;
import java.util.List;

import roland.rati.training.core.entity.Person;
import roland.rati.training.service.vo.PersonVo;

public class PersonConverter {

	public static PersonVo toVO(Person dto) {
		if (dto == null) {
			return null;
		}

		PersonVo vo = new PersonVo();

		vo.setId(dto.getId());
		vo.setFirstname(dto.getFirstname());
		vo.setLastname(dto.getLastname());
		vo.setSex(dto.getSex());
		vo.setEmail(dto.getEmail());

		vo.setAddress(AddressConverter.toVO(dto.getAddress()));
		vo.setUser(UserConverter.toVO(dto.getUser()));

		return vo;
	}

	public static List<PersonVo> toVO(List<Person> dtos) {
		if (dtos == null) {
			return null;
		}

		List<PersonVo> vos = new LinkedList<PersonVo>();

		for (Person person : dtos) {
			vos.add(toVO(person));
		}

		return vos;
	}

	public static Person toEntity(PersonVo vo) {
		if (vo == null) {
			return null;
		}

		Person dto = new Person();

		dto.setId(vo.getId());
		dto.setEmail(vo.getEmail());
		dto.setFirstname(vo.getFirstname());
		dto.setLastname(vo.getLastname());
		dto.setSex(vo.getSex());

		dto.setAddress(AddressConverter.toEntity(vo.getAddress()));
		dto.setUser(UserConverter.toEntity(vo.getUser()));

		return dto;
	}

	public static List<Person> toEntity(List<PersonVo> vos) {
		if (vos == null) {
			return null;
		}

		List<Person> dtos = new LinkedList<Person>();

		for (PersonVo person : vos) {
			dtos.add(toEntity(person));
		}

		return dtos;
	}

}
