package com.amx.conectio.imagen;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ImagenRepository extends JpaRepository<Imagen, Long> {
    Optional<Imagen> findByName(String name);
}