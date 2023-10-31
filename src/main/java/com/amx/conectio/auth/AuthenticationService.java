package com.amx.conectio.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.amx.conectio.config.JwtService;
import com.amx.conectio.user.Rol;
import com.amx.conectio.user.UserRepository;
import com.amx.conectio.user.Usuario;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

	private final UserRepository repository;

	private final PasswordEncoder passwordEncoder;

	private final JwtService jwtService;

	private final AuthenticationManager authenticationManager;

	public AuthenticationResponse register(RegisterRequest request) {

		var usuario = Usuario.builder()
				.username(request.getUsername())
				.nombre(request.getNombre()).apellidos(request.getApellidos())
				.email(request.getEmail()).password(passwordEncoder.encode(request.getPassword())).rol(Rol.USER)
				.build();
		
		System.out.println(usuario);

		repository.save(usuario);
		var jwtToken = jwtService.generateToken(usuario);
		return AuthenticationResponse.builder().token(jwtToken).build();

	}

	public AuthenticationResponse authenticate(AuthenticationRequest request) {
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

		var usuario = repository.findByUsername(request.getUsername()).orElseThrow();
		var jwtToken = jwtService.generateToken(usuario);
		return AuthenticationResponse.builder().token(jwtToken).build();
	}

}
