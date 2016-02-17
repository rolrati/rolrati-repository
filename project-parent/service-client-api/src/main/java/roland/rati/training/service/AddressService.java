package roland.rati.training.service;

import roland.rati.training.service.vo.AddressVo;

public interface AddressService {
	
	public AddressVo findAddressById(Long id) throws Exception;
	
	public AddressVo saveAndFlushAddress(AddressVo address) throws Exception;
}
