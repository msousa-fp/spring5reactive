package com.mfpsousa.streaming.stock.quote.service;


import com.mfpsousa.streaming.stock.quote.model.Quote;
import reactor.core.publisher.Flux;

import java.time.Duration;

public interface QuoteGeneratorService {

    Flux<Quote> fetchQuoteStream(Duration period);
}
