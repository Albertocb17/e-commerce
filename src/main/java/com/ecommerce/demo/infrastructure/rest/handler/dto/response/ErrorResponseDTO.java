package com.ecommerce.demo.infrastructure.rest.handler.dto.response;

import java.io.Serializable;
import java.time.Instant;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorResponseDTO implements Serializable {

	private static final long serialVersionUID = 375022888879233827L;

	private int statusCode;

	private String title;

	private Instant timestamp;

	private String message;
}
