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
	
}
