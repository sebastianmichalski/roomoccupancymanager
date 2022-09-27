package com.beusable.roomoccupancymanager.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RoomOccupancyResponseDto {
    String economyUsage;
    String premiumUsage;
}