package mr.cookie.brewery.web.controller;

import java.util.UUID;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import mr.cookie.brewery.services.CustomerService;
import mr.cookie.brewery.web.model.CustomerDTO;

@RequestMapping(CustomerController.URL)
@RestController
public class CustomerController {

	public static final String URL = "/api/v1/customer";
	private final CustomerService customerService;
	
	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	@GetMapping({"/{customerId}"})
	public ResponseEntity<CustomerDTO> getCustomer(@PathVariable("customerId") UUID customerId) {
		return new ResponseEntity<>(customerService.getCustomerById(customerId), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Void> saveCustomer(@RequestBody CustomerDTO customerDto) {
		CustomerDTO savedDto = customerService.saveCustomer(customerDto);
		
		HttpHeaders headers = new HttpHeaders();
        //TODO add hostname to url
        headers.add("Location", URL + savedDto.getId().toString());
        
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
	}
	
	@PutMapping({"/{customerId}"})
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateCustomer(
			@PathVariable("customerId") UUID customerId, 
			@RequestBody CustomerDTO customerDto) {
		customerService.updateCustomer(customerId, customerDto);
	}
	
	@DeleteMapping({"/{customerId}"})
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteCustomer(@PathVariable("customerId") UUID customerId) {
		customerService.deleteCustomer(customerId);
	}
	
}
