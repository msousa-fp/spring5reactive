package com.mfpsousa.beerclient.client;

import com.mfpsousa.beerclient.config.WebClientConfig;
import com.mfpsousa.beerclient.domain.Beer;
import com.mfpsousa.beerclient.domain.BeerPagedList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BeerClientImplTest {

    BeerClient beerClient;

    @BeforeEach
    void setup(){
        beerClient = new BeerClientImpl(new WebClientConfig().webClient());
    }
    @Disabled("API returning inventory when should not be")
    @Test
    void getBeerById() {
        Mono<BeerPagedList> beerPagedListMono = beerClient.listBeers(null, null, null, null, null);

        BeerPagedList pagedList = beerPagedListMono.block();

        UUID beerId = Objects.requireNonNull(pagedList).getContent().get(0).getId();

        Mono<Beer> beerMono = beerClient.getBeerById(beerId, false);

        Beer beer = beerMono.block();

        assertThat(beer).isNotNull();
        assertThat(beer.getId()).isEqualTo(beerId);
        assertThat(beer.getQuantityOnHand()).isNull();
    }

    @Test
    void getBeerByIdShowInventoryTrue() {
        Mono<BeerPagedList> beerPagedListMono = beerClient.listBeers(null, null, null, null,
                null);

        BeerPagedList pagedList = beerPagedListMono.block();

        UUID beerId = Objects.requireNonNull(pagedList).getContent().get(0).getId();

        Mono<Beer> beerMono = beerClient.getBeerById(beerId, true);

        Beer beerDto = beerMono.block();

        assertThat(beerDto).isNotNull();
        assertThat(beerDto.getId()).isEqualTo(beerId);
        assertThat(beerDto.getQuantityOnHand()).isNotNull();
    }

    @Test
    void listBeers() {
        BeerPagedList beerPagedList = beerClient
                .listBeers(null, null, null, null, null)
                .block();

        assertThat(beerPagedList).isNotNull();
        assertThat(beerPagedList.getContent()).isNotEmpty();
    }

    @Test
    void listBeersPageSize10() {
        Mono<BeerPagedList> beerPagedListMono = beerClient.listBeers(1, 10, null, null,
                null);

        BeerPagedList pagedList = beerPagedListMono.block();

        assertThat(pagedList).isNotNull();
        assertThat(pagedList.getContent()).hasSize(10);
    }

    @Test
    void listBeersNoRecords() {
        Mono<BeerPagedList> beerPagedListMono = beerClient.listBeers(10, 20, null, null,
                null);

        BeerPagedList pagedList = beerPagedListMono.block();

        assertThat(pagedList).isNotNull();
        assertThat(pagedList.getContent().size()).isZero();
    }

    @Test
    void createBeer() {
        Beer newBeer = Beer.builder()
                .beerName("Super Bock")
                .beerStyle("IPA")
                .upc("pt1929")
                .price(new BigDecimal("0.54"))
                .build();

        Mono<ResponseEntity<Void>> responseMono = beerClient.createBeer(newBeer);

        ResponseEntity<Void> response = responseMono.block();

        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);

    }

    @Test
    void updateBeer() {

        Mono<BeerPagedList> beerPagedListMono = beerClient.listBeers(null, null, null, null, null);

        BeerPagedList pagedList = beerPagedListMono.block();

        Beer beer = Objects.requireNonNull(pagedList).getContent().get(0);

        Beer newBeer = Beer.builder()
                .beerName("Super Bock")
                .beerStyle(beer.getBeerStyle())
                .upc(beer.getUpc())
                .price(new BigDecimal("0.54"))
                .build();

        Mono<ResponseEntity<Void>> responseMono = beerClient.updateBeer(beer.getId(), newBeer);

        ResponseEntity<Void> response = responseMono.block();

        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    void deleteBeerById() {

        Mono<BeerPagedList> beerPagedListMono = beerClient.listBeers(null, null, null, null, null);

        BeerPagedList pagedList = beerPagedListMono.block();

        UUID beerId = Objects.requireNonNull(pagedList).getContent().get(0).getId();

        Mono<ResponseEntity<Void>> responseMono = beerClient.deleteBeerById(beerId);

        ResponseEntity<Void> response = responseMono.block();

        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    void deleteBeerByIdNotFound() {
        UUID beerId = UUID.randomUUID();

        Mono<ResponseEntity<Void>> responseMono = beerClient.deleteBeerById(beerId);

        assertThrows(WebClientResponseException.class, () -> responseMono.block());
    }


    @Test
    void getBeerByUPC() {
        Mono<BeerPagedList> beerPagedListMono = beerClient.listBeers(null, null, null, null,
                null);

        BeerPagedList pagedList = beerPagedListMono.block();

        String upc = Objects.requireNonNull(pagedList).getContent().get(0).getUpc();

        Mono<Beer> beerMono = beerClient.getBeerByUPC(upc);

        Beer beer = beerMono.block();

        assertThat(beer).isNotNull();
        assertThat(beer.getUpc()).isEqualTo(upc);

    }
}