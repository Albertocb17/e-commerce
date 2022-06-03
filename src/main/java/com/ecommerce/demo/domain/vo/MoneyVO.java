package com.ecommerce.demo.domain.vo;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class MoneyVO {

	private static final int DECIMALS = 2;
	private static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_UP;

	@NonNull
	private BigDecimal amount;

	@NonNull
	private Currency currency;

	public MoneyVO(double amount, Currency currency) {
		this.amount = BigDecimal.valueOf(amount).setScale(DECIMALS, ROUNDING_MODE);
		this.currency = currency;
	}

}
