package com.ecommerce.demo.domain.persistence;

import java.time.Instant;
import java.util.List;

import com.ecommerce.demo.domain.model.Price;

public interface PricePersistence {

	List<Price> findByDateAndProductIdAndBrandId(Instant date, Long productId, Long brandId);

}
