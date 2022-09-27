package com.beusable.roomoccupancymanager.engine

import static com.beusable.roomoccupancymanager.TestCommons.CUSTOMER_BUDGETS

import com.beusable.roomoccupancymanager.TestCommons
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification
import spock.lang.Unroll

@SpringBootTest
class BookingEngineTest extends Specification {

    @Autowired
    BookingEngine bookingEngine

    static String messagePattern = "Usage %s: %d (EUR %.2f)"
    static String PREMIUM = "Premium"
    static String ECONOMY = "Economy"

    @Unroll
    def "test bookRooms"() {
        expect:
        def responseDto = bookingEngine.bookRooms(TestCommons.prepareRequest(economyRooms, premiumRooms, CUSTOMER_BUDGETS))
        responseDto.economyUsage == String.format(messagePattern, ECONOMY, economyUsage, economyTotal)
        responseDto.premiumUsage == String.format(messagePattern, PREMIUM, premiumUsage, premiumTotal)

        where:
        economyRooms | premiumRooms || economyUsage | economyTotal | premiumUsage | premiumTotal
        3            | 3            || 3            | 167.99d      | 3            | 738d
        5            | 7            || 4            | 189.99d      | 6            | 1054d
        7            | 2            || 4            | 189.99d      | 2            | 583d
        1            | 7            || 1            | 45d          | 7            | 1153.99d
    }
}