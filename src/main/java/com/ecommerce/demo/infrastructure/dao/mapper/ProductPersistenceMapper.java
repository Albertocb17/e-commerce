package com.ecommerce.demo.infrastructure.dao.mapper;

import org.mapstruct.Mapper;

import com.ecommerce.demo.domain.model.Product;
import com.ecommerce.demo.infrastructure.dao.entity.ProductEntity;

@Mapper(componentModel = "spring")
public interface ProductPersistenceMapper {

	Product entityToDomain(ProductEntity priceEntity);

}
