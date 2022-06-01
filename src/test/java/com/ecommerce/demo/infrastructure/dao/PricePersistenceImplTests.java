package com.ecommerce.demo.infrastructure.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ecommerce.demo.domain.model.Price;
import com.ecommerce.demo.domain.persistence.PricePersistence;
import com.ecommerce.demo.infrastructure.dao.entity.PriceEntity;
import com.ecommerce.demo.infrastructure.dao.repository.PriceJpaRepository;

@SpringBootTest
class PricePersistenceImplTests {

	@Autowired
	private PriceJpaRepository repository;

	@Autowired
	private PricePersistence persistence;

	PriceEntity entity1 = new PriceEntity();
	PriceEntity entity2 = new PriceEntity();

	@BeforeEach
	void initDatabase() {
		entity1.setBrandId(1L);
		entity1.setStartDate(Instant.now().minus(2, ChronoUnit.DAYS));
		entity1.setEndDate(Instant.now().plus(2, ChronoUnit.DAYS));
		entity1.setPriceList(1L);
		entity1.setProductId(1L);
		entity1.setPriority(1);
		entity1.setAmount(20.00);
		entity1.setCurrency("EUR");
		repository.save(entity1);

		entity2.setBrandId(1L);
		entity2.setStartDate(Instant.now().plus(2, ChronoUnit.DAYS));
		entity2.setEndDate(Instant.now().plus(4, ChronoUnit.DAYS));
		entity2.setPriceList(2L);
		entity2.setProductId(1L);
		entity2.setPriority(2);
		entity2.setAmount(40.00);
		entity2.setCurrency("EUR");
		repository.save(entity2);
	}

	@AfterEach
	void deleteAllDatabase() {
		repository.deleteAll();
	}

	@Test
	void findByDateAndProductIdAndBrandIdTest() {
		List<Price> priceList = persistence.findByDateAndProductIdAndBrandId(Instant.now(), 1L, 1L);
		assertNotNull(priceList);
		assertTrue(!priceList.isEmpty());
		assertEquals(1, priceList.size());

		var price = priceList.get(0);
		assertEquals(price.getBrand().getId(), entity1.getBrandId());
		assertEquals(price.getStartDate().getDate().truncatedTo(ChronoUnit.SECONDS),
				entity1.getStartDate().truncatedTo(ChronoUnit.SECONDS));
		assertEquals(price.getEndDate().getDate().truncatedTo(ChronoUnit.SECONDS),
				entity1.getEndDate().truncatedTo(ChronoUnit.SECONDS));
		assertEquals(price.getPriceList(), entity1.getPriceList());
		assertEquals(price.getProduct().getId(), entity1.getProductId());
		assertEquals(price.getPriority(), entity1.getPriority());
		assertEquals(price.getMoney().getAmount().doubleValue(), entity1.getAmount());
		assertEquals(price.getMoney().getCurrency().toString(), entity1.getCurrency());
	}

}
