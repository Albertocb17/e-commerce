package com.ecommerce.demo.infrastructure.rest.config;

import java.time.Instant;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class InstantFormattedConverter implements Converter<String, Instant> {

	@Override
	public Instant convert(String value) {
		return Instant.from(JsonInstantSerializer.FORMATTER.parse(value));
	}
}
