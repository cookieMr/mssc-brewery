package mr.cookie.brewery.services;

import java.util.UUID;

import mr.cookie.brewery.web.model.CustomerDTO;

public interface CustomerService {

	CustomerDTO getCustomerById(UUID customerId);	
	
}
