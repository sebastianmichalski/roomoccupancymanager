package com.beusable.roomoccupancymanager.dto;

import java.util.List;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RoomOccupancyRequestDto {
    @Min(value = 0, message = "The value must be positive")
    Integer economyRooms;
    @Min(value = 0, message = "The value must be positive")
    Integer premiumRooms;
    @NotEmpty(message = "Customer budgets are empty")
    List<@Min(value = 0, message = "The value must be positive") Double> customersBudgets;
}