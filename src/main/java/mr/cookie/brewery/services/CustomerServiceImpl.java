package mr.cookie.brewery.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import mr.cookie.brewery.web.model.CustomerDTO;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Override
	public CustomerDTO getCustomerById(UUID customerId) {
		return CustomerDTO.builder()
				.name("Dave")
				.id(UUID.randomUUID())
				.build();
	}

	@Override
	public CustomerDTO saveCustomer(CustomerDTO customerDto) {
		return CustomerDTO.builder()
				.id(UUID.randomUUID())
				.build();
	}

	@Override
	public void updateCustomer(UUID customerId, CustomerDTO customerDto) {
		// TODO add impl soon(tm)
	}

	@Override
	public void deleteCustomer(UUID customerId) {
		// TODO add impl soon(tm)
	}

}
