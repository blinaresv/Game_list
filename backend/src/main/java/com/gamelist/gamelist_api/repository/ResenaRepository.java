package com.gamelist.gamelist_api.repository;

import com.gamelist.gamelist_api.model.Resena;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResenaRepository extends JpaRepository<Resena, Long> {
    List<Resena> findByVideojuegoId(Long videojuegoId);
}
