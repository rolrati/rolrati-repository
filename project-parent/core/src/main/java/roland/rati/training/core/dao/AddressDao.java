package roland.rati.training.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import roland.rati.training.core.entity.Address;

@Repository
@Transactional(propagation = Propagation.SUPPORTS)
public interface AddressDao extends JpaRepository<Address, Long> {

}
