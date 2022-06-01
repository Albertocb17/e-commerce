package com.ecommerce.demo.domain.vo;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;

import lombok.NonNull;
import lombok.Value;

@Value
public class MoneyVO {

	private static final int DECIMALS = 2;
	private static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_UP;

	@NonNull
	private BigDecimal amount;

	@NonNull
	private Currency currency;

	public MoneyVO(BigDecimal amount, Currency currency) {
		this.amount = amount;
		this.currency = currency;
	}

	public MoneyVO(long amount, Currency currency) {
		this.amount = BigDecimal.valueOf(amount).setScale(DECIMALS, ROUNDING_MODE);
		this.currency = currency;
	}

	public MoneyVO(double amount, Currency currency) {
		this.amount = BigDecimal.valueOf(amount).setScale(DECIMALS, ROUNDING_MODE);
		this.currency = currency;
	}

	public static boolean isPositive(MoneyVO money) {
		return money.getAmount().compareTo(BigDecimal.ZERO) > 0;
	}

	public static boolean isZero(MoneyVO money) {
		return money.getAmount().compareTo(BigDecimal.ZERO) == 0;
	}

	public static boolean isNegative(MoneyVO money) {
		return money.getAmount().compareTo(BigDecimal.ZERO) < 0;
	}

	public MoneyVO add(MoneyVO otherMoney) throws Exception {
		isSameCurrency(otherMoney);

		return new MoneyVO(amount.add(otherMoney.amount), currency);
	}

	public MoneyVO subtract(MoneyVO otherMoney) throws Exception {
		isSameCurrency(otherMoney);

		return new MoneyVO(amount.subtract(otherMoney.amount), currency);
	}

	public MoneyVO multiply(MoneyVO otherMoney) throws Exception {
		isSameCurrency(otherMoney);

		return new MoneyVO(amount.multiply(otherMoney.amount).setScale(DECIMALS, ROUNDING_MODE), currency);
	}

	private void isSameCurrency(MoneyVO otherMoney) throws Exception {
		if (!currency.equals(otherMoney.currency)) {
			throw new Exception("Error en la operación monetaria, la divisa no coincide");
		}
	}

}
