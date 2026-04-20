package com.gamelist.gamelist_api.repository;

import com.gamelist.gamelist_api.model.Plataforma;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlataformaRepository extends JpaRepository<Plataforma, Long> {
    boolean existsByNombreIgnoreCase(String nombre);
}
