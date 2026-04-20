package com.gamelist.gamelist_api.repository;

import com.gamelist.gamelist_api.model.Videojuego;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VideojuegoRepository extends JpaRepository<Videojuego, Long> {

    List<Videojuego> findByCategoriaId(Long categoriaId);
    List<Videojuego> findByPlataformaId(Long plataformaId);
    List<Videojuego> findByEstado(String estado);
    List<Videojuego> findByTituloContainingIgnoreCase(String titulo);

    List<Videojuego> findByTituloContainingIgnoreCaseAndEstadoAndCategoriaIdAndPlataformaId(
        String titulo, String estado, Long categoriaId, Long plataformaId);
    List<Videojuego> findByTituloContainingIgnoreCaseAndEstadoAndCategoriaId(
        String titulo, String estado, Long categoriaId);
    List<Videojuego> findByTituloContainingIgnoreCaseAndEstadoAndPlataformaId(
        String titulo, String estado, Long plataformaId);
    List<Videojuego> findByTituloContainingIgnoreCaseAndEstado(String titulo, String estado);
    List<Videojuego> findByTituloContainingIgnoreCaseAndCategoriaIdAndPlataformaId(
        String titulo, Long categoriaId, Long plataformaId);
    List<Videojuego> findByTituloContainingIgnoreCaseAndCategoriaId(String titulo, Long categoriaId);
    List<Videojuego> findByTituloContainingIgnoreCaseAndPlataformaId(String titulo, Long plataformaId);
    List<Videojuego> findByEstadoAndCategoriaIdAndPlataformaId(String estado, Long categoriaId, Long plataformaId);
    List<Videojuego> findByEstadoAndCategoriaId(String estado, Long categoriaId);
    List<Videojuego> findByEstadoAndPlataformaId(String estado, Long plataformaId);
    List<Videojuego> findByCategoriaIdAndPlataformaId(Long categoriaId, Long plataformaId);

    @Query("SELECT v.estado AS estado, COUNT(v) AS total FROM Videojuego v GROUP BY v.estado")
    List<Object[]> countByEstado();
}
