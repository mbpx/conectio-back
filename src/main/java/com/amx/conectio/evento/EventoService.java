package com.amx.conectio.evento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventoService {

    private EventoRepository eventoRepository;

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
}


