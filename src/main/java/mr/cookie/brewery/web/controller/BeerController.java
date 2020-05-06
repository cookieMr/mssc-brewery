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

import mr.cookie.brewery.services.BeerService;
import mr.cookie.brewery.web.model.BeerDTO;

@RequestMapping("/api/v1/beer")
@RestController
public class BeerController {

	private final BeerService beerService;
	
	public BeerController(BeerService beerService) {
		this.beerService = beerService;
	}
	
	@GetMapping({"/{beerId}"})
	public ResponseEntity<BeerDTO> getBeer(@PathVariable("beerId") UUID beerId) {
		return new ResponseEntity<>(beerService.getBeerById(beerId), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Void> saveBeer(@RequestBody BeerDTO beerDto) {
		BeerDTO savedDto = beerService.saveBeer(beerDto);
		
		HttpHeaders headers = new HttpHeaders();
        //TODO add hostname to url
        headers.add("Location", "/api/v1/beer/" + savedDto.getId().toString());
        
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
	}
	
	@PutMapping({"/{beerId}"})
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateBeer(@PathVariable("beerId") UUID beerId, @RequestBody BeerDTO beerDto) {
		beerService.updateBeer(beerId, beerDto);
	}
	
	@DeleteMapping({"/{beerId}"})
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteBeer(@PathVariable("beerId") UUID beerId) {
		beerService.deleteBeer(beerId);
	}
	
}
