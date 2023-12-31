package com.brunostaine.api.gestor.financeiro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@SpringBootApplication
@RestController
public class ApiGestorFinanceiroApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGestorFinanceiroApplication.class, args);
	}
	@GetMapping("/")
	public String index(){
		return "SERVER ONLINE";
	}
}
