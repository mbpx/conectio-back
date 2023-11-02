package com.amx.conectio.evento;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventosCercaRequest {
	
	private double latitud;
	private double longitud;
	private double maxDistancia;
	private int page;
	private int size;

}
