package com.julioafonsso.cf.auth.model;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	@Query("SELECT u FROM Usuario u WHERE LOWER(u.email) = LOWER(:email)")
	Optional<Usuario> findByEmail(@Param("email") String email);
}
