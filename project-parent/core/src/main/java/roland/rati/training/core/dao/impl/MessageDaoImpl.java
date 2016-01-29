package roland.rati.training.core.dao.impl;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import roland.rati.training.core.dao.MessageDao;
import roland.rati.training.core.dao.MessageDaoCustom;
import roland.rati.training.core.entity.Message;

public class MessageDaoImpl implements MessageDaoCustom {

	@Autowired
	MessageDao dao;

	@Override
	public List<Message> findMessageByUser(Long userId) throws Exception {
		List<Message> dtos = null;

		dtos = dao.findAll();

		if (dtos == null) {
			return null;
		}

		List<Message> rdtos = new LinkedList<Message>();

		for (Message message : dtos) {
			if (message.getSender().getId() == userId
					|| message.getRecipient().getId() == userId) {
				rdtos.add(message);
			}
		}
		return rdtos;
	}
}
