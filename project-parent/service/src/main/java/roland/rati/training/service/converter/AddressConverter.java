package roland.rati.training.service.converter;

import java.util.LinkedList;
import java.util.List;

import roland.rati.training.core.entity.Address;
import roland.rati.training.service.vo.AddressVo;

public class AddressConverter {

	public static AddressVo toVO(Address dto){
		if (dto == null) {
			return null;
		}
		
		AddressVo vo = new AddressVo();
		
		vo.setId(dto.getId());
		vo.setZipcode(dto.getZipcode());
		vo.setCity(dto.getCity());
		vo.setStreet(dto.getStreet());
		vo.setNumber(dto.getNumber());
		
		return vo;
		
	}
	
	public static List<AddressVo> toVO(List<Address> dtos){
		if (dtos == null) {
			return null;
		}
		
		List<AddressVo> vos = new LinkedList<AddressVo>();
		
		for (Address address : dtos) {
			vos.add(toVO(address));
		}
		
		return vos;
	}
	
	public static Address toEntity(AddressVo vo){
		if (vo == null) {
			return null;
		}
		
		Address dto = new Address();
		
		dto.setId(vo.getId());
		dto.setCity(vo.getCity());
		dto.setStreet(vo.getStreet());
		dto.setZipcode(vo.getZipcode());
		dto.setNumber(vo.getNumber());
		
		return dto;
	}
	
	public static List<Address> toEntity(List<AddressVo> vos){
		if (vos == null) {
			return null;
		}
		
		List<Address> dtos = new LinkedList<Address>();
		
		for (AddressVo address : vos) {
			dtos.add(toEntity(address));
		}
		
		return dtos;
	}
}
