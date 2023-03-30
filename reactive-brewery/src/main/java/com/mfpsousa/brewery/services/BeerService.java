package com.mfpsousa.brewery.services;

import com.mfpsousa.brewery.web.model.BeerDto;
import com.mfpsousa.brewery.web.model.BeerPagedList;
import com.mfpsousa.brewery.web.model.BeerStyleEnum;
import org.springframework.data.domain.PageRequest;
import reactor.core.publisher.Mono;

/**
 * Created by jt on 2019-04-20.
 */
public interface BeerService {
    Mono<BeerPagedList> listBeers(String beerName, BeerStyleEnum beerStyle, PageRequest pageRequest, Boolean showInventoryOnHand);

    Mono<BeerDto> getById(Integer beerId, Boolean showInventoryOnHand);

    Mono<BeerDto> saveNewBeer(BeerDto beerDto);

    Mono<BeerDto> updateBeer(Integer beerId, BeerDto beerDto);

    Mono<BeerDto> getByUpc(String upc);

    void deleteBeerById(Integer beerId);
}
