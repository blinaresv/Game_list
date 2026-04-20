package com.gamelist.gamelist_api.service;

import com.gamelist.gamelist_api.exception.ResourceNotFoundException;
import com.gamelist.gamelist_api.model.Videojuego;
import com.gamelist.gamelist_api.repository.VideojuegoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideojuegoService {

    private final VideojuegoRepository videojuegoRepository;

    public VideojuegoService(VideojuegoRepository videojuegoRepository) {
        this.videojuegoRepository = videojuegoRepository;
    }

    public List<Videojuego> listarVideojuegos() {
        return videojuegoRepository.findAll();
    }

    public Videojuego crearVideojuego(Videojuego videojuego) {
        return videojuegoRepository.save(videojuego);
    }

    public Videojuego obtenerPorId(Long id) {
        return videojuegoRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Videojuego no encontrado con id: " + id));
    }

    public Videojuego actualizarVideojuego(Long id, Videojuego nuevo) {
        Videojuego existente = obtenerPorId(id);
        existente.setTitulo(nuevo.getTitulo());
        existente.setPlataforma(nuevo.getPlataforma());
        existente.setAnio(nuevo.getAnio());
        existente.setEstado(nuevo.getEstado());
        if (nuevo.getCategoria() != null) {
            existente.setCategoria(nuevo.getCategoria());
        }
        return videojuegoRepository.save(existente);
    }

    public void eliminarVideojuego(Long id) {
        obtenerPorId(id);
        videojuegoRepository.deleteById(id);
    }

    public List<Videojuego> listarPorCategoria(Long categoriaId) {
        return videojuegoRepository.findByCategoriaId(categoriaId);
    }
}
