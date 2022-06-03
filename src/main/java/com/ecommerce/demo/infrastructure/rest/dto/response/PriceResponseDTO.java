package com.ecommerce.demo.infrastructure.rest.dto.response;

import java.io.Serializable;
import java.time.Instant;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PriceResponseDTO implements Serializable {

	private static final long serialVersionUID = 783209483560315581L;

	private Long productId;

	private Long brandId;

	private Long priceList;

	private Instant startDate;

	private Instant endDate;

	private Double price;

}
