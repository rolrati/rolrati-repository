package roland.rati.training.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import roland.rati.training.core.dao.MessageDao;
import roland.rati.training.service.MessageService;
import roland.rati.training.service.converter.MessageConverter;
import roland.rati.training.service.vo.MessageVo;
import roland.rati.training.service.vo.UserVo;

@Service("messageService")
@Transactional(propagation = Propagation.REQUIRED)
public class MessageServiceImpl implements MessageService, Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	MessageDao messageDao;

	public MessageServiceImpl() {
	}

	@Override
	public MessageVo findMessageById(Long id) throws Exception {
		return MessageConverter.toVO(messageDao.findMessageById(id));
	}

	@Override
	public List<MessageVo> findMessageByUser(Long userId) throws Exception {
		return MessageConverter.toVO(messageDao.findMessageByUser(userId));
	}

	@Override
	public void addMessage(String message, UserVo sender, UserVo recipient, String date)
			throws Exception {
		MessageVo vo = new MessageVo();
		vo.setMessage(message);
		vo.setSender(sender);
		vo.setRecipient(recipient);
		vo.setDate(date);
		vo.setViewed(false);

		messageDao.save(MessageConverter.toEntity(vo));
	}

	@Override
	public void deleteMessage(MessageVo message) throws Exception {
		messageDao.delete(MessageConverter.toEntity(message));
	}

	@Override
	public void updateMessageStatus(Long messageId, boolean status)
			throws Exception {
		MessageVo selectedMessage = MessageConverter.toVO(messageDao.findMessageById(messageId));
		selectedMessage.setViewed(status);
		messageDao.save(MessageConverter.toEntity(selectedMessage));
	}

}
