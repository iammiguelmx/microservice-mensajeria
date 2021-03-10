package com.test.tarificador.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/mensajeria")
public class TestController {

	@GetMapping("/test")
	public String hola() {
		return "Hola desde Test Controller";
	}
	
}
