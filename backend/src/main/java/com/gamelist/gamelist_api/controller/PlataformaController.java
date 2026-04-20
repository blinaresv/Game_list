package com.gamelist.gamelist_api.controller;

import com.gamelist.gamelist_api.model.Plataforma;
import com.gamelist.gamelist_api.service.PlataformaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/plataformas")
@CrossOrigin(origins = "*")
public class PlataformaController {

    private final PlataformaService plataformaService;

    public PlataformaController(PlataformaService plataformaService) {
        this.plataformaService = plataformaService;
    }

    @GetMapping
    public ResponseEntity<List<Plataforma>> listar() {
        return ResponseEntity.ok(plataformaService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Plataforma> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(plataformaService.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<Plataforma> crear(@Valid @RequestBody Plataforma plataforma) {
        return ResponseEntity.status(HttpStatus.CREATED).body(plataformaService.crear(plataforma));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Plataforma> actualizar(@PathVariable Long id, @Valid @RequestBody Plataforma plataforma) {
        return ResponseEntity.ok(plataformaService.actualizar(id, plataforma));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        plataformaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
