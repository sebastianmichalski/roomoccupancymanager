package com.beusable.roomoccupancymanager.controller;

import com.beusable.roomoccupancymanager.dto.RoomOccupancyRequestDto;
import com.beusable.roomoccupancymanager.dto.RoomOccupancyResponseDto;
import com.beusable.roomoccupancymanager.service.RoomOccupancyManagerService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/manager")
@RequiredArgsConstructor
public class RoomOccupancyManagerController {

	private final RoomOccupancyManagerService service;

	@PostMapping("/bookrooms")
	public ResponseEntity<RoomOccupancyResponseDto> bookRooms(@Valid @RequestBody RoomOccupancyRequestDto request) {
		return ResponseEntity.ok(service.bookRooms(request));
	}
}