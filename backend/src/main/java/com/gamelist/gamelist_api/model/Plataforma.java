package com.gamelist.gamelist_api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "plataforma", uniqueConstraints = @UniqueConstraint(columnNames = "nombre"))
public class Plataforma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre de la plataforma es obligatorio")
    @Column(unique = true)
    private String nombre;

    @NotBlank(message = "El fabricante es obligatorio")
    private String fabricante;

    public Plataforma() {}

    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public String getFabricante() { return fabricante; }
    public void setId(Long id) { this.id = id; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setFabricante(String fabricante) { this.fabricante = fabricante; }
}
