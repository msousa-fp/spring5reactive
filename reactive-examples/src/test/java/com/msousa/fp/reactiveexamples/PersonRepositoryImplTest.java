package com.msousa.fp.reactiveexamples;

import com.msousa.fp.reactiveexamples.domain.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;

class PersonRepositoryImplTest {

    PersonRepositoryImpl personRepository;

    @BeforeEach
    void setUp() {
        personRepository = new PersonRepositoryImpl();
    }

    @Test
    void getByIdBlock() {
        Mono<Person> personMono = personRepository.getById(1);

        Person person = personMono.block();

        System.out.println(person);
    }

    @Test
    void getByIdSubscribe() {
        Mono<Person> personMono = personRepository.getById(1);

        StepVerifier
                .create(personMono)
                .expectNextCount(1)
                .verifyComplete();

        personMono.subscribe(person -> System.out.println(person));
    }

    @Test
    void getByIdSubscribeNotFound() {
        Mono<Person> personMono = personRepository.getById(5);

        StepVerifier
                .create(personMono)
                .verifyComplete();

        personMono.subscribe(person -> System.out.println(person));
    }

    @Test
    void getByIdMapFunction() {
        Mono<Person> personMono = personRepository.getById(1);
        personMono
                .map(person -> {
                    System.out.println(person);
                    return person.getFirstName();
                })
                .subscribe(lastName -> System.out.println("from map: " + lastName));
    }

    @Test
    void fluxTestBlockFirst() {
        Flux<Person> personFlux = personRepository.findAll();

        Person person = personFlux.blockFirst();

        System.out.println(person);
    }

    @Test
    void testFluxSubscribe() {
        Flux<Person> personFlux = personRepository.findAll();

        StepVerifier
                .create(personFlux)
                .expectNextCount(4)
                .verifyComplete();

        personFlux.subscribe(person ->
                System.out.println(person)
        );
    }

    @Test
    void testFluxToListMono() {
        Flux<Person> personFlux = personRepository.findAll();

        Mono<List<Person>> personListMono = personFlux.collectList();

        personListMono
                .subscribe(list -> list
                        .forEach(person -> System.out.println(person)));
    }

    @Test
    void testFindPersonById() {
        Flux<Person> personFlux = personRepository.findAll();

        final Integer id = 3;
        Mono<Person> personMono = personFlux
                .filter(person -> id.equals(person.getId()))
                .next();

        personMono.subscribe(person -> System.out.println(person));
    }

    @Test
    void testFindPersonByIdNotFound() {
        Flux<Person> personFlux = personRepository.findAll();

        final Integer id = 5;
        Mono<Person> personMono = personFlux
                .filter(person -> id.equals(person.getId()))
                .next();

        personMono.subscribe(person -> System.out.println(person));
    }

    @Test
    void testFindPersonByIdNotFoundWithException() {
        Flux<Person> personFlux = personRepository.findAll();

        final Integer id = 5;
        Mono<Person> personMono = personFlux
                .filter(person -> id.equals(person.getId()))
                .single();

        personMono
                .doOnError(throwable -> System.out.println("I went boom"))
                .onErrorReturn(Person.builder().build())
                .subscribe(person -> System.out.println(person));
    }
}