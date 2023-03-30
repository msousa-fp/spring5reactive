package com.mfpsousa.beerclient.client;

import com.mfpsousa.beerclient.domain.Beer;
import com.mfpsousa.beerclient.domain.BeerPagedList;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface BeerClient {

    Mono<Beer> getBeerById(UUID id, Boolean showInventoryOnHand);

    Mono<BeerPagedList> listBeers(Integer pageNumber, Integer pageSize, String beerName,
                                  String beerStyle, Boolean showInventoryOnHand);

    Mono<ResponseEntity<Void>> createBeer(Beer beer);

    Mono<ResponseEntity<Void>> updateBeer(UUID id, Beer beer);

    Mono<ResponseEntity<Void>> deleteBeerById(UUID id);

    Mono<Beer> getBeerByUPC(String upc);
}