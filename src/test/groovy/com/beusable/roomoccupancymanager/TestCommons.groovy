package com.beusable.roomoccupancymanager

import com.beusable.roomoccupancymanager.dto.RoomOccupancyRequestDto

class TestCommons {

    static List CUSTOMER_BUDGETS = [23.0d, 45.0d, 155.0d, 374.0d, 22.0d, 99.99d, 100.0d, 101.0d, 115.0d, 209.0d]

    static RoomOccupancyRequestDto prepareRequest(Integer economyRooms,
                                                  Integer premiumRooms,
                                                  List<Double> customerBudgets) {
        RoomOccupancyRequestDto.builder()
                .economyRooms(economyRooms)
                .premiumRooms(premiumRooms)
                .customersBudgets(customerBudgets)
                .build()
    }
}