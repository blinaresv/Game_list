package com.gamelist.gamelist_api.controller;

import com.gamelist.gamelist_api.model.Categoria;
import com.gamelist.gamelist_api.model.Videojuego;
import com.gamelist.gamelist_api.service.VideojuegoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/videojuegos")
@CrossOrigin(origins = "*")
public class VideojuegoController {

    private final VideojuegoService videojuegoService;

    public VideojuegoController(VideojuegoService videojuegoService) {
        this.videojuegoService = videojuegoService;
    }

    @GetMapping
    public ResponseEntity<List<Videojuego>> listar(
            @RequestParam(required = false) String titulo,
            @RequestParam(required = false) String estado,
            @RequestParam(required = false) Long categoriaId,
            @RequestParam(required = false) Long plataformaId) {
        return ResponseEntity.ok(videojuegoService.buscarConFiltros(titulo, estado, categoriaId, plataformaId));
    }

    @GetMapping("/estadisticas")
    public ResponseEntity<Map<String, Long>> estadisticas() {
        return ResponseEntity.ok(videojuegoService.obtenerEstadisticas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Videojuego> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(videojuegoService.obtenerPorId(id));
    }

    @GetMapping("/{id}/categoria")
    public ResponseEntity<Categoria> obtenerCategoria(@PathVariable Long id) {
        Videojuego v = videojuegoService.obtenerPorId(id);
        return ResponseEntity.ok(v.getCategoria());
    }

    @PostMapping
    public ResponseEntity<Videojuego> crear(@Valid @RequestBody Videojuego videojuego) {
        return ResponseEntity.status(HttpStatus.CREATED).body(videojuegoService.crearVideojuego(videojuego));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Videojuego> actualizar(@PathVariable Long id, @Valid @RequestBody Videojuego videojuego) {
        return ResponseEntity.ok(videojuegoService.actualizarVideojuego(id, videojuego));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        videojuegoService.eliminarVideojuego(id);
        return ResponseEntity.noContent().build();
    }
}
