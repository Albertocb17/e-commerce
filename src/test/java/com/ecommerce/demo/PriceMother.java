package com.ecommerce.demo;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

import com.ecommerce.demo.domain.model.Brand;
import com.ecommerce.demo.domain.model.Price;
import com.ecommerce.demo.domain.model.Product;
import com.ecommerce.demo.domain.vo.MoneyVO;
import com.ecommerce.demo.infrastructure.dao.entity.BrandEntity;
import com.ecommerce.demo.infrastructure.dao.entity.PriceEntity;
import com.ecommerce.demo.infrastructure.dao.entity.ProductEntity;
import com.ecommerce.demo.infrastructure.rest.config.JsonInstantSerializer;

public class PriceMother {

	public static final long BRAND_ID = 1L;
	public static final long PRODUCT_ID = 35455L;

	public static final Instant TEST_DATE_PRICE_1 = Instant
			.from(JsonInstantSerializer.FORMATTER.parse(PriceMother.START_DATE_PRICE_1)).plus(5, ChronoUnit.MINUTES);
	public static final String START_DATE_PRICE_1 = "2020-06-14-00.00.00";
	private static final String END_DATE_PRICE_1 = "2020-12-31-23.59.59";

	public static final Instant TEST_DATE_PRICE_2 = Instant
			.from(JsonInstantSerializer.FORMATTER.parse(PriceMother.START_DATE_PRICE_2)).plus(5, ChronoUnit.MINUTES);
	public static final String START_DATE_PRICE_2 = "2020-06-14-15.00.00";
	private static final String END_DATE_PRICE_2 = "2020-06-14-18.30.00";

	public static final Instant TEST_DATE_PRICE_3 = Instant
			.from(JsonInstantSerializer.FORMATTER.parse(PriceMother.START_DATE_PRICE_3)).plus(5, ChronoUnit.MINUTES);
	public static final String START_DATE_PRICE_3 = "2020-06-15-00.00.00";
	private static final String END_DATE_PRICE_3 = "2020-06-15-11.00.00";

	public static final Instant TEST_DATE_PRICE_4 = Instant
			.from(JsonInstantSerializer.FORMATTER.parse(PriceMother.START_DATE_PRICE_4)).plus(5, ChronoUnit.MINUTES);
	public static final String START_DATE_PRICE_4 = "2020-06-15-16.00.00";
	private static final String END_DATE_PRICE_4 = "2020-12-31-23.59.59";

	private static final int PRIORITY_PRICE_1 = 0;
	private static final int PRIORITY_PRICE_2_3_4 = 1;

	private static final double AMOUNT_PRICE_1 = 35.50;
	private static final double AMOUNT_PRICE_2 = 25.45;
	private static final double AMOUNT_PRICE_3 = 30.50;
	private static final double AMOUNT_PRICE_4 = 38.95;

	private static final String CURRENCY = "EUR";

	public static BrandEntity brandMotherTest() {
		BrandEntity brandEntity = new BrandEntity();
		brandEntity.setId(BRAND_ID);
		return brandEntity;
	}

	public static ProductEntity productMotherTest() {
		ProductEntity productEntity = new ProductEntity();
		productEntity.setId(PRODUCT_ID);
		return productEntity;
	}

	public static List<PriceEntity> priceListMotherTest() {
		List<PriceEntity> priceEntityList = new ArrayList<>();
		priceEntityList.add(price1MotherTest());
		priceEntityList.add(price2MotherTest());
		priceEntityList.add(price3MotherTest());
		priceEntityList.add(price4MotherTest());

		return priceEntityList;
	}

	public static PriceEntity price1MotherTest() {
		PriceEntity priceEntity1 = new PriceEntity();
		priceEntity1.setBrand(brandMotherTest());
		priceEntity1.setStartDate(Instant.from(JsonInstantSerializer.FORMATTER.parse(START_DATE_PRICE_1)));
		priceEntity1.setEndDate(Instant.from(JsonInstantSerializer.FORMATTER.parse(END_DATE_PRICE_1)));
		priceEntity1.setPriceList(1L);
		priceEntity1.setProduct(productMotherTest());
		priceEntity1.setPriority(PRIORITY_PRICE_1);
		priceEntity1.setAmount(AMOUNT_PRICE_1);
		priceEntity1.setCurrency(CURRENCY);
		return priceEntity1;
	}

	public static Price priceDomain1MotherTest() {
		return new Price(new Brand(BRAND_ID),
				Instant.from(JsonInstantSerializer.FORMATTER.parse(START_DATE_PRICE_1)),
				Instant.from(JsonInstantSerializer.FORMATTER.parse(END_DATE_PRICE_1)),
				1L,
				new Product(PRODUCT_ID),
				PRIORITY_PRICE_1,
				new MoneyVO(AMOUNT_PRICE_1, Currency.getInstance(CURRENCY)));
	}

	public static PriceEntity price2MotherTest() {
		PriceEntity priceEntity2 = new PriceEntity();
		priceEntity2.setBrand(brandMotherTest());
		priceEntity2.setStartDate(Instant.from(JsonInstantSerializer.FORMATTER.parse(START_DATE_PRICE_2)));
		priceEntity2.setEndDate(Instant.from(JsonInstantSerializer.FORMATTER.parse(END_DATE_PRICE_2)));
		priceEntity2.setPriceList(2L);
		priceEntity2.setProduct(productMotherTest());
		priceEntity2.setPriority(PRIORITY_PRICE_2_3_4);
		priceEntity2.setAmount(AMOUNT_PRICE_2);
		priceEntity2.setCurrency(CURRENCY);
		return priceEntity2;
	}
	
	public static Price priceDomain2MotherTest() {
		return new Price(new Brand(BRAND_ID),
				Instant.from(JsonInstantSerializer.FORMATTER.parse(START_DATE_PRICE_2)),
				Instant.from(JsonInstantSerializer.FORMATTER.parse(END_DATE_PRICE_2)),
				2L,
				new Product(PRODUCT_ID),
				PRIORITY_PRICE_2_3_4,
				new MoneyVO(AMOUNT_PRICE_2, Currency.getInstance(CURRENCY)));
	}

	public static PriceEntity price3MotherTest() {
		PriceEntity priceEntity3 = new PriceEntity();
		priceEntity3.setBrand(brandMotherTest());
		priceEntity3.setStartDate(Instant.from(JsonInstantSerializer.FORMATTER.parse(START_DATE_PRICE_3)));
		priceEntity3.setEndDate(Instant.from(JsonInstantSerializer.FORMATTER.parse(END_DATE_PRICE_3)));
		priceEntity3.setPriceList(3L);
		priceEntity3.setProduct(productMotherTest());
		priceEntity3.setPriority(PRIORITY_PRICE_2_3_4);
		priceEntity3.setAmount(AMOUNT_PRICE_3);
		priceEntity3.setCurrency(CURRENCY);
		return priceEntity3;
	}
	
	public static Price priceDomain3MotherTest() {
		return new Price(new Brand(BRAND_ID),
				Instant.from(JsonInstantSerializer.FORMATTER.parse(START_DATE_PRICE_3)),
				Instant.from(JsonInstantSerializer.FORMATTER.parse(END_DATE_PRICE_3)),
				3L,
				new Product(PRODUCT_ID),
				PRIORITY_PRICE_2_3_4,
				new MoneyVO(AMOUNT_PRICE_3, Currency.getInstance(CURRENCY)));
	}

	public static PriceEntity price4MotherTest() {
		PriceEntity priceEntity4 = new PriceEntity();
		priceEntity4.setBrand(brandMotherTest());
		priceEntity4.setStartDate(Instant.from(JsonInstantSerializer.FORMATTER.parse(START_DATE_PRICE_4)));
		priceEntity4.setEndDate(Instant.from(JsonInstantSerializer.FORMATTER.parse(END_DATE_PRICE_4)));
		priceEntity4.setPriceList(4L);
		priceEntity4.setProduct(productMotherTest());
		priceEntity4.setPriority(PRIORITY_PRICE_2_3_4);
		priceEntity4.setAmount(AMOUNT_PRICE_4);
		priceEntity4.setCurrency(CURRENCY);
		return priceEntity4;
	}
	
	public static Price priceDomain4MotherTest() {
		return new Price(new Brand(BRAND_ID),
				Instant.from(JsonInstantSerializer.FORMATTER.parse(START_DATE_PRICE_4)),
				Instant.from(JsonInstantSerializer.FORMATTER.parse(END_DATE_PRICE_4)),
				4L,
				new Product(PRODUCT_ID),
				PRIORITY_PRICE_2_3_4,
				new MoneyVO(AMOUNT_PRICE_4, Currency.getInstance(CURRENCY)));
	}

}
