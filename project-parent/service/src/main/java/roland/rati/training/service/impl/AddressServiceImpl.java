package roland.rati.training.service.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import roland.rati.training.core.dao.AddressDao;
import roland.rati.training.service.AddressService;
import roland.rati.training.service.converter.AddressConverter;
import roland.rati.training.service.vo.AddressVo;

@Service("addressService")
@Transactional(propagation = Propagation.REQUIRED)
public class AddressServiceImpl implements AddressService, Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	AddressDao addressDao;

	@Override
	public AddressVo findAddressById(Long id) throws Exception {
		return AddressConverter.toVO(addressDao.findOne(id));
	}

	@Override
	public AddressVo saveAndFlushAddress(AddressVo address) throws Exception {

		AddressVo vo = new AddressVo();

		vo = AddressConverter.toVO(addressDao.saveAndFlush(AddressConverter
				.toEntity(address)));
		return vo;
	}
}
