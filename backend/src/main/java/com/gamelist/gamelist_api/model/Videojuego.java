package com.gamelist.gamelist_api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "videojuego")
public class Videojuego {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El título es obligatorio")
    private String titulo;

    private String plataforma;

    @NotNull(message = "El año es obligatorio")
    private Integer anio;

    @NotBlank(message = "El estado es obligatorio")
    @Pattern(
        regexp = "PENDIENTE|JUGANDO|TERMINADO|FAVORITO",
        message = "El estado debe ser: PENDIENTE, JUGANDO, TERMINADO o FAVORITO"
    )
    private String estado;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    public Videojuego() {}

    public Long getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getPlataforma() { return plataforma; }
    public Integer getAnio() { return anio; }
    public String getEstado() { return estado; }
    public Categoria getCategoria() { return categoria; }

    public void setId(Long id) { this.id = id; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public void setPlataforma(String plataforma) { this.plataforma = plataforma; }
    public void setAnio(Integer anio) { this.anio = anio; }
    public void setEstado(String estado) { this.estado = estado; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }
}
