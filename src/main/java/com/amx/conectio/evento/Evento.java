package com.amx.conectio.evento;

import java.util.List;

import org.springframework.lang.NonNull;

import com.amx.conectio.imagen.Imagen;
import com.amx.conectio.usuario.Usuario;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Evento {

	@Id
	private Long id;
	
	@NonNull
	private String titulo;
	
	@NonNull
	private String descripcion;
	
	private Imagen imagen;
	
	private double[] coordenadas;
	
	@OneToOne
	private Usuario usuario;
	
	@OneToMany
	private List<Usuario> usuariosSuscritos;
}
