package com.beusable.roomoccupancymanager.engine;

import com.beusable.roomoccupancymanager.dto.RoomOccupancyRequestDto;
import com.beusable.roomoccupancymanager.dto.RoomOccupancyResponseDto;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class BookingEngine {

	@Value("${price_threshold}")
	private Double priceThreshold;

	@Value("${message_pattern}")
	private String messagePattern;

	public RoomOccupancyResponseDto bookRooms(RoomOccupancyRequestDto request) {
		var budgetsMap = request.getCustomersBudgets().stream()
			.sorted(Comparator.reverseOrder())
			.collect(Collectors.groupingBy(price -> price < priceThreshold));

		var budgetsBelowThreshold = budgetsMap.get(true);
		var budgetsAboveThreshold = budgetsMap.get(false);
		var freeEconomyRooms = request.getEconomyRooms();
		var freePremiumRooms = request.getPremiumRooms();
		var resultEconomy = new ArrayList<Double>();
		var resultPremium = new ArrayList<Double>();
		var premiumRoomsAdded = 0;
		var economyRoomsAddedToPremium = 0;

		if(budgetsAboveThreshold != null) {
			for (int i = 0; i < freePremiumRooms && budgetsAboveThreshold.size() > i; i++) {
				resultPremium.add(budgetsAboveThreshold.get(i));
				premiumRoomsAdded++;
			}
		}

		if (freePremiumRooms - premiumRoomsAdded > 0 && freeEconomyRooms < budgetsBelowThreshold.size()) {
			for (int i = premiumRoomsAdded, j = 0; i < freePremiumRooms && j < budgetsBelowThreshold.size(); i++, j++) {
				resultPremium.add(budgetsBelowThreshold.get(j));
				economyRoomsAddedToPremium++;
				premiumRoomsAdded++;
			}
		}

		for (int i = economyRoomsAddedToPremium; i < budgetsBelowThreshold.size() && freeEconomyRooms > 0; i++, freeEconomyRooms--) {
			resultEconomy.add(budgetsBelowThreshold.get(i));
		}

		return RoomOccupancyResponseDto.builder()
			.economyUsage(String.format(messagePattern, "Economy", resultEconomy.size(), resultEconomy.stream().reduce(0.0d, Double::sum)))
			.premiumUsage(String.format(messagePattern, "Premium", resultPremium.size(), resultPremium.stream().reduce(0.0d, Double::sum)))
			.build();
	}
}