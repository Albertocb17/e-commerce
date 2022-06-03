package com.ecommerce.demo.infrastructure.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.demo.infrastructure.dao.entity.BrandEntity;

@Repository
public interface BrandJpaRepository extends JpaRepository<BrandEntity, Long> {

}
