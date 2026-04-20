package com.gamelist.gamelist_api.controller;

import com.gamelist.gamelist_api.model.Resena;
import com.gamelist.gamelist_api.service.ResenaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resenas")
@CrossOrigin(origins = "*")
public class ResenaController {

    private final ResenaService resenaService;

    public ResenaController(ResenaService resenaService) {
        this.resenaService = resenaService;
    }

    @PostMapping
    public ResponseEntity<Resena> crear(@Valid @RequestBody Resena resena) {
        return ResponseEntity.status(HttpStatus.CREATED).body(resenaService.crear(resena));
    }

    @GetMapping("/videojuego/{videojuegoId}")
    public ResponseEntity<List<Resena>> listarPorVideojuego(@PathVariable Long videojuegoId) {
        return ResponseEntity.ok(resenaService.listarPorVideojuego(videojuegoId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        resenaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
