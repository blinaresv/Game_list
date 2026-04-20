package com.gamelist.gamelist_api.service;

import com.gamelist.gamelist_api.exception.ResourceNotFoundException;
import com.gamelist.gamelist_api.model.Resena;
import com.gamelist.gamelist_api.model.Videojuego;
import com.gamelist.gamelist_api.repository.ResenaRepository;
import com.gamelist.gamelist_api.repository.VideojuegoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResenaService {

    private final ResenaRepository resenaRepository;
    private final VideojuegoRepository videojuegoRepository;

    public ResenaService(ResenaRepository resenaRepository, VideojuegoRepository videojuegoRepository) {
        this.resenaRepository = resenaRepository;
        this.videojuegoRepository = videojuegoRepository;
    }

    public Resena crear(Resena resena) {
        Long videojuegoId = resena.getVideojuego().getId();
        Videojuego videojuego = videojuegoRepository.findById(videojuegoId)
            .orElseThrow(() -> new ResourceNotFoundException("Videojuego no encontrado con id: " + videojuegoId));
        resena.setVideojuego(videojuego);
        return resenaRepository.save(resena);
    }

    public List<Resena> listarPorVideojuego(Long videojuegoId) {
        if (!videojuegoRepository.existsById(videojuegoId)) {
            throw new ResourceNotFoundException("Videojuego no encontrado con id: " + videojuegoId);
        }
        return resenaRepository.findByVideojuegoId(videojuegoId);
    }

    public void eliminar(Long id) {
        if (!resenaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Reseña no encontrada con id: " + id);
        }
        resenaRepository.deleteById(id);
    }
}
