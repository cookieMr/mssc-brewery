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

}
