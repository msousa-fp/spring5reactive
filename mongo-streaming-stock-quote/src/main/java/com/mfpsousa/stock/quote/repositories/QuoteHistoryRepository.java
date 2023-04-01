package com.mfpsousa.stock.quote.repositories;

import com.mfpsousa.stock.quote.domain.QuoteHistory;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface QuoteHistoryRepository extends ReactiveMongoRepository<QuoteHistory, String> {
}
