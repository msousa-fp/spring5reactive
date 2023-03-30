package com.mfpsousa.beerclient.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Beer {

    UUID id;
    String beerName;
    String beerStyle;
    String upc;
    BigDecimal price;
    Integer quantityOnHand;
    OffsetDateTime createdDate;
    OffsetDateTime lastUpdatedDate;
}