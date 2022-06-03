package com.ecommerce.demo.infrastructure.rest;

import java.time.Instant;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.demo.application.usecase.PriceUseCases;
import com.ecommerce.demo.infrastructure.rest.docs.ApiRestControllerSwagger;
import com.ecommerce.demo.infrastructure.rest.dto.response.PriceResponseDTO;
import com.ecommerce.demo.infrastructure.rest.mapper.PriceDTOMapper;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/api/v01", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ApiRestController implements ApiRestControllerSwagger {

	private final PriceUseCases useCases;

	private final PriceDTOMapper mapper;

	@Override
	@GetMapping("/prices/rate")
	public PriceResponseDTO getPrice(@RequestParam(required = true) Instant date,
			@RequestParam(required = true) Long productId, @RequestParam(required = true) Long brandId) throws Exception {
		return mapper.domainToDto(useCases.findByDateAndProductIdAndBrandId(date, productId, brandId));
	}

}
