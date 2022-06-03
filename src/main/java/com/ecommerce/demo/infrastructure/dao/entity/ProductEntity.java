package com.ecommerce.demo.infrastructure.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PRODUCTS")
@Entity
public class ProductEntity {

	@Id
	@Column(name = "ID", nullable = false)
	private Long id;

}
