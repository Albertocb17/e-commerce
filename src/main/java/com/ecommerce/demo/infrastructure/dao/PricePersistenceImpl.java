package com.ecommerce.demo.infrastructure.dao;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.demo.domain.model.Price;
import com.ecommerce.demo.domain.persistence.PricePersistence;
import com.ecommerce.demo.infrastructure.dao.entity.PriceEntity;
import com.ecommerce.demo.infrastructure.dao.mapper.PricePersistenceMapper;
import com.ecommerce.demo.infrastructure.dao.repository.PriceJpaRepository;

@Service
public class PricePersistenceImpl implements PricePersistence {

	@Autowired
	private PriceJpaRepository repository;

	@Autowired
	private PricePersistenceMapper mapper;

	@Override
	public List<Price> findByDateAndProductIdAndBrandId(Instant date, Long productId, Long brandId) {
		List<PriceEntity> priceList = repository.findByStartDateBeforeAndEndDateAfterAndProductIdAndBrandId(date, date,
				productId, brandId);
		return priceList.stream().map(mapper::entityToDomain).collect(Collectors.toList());
	}

}
