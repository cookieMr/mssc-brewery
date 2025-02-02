package mr.cookie.brewery.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import mr.cookie.brewery.web.model.BeerDTO;

@Service
public class BeerServiceImpl implements BeerService {

	@Override
	public BeerDTO getBeerById(UUID beerId) {
		return BeerDTO.builder()
				.id(UUID.randomUUID())
				.name("Galaxy Cat")
				.style("Pale Ale")
				.build();
	}

	@Override
	public BeerDTO saveBeer(BeerDTO beerDto) {
		return BeerDTO.builder()
				.id(UUID.randomUUID())
				.build();
	}

	@Override
	public void updateBeer(UUID beerId, BeerDTO beerDto) {
		// TODO add impl soon(tm)
	}

	@Override
	public void deleteBeer(UUID beerId) {
		// TODO add impl soon(tm)
	}
	
}
