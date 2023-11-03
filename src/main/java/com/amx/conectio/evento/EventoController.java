package com.amx.conectio.evento;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/eventos")
@RequiredArgsConstructor
public class EventoController {

	private final EventoService eventoService;
	
	public ResponseEntity<String> sayHello() {
		return ResponseEntity.ok("hello eventos world");
	}

	@GetMapping
	public ResponseEntity<Page<Evento>> getAllEventos(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		Page<Evento> resultado = eventoService.getAllEventos(PageRequest.of(page, size));
		return ResponseEntity.ok(resultado);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Evento> getEventoById(@PathVariable Long id) {
		Optional<Evento> evento = eventoService.getEventoById(id);
		return evento.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	@PostMapping
	public ResponseEntity<Evento> createEvento(@RequestBody Evento evento) {
		Evento createdEvento = eventoService.createEvento(evento);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdEvento);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Evento> updateEvento(@PathVariable Long id, @RequestBody Evento evento) {
		Evento updatedEvento = eventoService.updateEvento(id, evento);
		return ResponseEntity.ok(updatedEvento);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteEvento(@PathVariable Long id) {
		eventoService.deleteEvento(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/cerca")
	public ResponseEntity<Page<Evento>> getEventosCercaDeMi(@RequestBody EventosCercaRequest request) {
		Page<Evento> resultado = eventoService.findNearbyEventosOrderByDistance(request.getLatitud(), request.getLongitud(),
				request.getMaxDistancia(), PageRequest.of(request.getPage(), request.getSize()));
		return ResponseEntity.ok(resultado);
	}
}
