package com.beusable.roomoccupancymanager.service

import com.beusable.roomoccupancymanager.dto.RoomOccupancyRequestDto
import com.beusable.roomoccupancymanager.engine.BookingEngine
import spock.lang.Specification

class RoomOccupancyManagerServiceTest extends Specification {

    BookingEngine bookingEngine = Mock(BookingEngine)
    RoomOccupancyManagerService service = new RoomOccupancyManagerService(bookingEngine)

    def "should call bookRooms from booking engine"() {
        given:
        def request = GroovyMock(RoomOccupancyRequestDto)
        when:
        service.bookRooms(request)
        then:
        1 * bookingEngine.bookRooms(request)
    }
}