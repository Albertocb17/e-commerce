package com.ecommerce.demo.domain.vo;

import java.time.Instant;

import lombok.NonNull;
import lombok.Value;

@Value
public class PriceDateVO {

	@NonNull
	private Instant date;

}
