package com.julioafonsso.cf.carteira.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Configuration
public class CarteiraController {

	@Autowired
	private ServletWebServerApplicationContext webServerAppCtxt;
	
	@GetMapping
	public ResponseEntity<String> getCarteira(){
		
		String resposta = "Respondendo na porta " + webServerAppCtxt.getWebServer().getPort();
		return new ResponseEntity<String>(resposta	, HttpStatus.OK);
	}
	
}
