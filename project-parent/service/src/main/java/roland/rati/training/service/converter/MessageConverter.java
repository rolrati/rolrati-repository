package roland.rati.training.service.converter;

import java.util.LinkedList;
import java.util.List;

import roland.rati.training.core.entity.Message;
import roland.rati.training.service.vo.MessageVo;

public class MessageConverter {
	
	public static MessageVo toVO(Message dto){
		if (dto == null) {
			return null;
		}
		
		MessageVo vo = new MessageVo();
		
		vo.setId(dto.getId());
		vo.setMessage(dto.getMessage());
		vo.setSender(UserConverter.toVO(dto.getSender()));
		vo.setRecipient(UserConverter.toVO(dto.getRecipient()));
		
		return vo;
		
	}
	
	public static List<MessageVo> toVO(List<Message> dtos){
		if (dtos == null) {
			return null;
		}
		
		List<MessageVo> vos = new LinkedList<MessageVo>();
		
		for (Message dto : dtos) {
			vos.add(toVO(dto));
		}
		
		return vos;
		
	}
	
	public static Message toEntity(MessageVo vo){
		if(vo == null){
			return null;
		}
		
		Message dto = new Message();
		
		dto.setId(vo.getId());
		dto.setMessage(vo.getMessage());
		dto.setSender(UserConverter.toEntity(vo.getSender()));
		dto.setRecipient(UserConverter.toEntity(vo.getRecipient()));
		
		return dto;
		
	}
	
	public static List<Message> toEntity(List<MessageVo> vos){
		if(vos == null){
			return null;
		}
		
		List<Message> dtos = new LinkedList<Message>();
		
		for (MessageVo vo : vos) {
			dtos.add(toEntity(vo));
		}
		
		return dtos;
	}

}
