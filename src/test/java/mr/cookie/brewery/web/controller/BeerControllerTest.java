package mr.cookie.brewery.web.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
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

import mr.cookie.brewery.services.BeerService;
import mr.cookie.brewery.web.model.BeerDTO;

@WebMvcTest(controllers = BeerController.class)
public class BeerControllerTest {

	private static final ObjectMapper mapper = new ObjectMapper();
	
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private BeerService beerService;

    @Test
    public void getBeer() throws Exception {
    	BeerDTO validBeer = getValidBeer();
    	
        when(beerService.getBeerById(any(UUID.class))).thenReturn(validBeer);

        mockMvc.perform(get("/api/v1/beer/" + validBeer.getId().toString())
        		.accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(validBeer.getId().toString())))
                .andExpect(jsonPath("$.name", is("Beer1")));

        verify(beerService).getBeerById(any(UUID.class));
        verifyNoMoreInteractions(beerService);
    }

    @Test
    public void handlePost() throws Exception {
    	BeerDTO beerDto = getValidBeer();
        beerDto.setId(null);
        
        BeerDTO savedDto = BeerDTO.builder()
        		.id(UUID.randomUUID())
        		.name("New Beer")
        		.build();
        
        when(beerService.saveBeer(any(BeerDTO.class))).thenReturn(savedDto);

        mockMvc.perform(post("/api/v1/beer/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(beerDto)))
                .andExpect(status().isCreated());

        verify(beerService).saveBeer(any(BeerDTO.class));
        verifyNoMoreInteractions(beerService);

    }
    
    @Test
    public void handleUpdate() throws Exception {
    	BeerDTO beerDto = getValidBeer();

        mockMvc.perform(put("/api/v1/beer/" + beerDto.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(beerDto)))
                .andExpect(status().isNoContent());

        verify(beerService).updateBeer(any(UUID.class), any(BeerDTO.class));
        verifyNoMoreInteractions(beerService);
    }
    
    private BeerDTO getValidBeer() {
        return BeerDTO.builder().id(UUID.randomUUID())
                .name("Beer1")
                .style("PALE_ALE")
                .upc(123456789012L)
                .build();
    }
	
}
