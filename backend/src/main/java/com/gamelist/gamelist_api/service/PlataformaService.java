package com.gamelist.gamelist_api.service;

import com.gamelist.gamelist_api.exception.ResourceNotFoundException;
import com.gamelist.gamelist_api.model.Plataforma;
import com.gamelist.gamelist_api.repository.PlataformaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlataformaService {

    private final PlataformaRepository plataformaRepository;

    public PlataformaService(PlataformaRepository plataformaRepository) {
        this.plataformaRepository = plataformaRepository;
    }

    public List<Plataforma> listar() {
        return plataformaRepository.findAll();
    }

    public Plataforma obtenerPorId(Long id) {
        return plataformaRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Plataforma no encontrada con id: " + id));
    }

    public Plataforma crear(Plataforma plataforma) {
        if (plataformaRepository.existsByNombreIgnoreCase(plataforma.getNombre())) {
            throw new IllegalArgumentException("Ya existe una plataforma con el nombre: " + plataforma.getNombre());
        }
        return plataformaRepository.save(plataforma);
    }

    public Plataforma actualizar(Long id, Plataforma datos) {
        Plataforma existente = obtenerPorId(id);
        if (!existente.getNombre().equalsIgnoreCase(datos.getNombre())
                && plataformaRepository.existsByNombreIgnoreCase(datos.getNombre())) {
            throw new IllegalArgumentException("Ya existe una plataforma con el nombre: " + datos.getNombre());
        }
        existente.setNombre(datos.getNombre());
        existente.setFabricante(datos.getFabricante());
        return plataformaRepository.save(existente);
    }

    public void eliminar(Long id) {
        obtenerPorId(id);
        plataformaRepository.deleteById(id);
    }
}
