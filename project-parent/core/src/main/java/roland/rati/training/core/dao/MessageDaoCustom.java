package roland.rati.training.core.dao;

import java.util.List;

import roland.rati.training.core.entity.Message;

public interface MessageDaoCustom {

	List<Message> findMessageByUser(Long userId) throws Exception;
}
