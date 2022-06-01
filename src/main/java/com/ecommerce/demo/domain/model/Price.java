package com.ecommerce.demo.domain.model;

import com.ecommerce.demo.domain.vo.MoneyVO;
import com.ecommerce.demo.domain.vo.PriceDateVO;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Price {

	private Brand brand;

	private PriceDateVO startDate;

	private PriceDateVO endDate;

	private Long priceList;

	private Product product;

	private Integer priority;

	private MoneyVO money;

}
