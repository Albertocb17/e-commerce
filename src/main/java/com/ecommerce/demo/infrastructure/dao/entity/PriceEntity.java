package com.ecommerce.demo.infrastructure.dao.entity;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name = "PRICES")
@Entity
public class PriceEntity {

	@Column(name = "BRAND_ID", nullable = false)
	private Long brandId;

	@Column(name = "START_DATE", nullable = false)
	private Instant startDate;

	@Column(name = "END_DATE", nullable = false)
	private Instant endDate;

	@Id
	@Column(name = "PRICE_LIST", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long priceList;

	@Column(name = "PRODUCT_ID", nullable = false)
	private Long productId;

	@Column(name = "PRIORITY", nullable = false)
	private Integer priority;

	@Column(name = "PRICE", nullable = false)
	private Double amount;

	@Column(name = "CURR", nullable = false)
	private String currency;

}
