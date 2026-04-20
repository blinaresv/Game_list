package com.gamelist.gamelist_api.controller;

import com.gamelist.gamelist_api.model.WishlistItem;
import com.gamelist.gamelist_api.service.WishlistService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wishlist")
@CrossOrigin(origins = "*")
public class WishlistController {

    private final WishlistService wishlistService;

    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @GetMapping
    public ResponseEntity<List<WishlistItem>> listar(
            @RequestParam(required = false) String prioridad,
            @RequestParam(required = false) String titulo) {
        return ResponseEntity.ok(wishlistService.listar(prioridad, titulo));
    }

    @GetMapping("/{id}")
    public ResponseEntity<WishlistItem> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(wishlistService.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<WishlistItem> crear(@Valid @RequestBody WishlistItem item) {
        return ResponseEntity.status(HttpStatus.CREATED).body(wishlistService.crear(item));
    }

    @PutMapping("/{id}")
    public ResponseEntity<WishlistItem> actualizar(@PathVariable Long id, @Valid @RequestBody WishlistItem item) {
        return ResponseEntity.ok(wishlistService.actualizar(id, item));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        wishlistService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
