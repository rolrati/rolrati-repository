package roland.rati.training.core.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import roland.rati.training.core.entity.Role;

@Repository
@Transactional(propagation = Propagation.SUPPORTS)
public interface RoleDao extends JpaRepository<Role, Long> {

	Role findRoleByName(@Param("name") String name) throws Exception;
	
	Role findRoleById(@Param("id") Long id) throws Exception;
}
