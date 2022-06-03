package com.ecommerce.demo.domain.model;

import java.time.Instant;

import com.ecommerce.demo.domain.vo.MoneyVO;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Price {

	private Brand brand;

	private Instant startDate;

	private Instant endDate;

	private Long priceList;

	private Product product;

	private Integer priority;

	private MoneyVO money;

}
