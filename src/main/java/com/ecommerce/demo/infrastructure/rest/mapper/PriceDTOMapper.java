package com.ecommerce.demo.infrastructure.rest.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ecommerce.demo.domain.model.Price;
import com.ecommerce.demo.domain.vo.MoneyVO;
import com.ecommerce.demo.infrastructure.rest.dto.response.PriceResponseDTO;

@Mapper(componentModel = "spring")
public interface PriceDTOMapper {

	@Mapping(target = "productId", source = "product.id")
	@Mapping(target = "brandId", source = "brand.id")
	@Mapping(target = "price", source = "money")
	PriceResponseDTO domainToDto(Price priceDomain);

	public default Double moneyVOToPrice(MoneyVO money) {
		return money.getAmount().doubleValue();
	}

}
