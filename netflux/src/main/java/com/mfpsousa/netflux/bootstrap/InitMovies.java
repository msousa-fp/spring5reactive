package com.mfpsousa.netflux.bootstrap;

import com.mfpsousa.netflux.domain.Movie;
import com.mfpsousa.netflux.repositories.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
@Component
public class InitMovies implements CommandLineRunner {
    private final MovieRepository movieRepository;

    @Override
    public void run(String... args) throws Exception {
        movieRepository.deleteAll()
                .thenMany(
                        Flux.just("M1", "M2", "M3", "M4", "M5")
                                .map(title -> Movie.builder().title(title).build())
                                .flatMap(movieRepository::save)
                ).subscribe(null, null, () -> {
                    movieRepository.findAll().subscribe(System.out::println);
                });
    }
}
