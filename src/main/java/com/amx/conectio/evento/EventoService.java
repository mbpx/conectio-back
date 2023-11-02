package com.amx.conectio.evento;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.amx.conectio.usuario.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EventoService {

    private final EventoRepository eventoRepository;
    private final UserRepository userRepository;
    
    public Page<Evento> getAllEventos(Pageable pageable) {
        return eventoRepository.findAll(pageable);
    }

    public Optional<Evento> getEventoById(Long id) {
        return eventoRepository.findById(id);
    }

    public Evento createEvento(Evento evento) {
        return eventoRepository.save(evento);
    }

    public Evento updateEvento(Long id, Evento evento) {
        if (eventoRepository.existsById(id)) {
            evento.setId(id);
            return eventoRepository.save(evento);
        } else {
            throw new RuntimeException("Evento with ID " + id + " not found.");
        }
    }

    public void deleteEvento(Long id) {
        eventoRepository.deleteById(id);
    }
    
    public Page<Evento> findNearbyEventosOrderByDistance(double latitude, double longitude, double maxDistance, Pageable pageable) {
        return eventoRepository.findNearbyEventosOrderByDistance(latitude, longitude, maxDistance, pageable);
    }
}


