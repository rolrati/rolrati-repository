package roland.rati.training.service;

import roland.rati.training.service.vo.AddressVo;
import roland.rati.training.service.vo.PersonVo;
import roland.rati.training.service.vo.UserVo;

public interface PersonService {

	public PersonVo findPersonById(Long id) throws Exception;
	
	public PersonVo findPersonByUserId(UserVo user) throws Exception;

	public PersonVo addUserAndAddressToPerson(UserVo user, AddressVo address,
			String firstname, String lastname, String sex, String email)
			throws Exception;
}
