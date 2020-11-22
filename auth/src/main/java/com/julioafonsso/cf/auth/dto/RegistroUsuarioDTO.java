package com.julioafonsso.cf.auth.dto;

import javax.validation.constraints.NotNull;

public class RegistroUsuarioDTO {

	@NotNull
	public String email;

	@NotNull
	public String nome;

	@NotNull
	public String password;

}
