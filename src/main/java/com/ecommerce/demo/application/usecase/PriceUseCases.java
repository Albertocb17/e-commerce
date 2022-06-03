package com.ecommerce.demo.application.usecase;

import java.time.Instant;

import com.ecommerce.demo.domain.model.Price;

public interface PriceUseCases {

	Price findByDateAndProductIdAndBrandId(Instant date, Long productId, Long brandId) throws Exception;

}
