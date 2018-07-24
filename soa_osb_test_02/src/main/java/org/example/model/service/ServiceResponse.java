package org.example.model.service;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceResponse {
	private Integer id;
	private String cityName;
	private List<ServiceRoom> rooms;
}
