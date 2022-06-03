package com.ecommerce.demo.infrastructure.dao.mapper;

import java.util.Currency;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import com.ecommerce.demo.domain.model.Price;
import com.ecommerce.demo.domain.vo.MoneyVO;
import com.ecommerce.demo.infrastructure.dao.entity.PriceEntity;

@Mapper(componentModel = "spring")
public interface PricePersistenceMapper {

	@Mapping(target = "money", source = "priceEntity", qualifiedByName = "entityToMoneyVO")
	Price entityToDomain(PriceEntity priceEntity);

	@Named("entityToMoneyVO")
	public default MoneyVO entityToMoneyVO(PriceEntity priceEntity) {
		return new MoneyVO(priceEntity.getAmount(), Currency.getInstance(priceEntity.getCurrency()));
	}

}
