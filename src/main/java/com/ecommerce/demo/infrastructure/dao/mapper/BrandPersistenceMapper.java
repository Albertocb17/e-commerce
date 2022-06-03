package com.ecommerce.demo.infrastructure.dao.mapper;

import org.mapstruct.Mapper;

import com.ecommerce.demo.domain.model.Brand;
import com.ecommerce.demo.infrastructure.dao.entity.BrandEntity;

@Mapper(componentModel = "spring")
public interface BrandPersistenceMapper {

	Brand entityToDomain(BrandEntity priceEntity);

}
