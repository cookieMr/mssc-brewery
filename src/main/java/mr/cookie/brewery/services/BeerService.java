package mr.cookie.brewery.services;

import java.util.UUID;

import mr.cookie.brewery.web.model.BeerDTO;

public interface BeerService {

	BeerDTO getBeerById(UUID beerId);

	BeerDTO saveBeer(BeerDTO beerDto);

	void updateBeer(UUID beerId, BeerDTO beerDto);

	void deleteBeer(UUID beerId);
	
}
