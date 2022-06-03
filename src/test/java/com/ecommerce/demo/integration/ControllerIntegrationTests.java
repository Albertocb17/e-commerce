package com.ecommerce.demo.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.ecommerce.demo.PriceMother;
import com.ecommerce.demo.infrastructure.dao.entity.PriceEntity;
import com.ecommerce.demo.infrastructure.rest.dto.response.PriceResponseDTO;
import com.ecommerce.demo.infrastructure.rest.handler.dto.response.ErrorResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class ControllerIntegrationTests {

	private static final String BASE_URL = "http://localhost:8080";

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper mapper;

//	@Autowired
//	private BrandJpaRepository brandRepository;
//
//	@Autowired
//	private ProductJpaRepository productRepository;
//
//	@Autowired
//	private PriceJpaRepository priceRepository;

//	@BeforeEach
//	void initDatabase() {
//		brandRepository.save(PriceMother.brandMotherTest());
//		productRepository.save(PriceMother.productMotherTest());
//		priceRepository.saveAll(PriceMother.priceListMotherTest());
//	}
//
//	@AfterEach
//	void deleteAllDatabase() {
//		priceRepository.deleteAll();
//		brandRepository.deleteAll();
//		productRepository.deleteAll();
//	}

	private static Stream<Arguments> usesCasesOk() {
		return Stream.of(
				arguments(1, "2020-06-14-10.00.00", PriceMother.price1MotherTest()), 
				arguments(2, "2020-06-14-16.00.00", PriceMother.price2MotherTest()),
				arguments(3, "2020-06-14-21.00.00", PriceMother.price1MotherTest()), 
				arguments(4, "2020-06-15-10.00.00", PriceMother.price3MotherTest()),
				arguments(5, "2020-06-16-21.00.00", PriceMother.price4MotherTest()));
	}

	private static Stream<Arguments> usesCasesKo400() {
		return Stream.of(
				arguments("fail", "35455", "2", "date"),
				arguments("2020-06-14-10.00.00", "35455", "fail", "brandId"),
				arguments("2020-06-14-10.00.00", "fail", "2", "productId"));
	}
	
	private static Stream<Arguments> usesCasesKo500() {
		return Stream.of(
				arguments("2000-06-14-10.00.00", "35455", "2"),
				arguments("2020-06-14-10.00.00", "1", "2"),
				arguments("2020-06-14-10.00.00", "35455", "10"));
	}

	@ParameterizedTest
	@MethodSource("usesCasesOk")
	void getPriceTest(int index, String date, PriceEntity priceEntity) throws Exception {
		ResultActions result =	mockMvc.perform(get(BASE_URL + "/api/v01/prices/rate")
						.param("date", date)
						.param("productId", String.valueOf(PriceMother.PRODUCT_ID))
						.param("brandId", String.valueOf(PriceMother.BRAND_ID)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").exists());

		PriceResponseDTO price = mapper.readValue(
				result.andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8), PriceResponseDTO.class);

		assertNotNull(price);
		assertEquals(priceEntity.getProduct().getId(), price.getProductId());
		assertEquals(priceEntity.getBrand().getId(), price.getBrandId());
		assertEquals(priceEntity.getPriceList(), price.getPriceList());
		assertEquals(priceEntity.getStartDate(), price.getStartDate());
		assertEquals(priceEntity.getEndDate(), price.getEndDate());
		assertEquals(priceEntity.getAmount(), price.getPrice());
	}
	
	@ParameterizedTest
	@MethodSource("usesCasesKo400")
	void getPrice400FailTest(String date, String productId, String brandId, String failedParam) throws Exception {
		ResultActions result =	mockMvc.perform(get(BASE_URL + "/api/v01/prices/rate")
						.param("date", date)
						.param("productId", productId)
						.param("brandId", brandId))
				.andExpect(status().is4xxClientError())
				.andExpect(jsonPath("$").exists());

		ErrorResponseDTO errorResponse = mapper.readValue(
				result.andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8), ErrorResponseDTO.class);

		assertNotNull(errorResponse);
		assertEquals(HttpStatus.BAD_REQUEST.value(), errorResponse.getStatusCode());
		assertEquals(HttpStatus.BAD_REQUEST.name(), errorResponse.getTitle());
		String msg = String.format("El parámetro [%s] tiene un formato incorrecto -> Valor recibido [fail]",
				failedParam);
		assertEquals(msg, errorResponse.getMessage());
	}
	
	@ParameterizedTest
	@MethodSource("usesCasesKo500")
	void getPrice500FailTest(String date, String productId, String brandId) throws Exception {
		ResultActions result =	mockMvc.perform(get(BASE_URL + "/api/v01/prices/rate")
						.param("date", date)
						.param("productId", productId)
						.param("brandId", brandId))
				.andExpect(status().is5xxServerError())
				.andExpect(jsonPath("$").exists());

		ErrorResponseDTO errorResponse = mapper.readValue(
				result.andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8), ErrorResponseDTO.class);

		assertNotNull(errorResponse);
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), errorResponse.getStatusCode());
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.name(), errorResponse.getTitle());
		assertEquals("No existe una tarifa de precios aplicable para los datos introducidos.", errorResponse.getMessage());
	}
	
}