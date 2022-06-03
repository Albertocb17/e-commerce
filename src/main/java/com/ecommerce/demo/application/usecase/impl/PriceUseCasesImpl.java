package com.ecommerce.demo.application.usecase.impl;

import java.time.Instant;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ecommerce.demo.application.usecase.PriceUseCases;
import com.ecommerce.demo.domain.exception.PriceException;
import com.ecommerce.demo.domain.model.Price;
import com.ecommerce.demo.domain.persistence.PricePersistence;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PriceUseCasesImpl implements PriceUseCases {

	private final PricePersistence persistence;

	@Override
	public Price findByDateAndProductIdAndBrandId(Instant date, Long productId, Long brandId) throws Exception {
		List<Price> priceList = persistence.findByDateAndProductIdAndBrandId(date, productId, brandId);
		
		Optional<Price> priceOpt = priceList.stream()
				.sorted(Comparator.comparing(Price::getPriority).reversed())
				.findFirst();
		
		if (priceOpt.isPresent())
			return priceOpt.get();
		else
			throw new PriceException("No existe una tarifa de precios aplicable para los datos introducidos.");
	}

}
