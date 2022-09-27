package com.beusable.roomoccupancymanager.controller

import com.beusable.roomoccupancymanager.TestCommons
import com.beusable.roomoccupancymanager.configuration.ApiExceptionHandler
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.util.UriComponentsBuilder
import spock.lang.Specification
import spock.lang.Unroll

@SpringBootTest
class RoomOccupancyManagerControllerTest extends Specification {

    MockMvc mockMvc
    @Autowired
    RoomOccupancyManagerController controller
    @Autowired
    ObjectMapper objectMapper

    def setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).setControllerAdvice(new ApiExceptionHandler()).build()
    }

    @Unroll
    def "should call bookRooms endpoint"() {
        given:
        def HTTP_URL = "http://foo.bar"
        def request = TestCommons.prepareRequest(economyRooms, premiumRooms, customerBudgets)

        expect:
        mockMvc.perform(
                MockMvcRequestBuilders.post(UriComponentsBuilder.fromHttpUrl(HTTP_URL).path("/api/manager/bookrooms")
                        .build().toUri())
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status)
                .andReturn()

        where:
        economyRooms | premiumRooms | customerBudgets || status
        -1           | 0            | [10.0d]         || MockMvcResultMatchers.status().isBadRequest()
        0            | -1           | [10.0d]         || MockMvcResultMatchers.status().isBadRequest()
        0            | 1            | [10.0d]         || MockMvcResultMatchers.status().isOk()
        0            | 1            | []              || MockMvcResultMatchers.status().isBadRequest()
        0            | 1            | [-100.0d]       || MockMvcResultMatchers.status().isBadRequest()
    }
}