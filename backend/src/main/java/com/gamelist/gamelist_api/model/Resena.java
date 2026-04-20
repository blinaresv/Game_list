package com.gamelist.gamelist_api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "resena")
public class Resena {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "La puntuación es obligatoria")
    @Min(value = 1, message = "La puntuación mínima es 1")
    @Max(value = 10, message = "La puntuación máxima es 10")
    private Integer puntuacion;

    private String comentario;

    @NotNull(message = "El videojuego es obligatorio")
    @ManyToOne
    @JoinColumn(name = "videojuego_id", nullable = false)
    private Videojuego videojuego;

    public Resena() {}

    public Long getId() { return id; }
    public Integer getPuntuacion() { return puntuacion; }
    public String getComentario() { return comentario; }
    public Videojuego getVideojuego() { return videojuego; }

    public void setId(Long id) { this.id = id; }
    public void setPuntuacion(Integer puntuacion) { this.puntuacion = puntuacion; }
    public void setComentario(String comentario) { this.comentario = comentario; }
    public void setVideojuego(Videojuego videojuego) { this.videojuego = videojuego; }
}
