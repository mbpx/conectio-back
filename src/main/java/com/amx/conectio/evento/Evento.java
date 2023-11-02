package com.amx.conectio.evento;

import java.util.List;

import org.springframework.lang.NonNull;

import com.amx.conectio.imagen.Imagen;
import com.amx.conectio.usuario.Usuario;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Evento {

	@Id
	@GeneratedValue
	private Long id;

	@NonNull
	private String titulo;

	@NonNull
	private String descripcion;

	@OneToOne(cascade = CascadeType.ALL, optional = true)
	@JoinColumn(name = "imagen_id", referencedColumnName = "id")
	private Imagen imagen;
	private double latitud;
	private double longitud;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usuario_id", referencedColumnName = "id")
	@JsonBackReference
	private Usuario usuario;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "usuario_evento", joinColumns = { @JoinColumn(name = "evento_id") }, inverseJoinColumns = {
			@JoinColumn(name = "usuario_id") })
	private List<Usuario> usuariosSuscritos;
}
