package com.mfpsousa.stock.quote.service;

import com.mfpsousa.stock.quote.model.Quote;
import com.mfpsousa.stock.quote.domain.QuoteHistory;
import reactor.core.publisher.Mono;

public interface QuoteHistoryService {

    Mono<QuoteHistory> saveQuoteToMongo(Quote quote);
}
