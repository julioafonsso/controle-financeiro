package com.julioafonsso.cf.auth.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column
	private String email;
	
	@Column
	private String nome;
	
	@JsonIgnore
	@Column
	private String password;

	@ManyToMany
    @JoinTable(
            name = "user_authority",
            joinColumns = @JoinColumn(name = "username"),
            inverseJoinColumns = @JoinColumn(name = "authority"))
    private Set<Authority> authorities;
	
	protected Usuario() {}
	public Usuario(String email, 
				   String nome, 
				   String password,
				   Set<Authority> authorities) {
		this.email = email;
		this.nome = nome;
		this.password = password;
		this.authorities = authorities;
	}

	public String getEmail() {
		return email;
	}

	public String getNome() {
		return nome;
	}

	public String getPassword() {
		return password;
	}

	public Set<Authority> getAuthorities() {
		return authorities;
	}


}
