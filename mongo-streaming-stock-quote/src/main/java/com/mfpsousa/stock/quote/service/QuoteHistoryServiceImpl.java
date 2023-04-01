package com.mfpsousa.stock.quote.service;

import com.mfpsousa.stock.quote.model.Quote;
import com.mfpsousa.stock.quote.repositories.QuoteHistoryRepository;
import com.mfpsousa.stock.quote.domain.QuoteHistory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class QuoteHistoryServiceImpl implements QuoteHistoryService {

    private final QuoteHistoryRepository repository;

    @Override
    public Mono<QuoteHistory> saveQuoteToMongo(Quote quote) {
        return repository.save(QuoteHistory.builder()
                .ticker(quote.getTicker())
                .price(quote.getPrice())
                .instant(quote.getInstant())
                .build());
    }
}
