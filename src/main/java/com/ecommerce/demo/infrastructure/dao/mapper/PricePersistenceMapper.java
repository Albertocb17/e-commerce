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

	@Mapping(target = "brand.id", source = "brandId")
	@Mapping(target = "product.id", source = "productId")
	@Mapping(target = "startDate.date", source = "startDate")
	@Mapping(target = "endDate.date", source = "endDate")
	@Mapping(target = "money", source = "priceEntity", qualifiedByName = "entityToMoneyVO")
	Price entityToDomain(PriceEntity priceEntity);

	@Named("entityToMoneyVO")
	public default MoneyVO entityToMoneyVO(PriceEntity priceEntity) {
		return new MoneyVO(priceEntity.getAmount(), Currency.getInstance(priceEntity.getCurrency()));
	}

}
