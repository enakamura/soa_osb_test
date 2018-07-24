package org.example.model.parter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Room {
	private Integer roomID;
	private String categoryName;
	private Price price;
}
