package roland.rati.training.service.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import roland.rati.training.core.dao.PersonDao;
import roland.rati.training.service.PersonService;
import roland.rati.training.service.converter.PersonConverter;
import roland.rati.training.service.converter.UserConverter;
import roland.rati.training.service.vo.AddressVo;
import roland.rati.training.service.vo.PersonVo;
import roland.rati.training.service.vo.UserVo;

@Service("personService")
@Transactional(propagation = Propagation.REQUIRED)
public class PersonServiceImpl implements PersonService, Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	PersonDao personDao;

	@Override
	public PersonVo findPersonById(Long id) throws Exception {
		return PersonConverter.toVO(personDao.findOne(id));
	}

	@Override
	public PersonVo addUserAndAddressToPerson(UserVo user, AddressVo address,
			String firstname, String lastname, String sex, String email)
			throws Exception {
		PersonVo vo = new PersonVo();
		PersonVo rv = new PersonVo();

		vo.setAddress(address);
		vo.setUser(user);

		vo.setEmail(email);
		vo.setFirstname(firstname);
		vo.setLastname(lastname);
		vo.setSex(sex);
		
		

		rv = PersonConverter.toVO(personDao.saveAndFlush(PersonConverter.toEntity(vo)));
		
		return rv;
	}

	@Override
	public PersonVo findPersonByUserId(UserVo user) throws Exception {
		return PersonConverter.toVO(personDao.findPersonByUser(UserConverter
				.toEntity(user)));
	}

}
