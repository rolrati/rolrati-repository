package roland.rati.training.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import roland.rati.training.core.entity.Message;

@Repository
@Transactional(propagation = Propagation.SUPPORTS)
public interface MessageDao extends JpaRepository<Message, Long>, MessageDaoCustom {
	
	Message findMessageById(@Param("id") Long id) throws Exception;
	
}
