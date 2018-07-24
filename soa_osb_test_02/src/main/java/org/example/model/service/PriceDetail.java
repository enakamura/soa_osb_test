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
public class PriceDetail {
	private BigDecimal pricePerDayAdult;
	private BigDecimal pricePerDayChild;
}
