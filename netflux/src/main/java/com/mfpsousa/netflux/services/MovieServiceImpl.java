package com.mfpsousa.netflux.services;

import com.mfpsousa.netflux.domain.Movie;
import com.mfpsousa.netflux.domain.MovieEvent;
import com.mfpsousa.netflux.repositories.MovieRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    @Override
    public Mono<Movie> getMovieById(String id) {
        return movieRepository.findById(id);
    }

    @Override
    public Flux<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public Flux<MovieEvent> streamMovieEvents(String id) {
        return Flux
                .<MovieEvent>generate(movieEventSynchronousSink -> movieEventSynchronousSink
                        .next(MovieEvent.builder()
                                .movieId(id)
                                .movieDate(LocalDateTime.now())
                                .build()))
                .delayElements(Duration.ofSeconds(1));
    }
}
