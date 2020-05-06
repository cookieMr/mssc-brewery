package mr.cookie.brewery.web.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import mr.cookie.brewery.services.CustomerService;
import mr.cookie.brewery.web.model.CustomerDTO;

@WebMvcTest(controllers = CustomerController.class)
public class CustomerControllerTest {

	private static final String CUSTOMER_URL = "/api/v1/customer/";
	private static final ObjectMapper MAPPER = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private CustomerService customerService;

    @Test
    public void getCustomer() throws Exception {
    	CustomerDTO validCustomer = getValidCustomer();
    	
        when(customerService.getCustomerById(any(UUID.class))).thenReturn(validCustomer);

        mockMvc.perform(get(CUSTOMER_URL + validCustomer.getId().toString())
        		.accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(validCustomer.getId().toString())))
                .andExpect(jsonPath("$.name", is("Customer1")));

        verify(customerService).getCustomerById(eq(validCustomer.getId()));
        verifyNoMoreInteractions(customerService);
    }

    @Test
    public void saveCustomer() throws Exception {
    	CustomerDTO validCustomer = getValidCustomer();
    	validCustomer.setId(null);
    	
    	CustomerDTO savedDto =  CustomerDTO.builder()
				.id(UUID.randomUUID())
				.build();
    	
        when(customerService.saveCustomer(any(CustomerDTO.class))).thenReturn(savedDto);

        mockMvc.perform(post(CUSTOMER_URL)
        		.contentType(MediaType.APPLICATION_JSON)
        		.content(MAPPER.writeValueAsString(validCustomer)))
        		.andExpect(status().isCreated())
        		.andExpect(jsonPath("$").doesNotExist());

        verify(customerService).saveCustomer(eq(validCustomer));
        verifyNoMoreInteractions(customerService);
    }

    @Test
    public void updateCustomer() throws Exception {
    	CustomerDTO validCustomer = getValidCustomer();

        mockMvc.perform(put(CUSTOMER_URL + validCustomer.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(MAPPER.writeValueAsString(validCustomer)))
                .andExpect(status().isNoContent())
                .andExpect(jsonPath("$").doesNotExist());

        verify(customerService).updateCustomer(eq(validCustomer.getId()), eq(validCustomer));
        verifyNoMoreInteractions(customerService);
    }

    @Test
    public void deleteCustomer() throws Exception {
    	UUID uuid = UUID.randomUUID();

        mockMvc.perform(delete(CUSTOMER_URL + uuid.toString()))
                .andExpect(status().isNoContent())
                .andExpect(jsonPath("$").doesNotExist());

        verify(customerService).deleteCustomer(eq(uuid));
        verifyNoMoreInteractions(customerService);
    }
    
    private CustomerDTO getValidCustomer() {
		return CustomerDTO.builder()
				.name("Customer1")
				.id(UUID.randomUUID())
				.build();
    }
    
}
