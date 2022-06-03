package com.ecommerce.demo.infrastructure.dao.entity;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PRICES")
@Entity
public class PriceEntity {

	@ManyToOne
	@JoinColumn(name = "BRAND_ID", foreignKey = @ForeignKey(name = "BRANDS_FK"), nullable = false)
	private BrandEntity brand;

	@Column(name = "START_DATE", nullable = false)
	private Instant startDate;

	@Column(name = "END_DATE", nullable = false)
	private Instant endDate;

	@Id
	@Column(name = "PRICE_LIST", nullable = false)
	private Long priceList;

	@ManyToOne
	@JoinColumn(name = "PRODUCT_ID", foreignKey = @ForeignKey(name = "PRODUCTS_FK"), nullable = false)
	private ProductEntity product;

	@Column(name = "PRIORITY", nullable = false)
	private Integer priority;

	@Column(name = "PRICE", nullable = false)
	private Double amount;

	@Column(name = "CURR", nullable = false)
	private String currency;

}
