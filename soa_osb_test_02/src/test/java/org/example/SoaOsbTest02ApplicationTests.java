package org.example;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.example.model.service.ServiceResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SoaOsbTest02ApplicationTests {

	@Autowired
	SoaOsbTest02Application application;
	
	@Test
	public void contextLoads() {
		ServiceResponse service = (ServiceResponse) application.test02(1, "28-02-2004", "01-03-2004", 2, 3);
				
		// Assert categoryName value
		String categoryName = service.getRooms().get(0).getCategoryName();
		assertEquals("Standard", categoryName);
		
		// Assert totalPrice value
		BigDecimal totalPrice = service.getRooms().get(0).getTotalPrice();
		assertEquals(new BigDecimal("7558.44"), totalPrice);
		
	}

}
