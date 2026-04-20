package com.gamelist.gamelist_api.service;

import com.gamelist.gamelist_api.exception.ResourceNotFoundException;
import com.gamelist.gamelist_api.model.WishlistItem;
import com.gamelist.gamelist_api.repository.WishlistRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishlistService {

    private final WishlistRepository wishlistRepository;

    public WishlistService(WishlistRepository wishlistRepository) {
        this.wishlistRepository = wishlistRepository;
    }

    public List<WishlistItem> listar(String prioridad, String titulo) {
        boolean p = prioridad != null && !prioridad.isBlank();
        boolean t = titulo != null && !titulo.isBlank();

        if (p && t) return wishlistRepository.findByPrioridadAndTituloContainingIgnoreCase(prioridad, titulo);
        if (p)      return wishlistRepository.findByPrioridad(prioridad);
        if (t)      return wishlistRepository.findByTituloContainingIgnoreCase(titulo);
        return wishlistRepository.findAll();
    }

    public WishlistItem obtenerPorId(Long id) {
        return wishlistRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Item de wishlist no encontrado con id: " + id));
    }

    public WishlistItem crear(WishlistItem item) {
        return wishlistRepository.save(item);
    }

    public WishlistItem actualizar(Long id, WishlistItem datos) {
        WishlistItem existente = obtenerPorId(id);
        existente.setTitulo(datos.getTitulo());
        existente.setPrioridad(datos.getPrioridad());
        existente.setNotas(datos.getNotas());
        existente.setPlataforma(datos.getPlataforma());
        existente.setCategoria(datos.getCategoria());
        return wishlistRepository.save(existente);
    }

    public void eliminar(Long id) {
        obtenerPorId(id);
        wishlistRepository.deleteById(id);
    }
}
