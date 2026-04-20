package com.gamelist.gamelist_api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "wishlist")
public class WishlistItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El título es obligatorio")
    private String titulo;

    @NotBlank(message = "La prioridad es obligatoria")
    @Pattern(
        regexp = "ALTA|MEDIA|BAJA",
        message = "La prioridad debe ser: ALTA, MEDIA o BAJA"
    )
    private String prioridad;

    private String notas;

    @ManyToOne
    @JoinColumn(name = "plataforma_id")
    private Plataforma plataforma;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    public WishlistItem() {}

    public Long getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getPrioridad() { return prioridad; }
    public String getNotas() { return notas; }
    public Plataforma getPlataforma() { return plataforma; }
    public Categoria getCategoria() { return categoria; }

    public void setId(Long id) { this.id = id; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public void setPrioridad(String prioridad) { this.prioridad = prioridad; }
    public void setNotas(String notas) { this.notas = notas; }
    public void setPlataforma(Plataforma plataforma) { this.plataforma = plataforma; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }
}
