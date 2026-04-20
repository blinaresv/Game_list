package com.gamelist.gamelist_api.service;

import com.gamelist.gamelist_api.exception.ResourceNotFoundException;
import com.gamelist.gamelist_api.model.Videojuego;
import com.gamelist.gamelist_api.repository.VideojuegoRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class VideojuegoService {

    private final VideojuegoRepository videojuegoRepository;

    public VideojuegoService(VideojuegoRepository videojuegoRepository) {
        this.videojuegoRepository = videojuegoRepository;
    }

    public List<Videojuego> listarVideojuegos() {
        return videojuegoRepository.findAll();
    }

    public List<Videojuego> buscarConFiltros(String titulo, String estado, Long categoriaId, Long plataformaId) {
        boolean t = titulo != null && !titulo.isBlank();
        boolean e = estado != null && !estado.isBlank();
        boolean c = categoriaId != null;
        boolean p = plataformaId != null;

        if (t && e && c && p) return videojuegoRepository.findByTituloContainingIgnoreCaseAndEstadoAndCategoriaIdAndPlataformaId(titulo, estado, categoriaId, plataformaId);
        if (t && e && c)      return videojuegoRepository.findByTituloContainingIgnoreCaseAndEstadoAndCategoriaId(titulo, estado, categoriaId);
        if (t && e && p)      return videojuegoRepository.findByTituloContainingIgnoreCaseAndEstadoAndPlataformaId(titulo, estado, plataformaId);
        if (t && e)           return videojuegoRepository.findByTituloContainingIgnoreCaseAndEstado(titulo, estado);
        if (t && c && p)      return videojuegoRepository.findByTituloContainingIgnoreCaseAndCategoriaIdAndPlataformaId(titulo, categoriaId, plataformaId);
        if (t && c)           return videojuegoRepository.findByTituloContainingIgnoreCaseAndCategoriaId(titulo, categoriaId);
        if (t && p)           return videojuegoRepository.findByTituloContainingIgnoreCaseAndPlataformaId(titulo, plataformaId);
        if (t)                return videojuegoRepository.findByTituloContainingIgnoreCase(titulo);
        if (e && c && p)      return videojuegoRepository.findByEstadoAndCategoriaIdAndPlataformaId(estado, categoriaId, plataformaId);
        if (e && c)           return videojuegoRepository.findByEstadoAndCategoriaId(estado, categoriaId);
        if (e && p)           return videojuegoRepository.findByEstadoAndPlataformaId(estado, plataformaId);
        if (e)                return videojuegoRepository.findByEstado(estado);
        if (c && p)           return videojuegoRepository.findByCategoriaIdAndPlataformaId(categoriaId, plataformaId);
        if (c)                return videojuegoRepository.findByCategoriaId(categoriaId);
        if (p)                return videojuegoRepository.findByPlataformaId(plataformaId);
        return videojuegoRepository.findAll();
    }

    public Map<String, Long> obtenerEstadisticas() {
        Map<String, Long> estadisticas = new HashMap<>();
        for (Object[] row : videojuegoRepository.countByEstado()) {
            estadisticas.put((String) row[0], (Long) row[1]);
        }
        return estadisticas;
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
        existente.setAnio(nuevo.getAnio());
        existente.setEstado(nuevo.getEstado());
        if (nuevo.getCategoria() != null) {
            existente.setCategoria(nuevo.getCategoria());
        }
        if (nuevo.getPlataforma() != null) {
            existente.setPlataforma(nuevo.getPlataforma());
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

    public List<Videojuego> listarPorPlataforma(Long plataformaId) {
        return videojuegoRepository.findByPlataformaId(plataformaId);
    }
}
