package com.mfpsousa.netflux.domain;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovieEvent {

    private String movieId;

    @NonNull
    private LocalDateTime movieDate;
}
