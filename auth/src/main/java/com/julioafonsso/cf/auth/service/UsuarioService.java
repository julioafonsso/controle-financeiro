package com.julioafonsso.cf.auth.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.julioafonsso.cf.auth.dto.RegistroUsuarioDTO;
import com.julioafonsso.cf.auth.model.Authority;
import com.julioafonsso.cf.auth.model.AuthorityRepository;
import com.julioafonsso.cf.auth.model.Usuario;
import com.julioafonsso.cf.auth.model.UsuarioRepository;


@Service
@Transactional
public class UsuarioService implements UserDetailsService {

	private UsuarioRepository repositorio;
	private AuthorityRepository authorityRepositorio;

	public UsuarioService(UsuarioRepository repositorio, AuthorityRepository authorityRepositorio) {
		this.repositorio = repositorio;
		this.authorityRepositorio = authorityRepositorio;
	}

	public Usuario criarUsuario(RegistroUsuarioDTO usuarioDTO) {

		Set<Authority> autorizacoes = new HashSet<>(authorityRepositorio.findAll());

		Usuario usuario = new Usuario(usuarioDTO.email, usuarioDTO.nome, criptografaSenha(usuarioDTO.password),
				autorizacoes);

		this.repositorio.save(usuario);

		return usuario;
	}

	public String criptografaSenha(String password) {

		return passwordEncoder().encode(password);

	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return repositorio.findByEmail(username)
				.map(usuario -> new User(usuario.getEmail(), usuario.getPassword(), getGrantedAuthorities(usuario)))
				.orElseThrow(() -> new UsernameNotFoundException("Usuario  " + username + " não encontrado!"));
	}

	private Collection<GrantedAuthority> getGrantedAuthorities(Usuario user) {
		Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		for (Authority authority : user.getAuthorities()) {
			GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(authority.getName());
			grantedAuthorities.add(grantedAuthority);
		}

		return grantedAuthorities;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}