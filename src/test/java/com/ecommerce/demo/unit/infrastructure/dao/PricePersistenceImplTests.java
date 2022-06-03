package com.ecommerce.demo.unit.infrastructure.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.Instant;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.ecommerce.demo.PriceMother;
import com.ecommerce.demo.domain.model.Price;
import com.ecommerce.demo.infrastructure.dao.PricePersistenceImpl;
import com.ecommerce.demo.infrastructure.dao.entity.PriceEntity;
import com.ecommerce.demo.infrastructure.dao.mapper.PricePersistenceMapper;
import com.ecommerce.demo.infrastructure.dao.repository.PriceJpaRepository;

class PricePersistenceImplTests {

	private PriceJpaRepository repository;

	private PricePersistenceMapper mapper;

	private PricePersistenceImpl persistence;

	private static Stream<Arguments> usesCasesRepository() {
		return Stream.of(arguments(PriceMother.TEST_DATE_PRICE_1, Arrays.asList(PriceMother.price1MotherTest())),
				arguments(PriceMother.TEST_DATE_PRICE_2,
						Arrays.asList(PriceMother.price1MotherTest(), PriceMother.price2MotherTest())),
				arguments(PriceMother.TEST_DATE_PRICE_3,
						Arrays.asList(PriceMother.price1MotherTest(), PriceMother.price3MotherTest())),
				arguments(PriceMother.TEST_DATE_PRICE_4,
						Arrays.asList(PriceMother.price1MotherTest(), PriceMother.price4MotherTest())));
	}

	@BeforeEach
	void configMock() {
		repository = mock(PriceJpaRepository.class);
		mapper = mock(PricePersistenceMapper.class);
		persistence = new PricePersistenceImpl(repository, mapper);

		when(repository.findByStartDateBeforeAndEndDateAfterAndProductIdAndBrandId(PriceMother.TEST_DATE_PRICE_1,
				PriceMother.TEST_DATE_PRICE_1, PriceMother.PRODUCT_ID, PriceMother.BRAND_ID))
				.thenReturn(Arrays.asList(PriceMother.price1MotherTest()));

		when(repository.findByStartDateBeforeAndEndDateAfterAndProductIdAndBrandId(PriceMother.TEST_DATE_PRICE_2,
				PriceMother.TEST_DATE_PRICE_2, PriceMother.PRODUCT_ID, PriceMother.BRAND_ID))
				.thenReturn(Arrays.asList(PriceMother.price1MotherTest(), PriceMother.price2MotherTest()));

		when(repository.findByStartDateBeforeAndEndDateAfterAndProductIdAndBrandId(PriceMother.TEST_DATE_PRICE_3,
				PriceMother.TEST_DATE_PRICE_3, PriceMother.PRODUCT_ID, PriceMother.BRAND_ID))
				.thenReturn(Arrays.asList(PriceMother.price1MotherTest(), PriceMother.price3MotherTest()));

		when(repository.findByStartDateBeforeAndEndDateAfterAndProductIdAndBrandId(PriceMother.TEST_DATE_PRICE_4,
				PriceMother.TEST_DATE_PRICE_4, PriceMother.PRODUCT_ID, PriceMother.BRAND_ID))
				.thenReturn(Arrays.asList(PriceMother.price1MotherTest(), PriceMother.price4MotherTest()));

		when(mapper.entityToDomain(PriceMother.price1MotherTest())).thenReturn(PriceMother.priceDomain1MotherTest());
		when(mapper.entityToDomain(PriceMother.price2MotherTest())).thenReturn(PriceMother.priceDomain2MotherTest());
		when(mapper.entityToDomain(PriceMother.price3MotherTest())).thenReturn(PriceMother.priceDomain3MotherTest());
		when(mapper.entityToDomain(PriceMother.price4MotherTest())).thenReturn(PriceMother.priceDomain4MotherTest());
	}

	@ParameterizedTest
	@MethodSource("usesCasesRepository")
	void findByDateAndProductIdAndBrandIdTest(Instant date, List<PriceEntity> priceEntityListExpected) {
		List<Price> priceList = persistence.findByDateAndProductIdAndBrandId(date, PriceMother.PRODUCT_ID,
				PriceMother.BRAND_ID);
		assertNotNull(priceList);
		assertTrue(!priceList.isEmpty());
		assertEquals(priceEntityListExpected.size(), priceList.size());

		priceList = priceList.stream().sorted(Comparator.comparing(Price::getPriceList)).collect(Collectors.toList());
		priceEntityListExpected = priceEntityListExpected.stream()
				.sorted(Comparator.comparing(PriceEntity::getPriceList)).collect(Collectors.toList());

		for (int i = 0; i < priceList.size(); i++) {
			var price = priceList.get(i);
			var priceEntityExpected = priceEntityListExpected.get(i);
			assertEquals(priceEntityExpected.getBrand().getId(), price.getBrand().getId());
			assertEquals(priceEntityExpected.getStartDate(), price.getStartDate());
			assertEquals(priceEntityExpected.getEndDate(), price.getEndDate());
			assertEquals(priceEntityExpected.getPriceList(), price.getPriceList());
			assertEquals(priceEntityExpected.getProduct().getId(), price.getProduct().getId());
			assertEquals(priceEntityExpected.getPriority(), price.getPriority());
			assertEquals(priceEntityExpected.getAmount(), price.getMoney().getAmount().doubleValue());
			assertEquals(priceEntityExpected.getCurrency(), price.getMoney().getCurrency().toString());
		}
	}

}
