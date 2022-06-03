package com.ecommerce.demo.unit.application.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.Instant;
import java.util.Arrays;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.ecommerce.demo.PriceMother;
import com.ecommerce.demo.application.usecase.impl.PriceUseCasesImpl;
import com.ecommerce.demo.domain.model.Price;
import com.ecommerce.demo.domain.persistence.PricePersistence;

class PriceUseCasesImplTests {

	private PricePersistence persistence;

	private PriceUseCasesImpl usecase;

	private static Stream<Arguments> usesCasesRepository() {
		return Stream.of(arguments(PriceMother.TEST_DATE_PRICE_1, PriceMother.priceDomain1MotherTest()),
				arguments(PriceMother.TEST_DATE_PRICE_2, PriceMother.priceDomain2MotherTest()),
				arguments(PriceMother.TEST_DATE_PRICE_3, PriceMother.priceDomain3MotherTest()),
				arguments(PriceMother.TEST_DATE_PRICE_4, PriceMother.priceDomain4MotherTest()));
	}

	@BeforeEach
	void configMock() {
		persistence = mock(PricePersistence.class);
		usecase = new PriceUseCasesImpl(persistence);

		when(persistence.findByDateAndProductIdAndBrandId(PriceMother.TEST_DATE_PRICE_1, PriceMother.PRODUCT_ID,
				PriceMother.BRAND_ID)).thenReturn(Arrays.asList(PriceMother.priceDomain1MotherTest()));

		when(persistence.findByDateAndProductIdAndBrandId(PriceMother.TEST_DATE_PRICE_2, PriceMother.PRODUCT_ID,
				PriceMother.BRAND_ID))
				.thenReturn(Arrays.asList(PriceMother.priceDomain1MotherTest(), PriceMother.priceDomain2MotherTest()));

		when(persistence.findByDateAndProductIdAndBrandId(PriceMother.TEST_DATE_PRICE_3, PriceMother.PRODUCT_ID,
				PriceMother.BRAND_ID))
				.thenReturn(Arrays.asList(PriceMother.priceDomain1MotherTest(), PriceMother.priceDomain3MotherTest()));

		when(persistence.findByDateAndProductIdAndBrandId(PriceMother.TEST_DATE_PRICE_4, PriceMother.PRODUCT_ID,
				PriceMother.BRAND_ID))
				.thenReturn(Arrays.asList(PriceMother.priceDomain1MotherTest(), PriceMother.priceDomain4MotherTest()));

	}

	@ParameterizedTest
	@MethodSource("usesCasesRepository")
	void findByDateAndProductIdAndBrandIdTest(Instant date, Price expectedResult) {
		try {
			Price price = usecase.findByDateAndProductIdAndBrandId(date, PriceMother.PRODUCT_ID, PriceMother.BRAND_ID);
			assertNotNull(price);

			assertEquals(expectedResult.getBrand().getId(), price.getBrand().getId());
			assertEquals(expectedResult.getStartDate(), price.getStartDate());
			assertEquals(expectedResult.getEndDate(), price.getEndDate());
			assertEquals(expectedResult.getPriceList(), price.getPriceList());
			assertEquals(expectedResult.getProduct().getId(), price.getProduct().getId());
			assertEquals(expectedResult.getPriority(), price.getPriority());
			assertEquals(expectedResult.getMoney().getAmount(), price.getMoney().getAmount());
			assertEquals(expectedResult.getMoney().getCurrency(), price.getMoney().getCurrency());
		} catch (Exception e) {
			fail(e);
		}
	}

}
