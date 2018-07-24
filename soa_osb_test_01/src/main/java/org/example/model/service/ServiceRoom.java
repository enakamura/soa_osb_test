package org.example.model.service;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceRoom {
	private Integer roomID;
	private String categoryName;
	private BigDecimal totalPrice;
	private PriceDetail priceDetail;
}
