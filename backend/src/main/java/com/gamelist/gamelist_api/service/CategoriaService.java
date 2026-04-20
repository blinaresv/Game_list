package com.gamelist.gamelist_api.service;

import com.gamelist.gamelist_api.exception.ResourceNotFoundException;
import com.gamelist.gamelist_api.model.Categoria;
import com.gamelist.gamelist_api.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<Categoria> listarCategorias() {
        return categoriaRepository.findAll();
    }

    public Categoria obtenerPorId(Long id) {
        return categoriaRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada con id: " + id));
    }

    public Categoria crearCategoria(Categoria categoria) {
        if (categoriaRepository.existsByNombreIgnoreCase(categoria.getNombre())) {
            throw new IllegalArgumentException("Ya existe una categoría con el nombre: " + categoria.getNombre());
        }
        return categoriaRepository.save(categoria);
    }

    public Categoria actualizarCategoria(Long id, Categoria datos) {
        Categoria existente = obtenerPorId(id);
        if (!existente.getNombre().equalsIgnoreCase(datos.getNombre())
                && categoriaRepository.existsByNombreIgnoreCase(datos.getNombre())) {
            throw new IllegalArgumentException("Ya existe una categoría con el nombre: " + datos.getNombre());
        }
        existente.setNombre(datos.getNombre());
        return categoriaRepository.save(existente);
    }

    public void eliminarCategoria(Long id) {
        obtenerPorId(id);
        categoriaRepository.deleteById(id);
    }
}
