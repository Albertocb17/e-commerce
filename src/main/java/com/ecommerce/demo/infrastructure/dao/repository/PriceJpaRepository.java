package com.ecommerce.demo.infrastructure.dao.repository;

import java.time.Instant;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.demo.infrastructure.dao.entity.PriceEntity;

@Repository
public interface PriceJpaRepository extends JpaRepository<PriceEntity, Long> {

	List<PriceEntity> findByStartDateBeforeAndEndDateAfterAndProductIdAndBrandId(Instant startDate, Instant endDate,
			Long productId, Long brandId);

}
