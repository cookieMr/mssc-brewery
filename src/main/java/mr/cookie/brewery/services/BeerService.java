package mr.cookie.brewery.services;

import java.util.UUID;

import mr.cookie.brewery.web.model.BeerDTO;

public interface BeerService {

	public BeerDTO getBeerById(UUID beerId);

}
