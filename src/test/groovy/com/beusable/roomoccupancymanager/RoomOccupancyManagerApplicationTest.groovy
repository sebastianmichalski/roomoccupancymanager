package com.beusable.roomoccupancymanager

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.context.ApplicationContext
import spock.lang.Specification

@SpringBootTest(webEnvironment = WebEnvironment.NONE, classes = RoomOccupancyManagerApplication)
class RoomOccupancyManagerApplicationTest extends Specification {

    @Autowired
    ApplicationContext context

    def "context is loaded"() {
        expect:
        context
    }
}
