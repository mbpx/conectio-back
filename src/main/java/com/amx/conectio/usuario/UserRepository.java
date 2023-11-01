package com.amx.conectio.usuario;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Usuario, Integer> {
	
	Optional<Usuario> findByUsername(String username);
	
	Optional<Usuario> findByEmail(String email);

}
