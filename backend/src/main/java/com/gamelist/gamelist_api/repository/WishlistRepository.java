package com.gamelist.gamelist_api.repository;

import com.gamelist.gamelist_api.model.WishlistItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WishlistRepository extends JpaRepository<WishlistItem, Long> {
    List<WishlistItem> findByPrioridad(String prioridad);
    List<WishlistItem> findByTituloContainingIgnoreCase(String titulo);
    List<WishlistItem> findByPrioridadAndTituloContainingIgnoreCase(String prioridad, String titulo);
}
