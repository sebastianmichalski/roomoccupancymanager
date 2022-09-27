package com.beusable.roomoccupancymanager.service;

import com.beusable.roomoccupancymanager.dto.RoomOccupancyRequestDto;
import com.beusable.roomoccupancymanager.dto.RoomOccupancyResponseDto;
import com.beusable.roomoccupancymanager.engine.BookingEngine;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomOccupancyManagerService {

	private final BookingEngine bookingEngine;

	public RoomOccupancyResponseDto bookRooms(RoomOccupancyRequestDto request) {
		return bookingEngine.bookRooms(request);
	}
}