package org.example;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.List;

import org.example.model.service.ServiceResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SoaOsbTest01ApplicationTests {

	@Autowired
	SoaOsbTest01Application application;
	
	@Test
	public void contextLoads() {
		List<ServiceResponse> service = (List<ServiceResponse>) application.test01(1032, "28-02-2004", "01-03-2004", 2, 3);
		
		// Assert responseItemSize value
		Integer responseItemSize = service.size();
		assertEquals(new Integer(1673), responseItemSize);
		
		// Assert categoryName value
		String categoryName = service.get(1).getRooms().get(1).getCategoryName();
		assertEquals("Luxo", categoryName);
		
		// Assert totalPrice value
		BigDecimal totalPrice = service.get(1).getRooms().get(1).getTotalPrice();
		assertEquals(new BigDecimal("3914.33"), totalPrice);
		
	}

}
