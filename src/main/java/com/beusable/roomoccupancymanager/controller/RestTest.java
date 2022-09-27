package com.beusable.roomoccupancymanager.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestTest {

	@GetMapping
	public String testEndpoint(){
		return "Hello world!";
	}

}
