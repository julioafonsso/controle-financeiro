package com.julioafonsso.cf.auth.controllers;

import java.security.Principal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.julioafonsso.cf.auth.dto.RegistroUsuarioDTO;
import com.julioafonsso.cf.auth.model.Usuario;
import com.julioafonsso.cf.auth.service.UsuarioService;

@RestController
public class UsuarioController {

	private UsuarioService usuarioService;
	
	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	
	@PostMapping(value = "usuario")
	public ResponseEntity<Usuario> criarUsuario(@RequestBody RegistroUsuarioDTO usuario){
		
		Usuario usuarioCriado = this.usuarioService.criarUsuario(usuario);
		
		return new ResponseEntity<Usuario>(usuarioCriado, HttpStatus.CREATED);
	}
	
	
	@GetMapping(value = "usuario")
	public Principal getCurrentLoggedInUser(Principal user) {
        return user;
    }
	
	@GetMapping(value = "teste")
	public String get() {
        return "Servico Auth funcionando!!" ;
    }
	
	@GetMapping(value = "teste-bloqueado")
	public String getBloqueado() {
        return "Servico Auth funcionando!!" ;
    }
	
}