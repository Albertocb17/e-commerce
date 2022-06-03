package com.ecommerce.demo.infrastructure.rest.config;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import org.springframework.boot.jackson.JsonComponent;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

@JsonComponent
public class JsonInstantSerializer {

	private static final String DATE_FORMAT = "yyyy-MM-dd-HH.mm.ss";
	private static final String ZONE_ID = "Europe/Madrid";
	public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT)
			.withZone(ZoneId.of(ZONE_ID));

	public static class InstantSerializer extends JsonSerializer<Instant> {
		@Override
		public void serialize(Instant value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
			gen.writeString(FORMATTER.format(value));
		}
	}

	public static class InstantDeserializer extends JsonDeserializer<Instant> {
		@Override
		public Instant deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
			return Instant.from(FORMATTER.parse(p.getText()));
		}
	}

}
