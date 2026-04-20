package com.gamelist.gamelist_api.controller;

import com.gamelist.gamelist_api.model.Videojuego;
import com.gamelist.gamelist_api.service.VideojuegoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/videojuegos")
@CrossOrigin(origins = "*")
public class VideojuegoController {

    private final VideojuegoService videojuegoService;

    public VideojuegoController(VideojuegoService videojuegoService) {
        this.videojuegoService = videojuegoService;
    }

    @GetMapping
    public ResponseEntity<List<Videojuego>> listar() {
        return ResponseEntity.ok(videojuegoService.listarVideojuegos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Videojuego> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(videojuegoService.obtenerPorId(id));
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

    @GetMapping("/categoria/{categoriaId}")
    public ResponseEntity<List<Videojuego>> listarPorCategoria(@PathVariable Long categoriaId) {
        return ResponseEntity.ok(videojuegoService.listarPorCategoria(categoriaId));
    }
}
