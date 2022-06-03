package com.ecommerce.demo.infrastructure.rest.docs;

import java.time.Instant;

import com.ecommerce.demo.infrastructure.rest.dto.response.PriceResponseDTO;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@OpenAPIDefinition(info = @Info(title = "Pricing API", description = "Pricing api developed on hexagonal architecture applying domain driven design", version = "v01"))
public interface ApiRestControllerSwagger {

	@Operation(summary = "Get price", description = "Get price from a product, a brand and a given date", parameters = {
			@Parameter(in = ParameterIn.QUERY, description = "Pricing date format (yyyy-MM-dd-HH.mm.ss)", name = "date", content = @Content(schema = @Schema(type = "string", format = "yyyy-MM-dd-HH.mm.ss", example = "2020-06-14-10.00.00"))),
			@Parameter(in = ParameterIn.QUERY, description = "Product identification", name = "productId", content = @Content(schema = @Schema(type = "string"))),
			@Parameter(in = ParameterIn.QUERY, description = "Brand identification", name = "brandId", content = @Content(schema = @Schema(type = "string"))) })
	PriceResponseDTO getPrice(Instant date, Long productId, Long brandId) throws Exception;

}
