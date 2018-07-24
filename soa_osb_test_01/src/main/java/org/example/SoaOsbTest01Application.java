package org.example;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.example.model.parter.Hotel;
import org.example.model.parter.Room;
import org.example.model.service.PriceDetail;
import org.example.model.service.ServiceResponse;
import org.example.model.service.ServiceRoom;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
public class SoaOsbTest01Application {

	@Value("${partnerUrl}")
	private String partnerUrl;
	
	public static void main(String[] args) {
		SpringApplication.run(SoaOsbTest01Application.class, args);
	}

	@GetMapping("/test01")
	public Object test01(@RequestParam("cityCode") Integer cityCode, 
			@RequestParam("checkin") String checkin, 
			@RequestParam("checkout") String checkout, 
			@RequestParam("adultQtd") Integer adultQuantity, 
			@RequestParam("childQtd") Integer childQuantity) {
		
		LocalDate parsedcheckin = LocalDate.parse(checkin, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		LocalDate parsedcheckout = LocalDate.parse(checkout, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		Long days = ChronoUnit.DAYS.between(parsedcheckin, parsedcheckout);
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Hotel[]> response = restTemplate.getForEntity(partnerUrl, Hotel[].class, cityCode);
		
		List<ServiceResponse> serviceResponseList = new ArrayList<ServiceResponse>();
		for(Hotel hotel : response.getBody()) {
			List<ServiceRoom> serviceRoomList = new ArrayList<ServiceRoom>();
			ServiceResponse serviceResponse = ServiceResponse.builder()
					.id(hotel.getId())
					.cityName(hotel.getCityName())
					.rooms(serviceRoomList)
					.build();
			for(Room room : hotel.getRooms()) {
				ServiceRoom serviceRoom = ServiceRoom.builder()
						.roomID(room.getRoomID())
						.categoryName(room.getCategoryName())
						.totalPrice(calculateTotalPrice(room.getPrice().getAdult(), room.getPrice().getChild(), adultQuantity, childQuantity))
						.priceDetail(PriceDetail.builder()
								.pricePerDayAdult(room.getPrice().getAdult())
								.pricePerDayChild(room.getPrice().getChild())
								.build())
						.build();
				serviceRoomList.add(serviceRoom);
			}
			serviceResponseList.add(serviceResponse);
		}
		
		return serviceResponseList;
	}
		
	private BigDecimal calculateTotalPrice(BigDecimal adultPrice, BigDecimal childPrice, Integer adultQuantity, Integer childQuantity) {
		// Calculo do valor para todos os dias
		BigDecimal adultTotalPrice = adultPrice.multiply(new BigDecimal(adultQuantity));
		BigDecimal childTotalPrice = childPrice.multiply(new BigDecimal(childQuantity));
		
		// Calculo do valor com comissao
		BigDecimal adultTotalPriceCommission = adultTotalPrice.divide(new BigDecimal("0.7"), 2, RoundingMode.HALF_EVEN);
		BigDecimal childTotalPriceCommission = childTotalPrice.divide(new BigDecimal("0.7"), 2, RoundingMode.HALF_EVEN);
		
		// Soma total
		return adultTotalPriceCommission.add(childTotalPriceCommission);
	}
}
