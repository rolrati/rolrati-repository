package roland.rati.training.service;

import java.util.List;

import roland.rati.training.service.vo.MessageVo;
import roland.rati.training.service.vo.UserVo;

public interface MessageService {

	MessageVo findMessageById(Long id) throws Exception;

	List<MessageVo> findMessageByUser(Long userId) throws Exception;

	void addMessage(String message, UserVo sender, UserVo recipient, String date)
			throws Exception;

	void deleteMessage(MessageVo message) throws Exception;
	
	void updateMessageStatus(Long messageId, boolean status) throws Exception;

}
