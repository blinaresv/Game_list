-- GameVault — Schema PostgreSQL 15
-- Tablas en orden de dependencias (FK de menor a mayor)

CREATE TABLE IF NOT EXISTS categoria (
    id       SERIAL PRIMARY KEY,
    nombre   VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS plataforma (
    id         SERIAL PRIMARY KEY,
    nombre     VARCHAR(100) NOT NULL UNIQUE,
    fabricante VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS videojuego (
    id           SERIAL PRIMARY KEY,
    titulo       VARCHAR(255) NOT NULL,
    anio         INTEGER      NOT NULL,
    estado       VARCHAR(50)  NOT NULL
                     CHECK (estado IN ('PENDIENTE','JUGANDO','TERMINADO','FAVORITO')),
    categoria_id INTEGER REFERENCES categoria(id),
    plataforma_id INTEGER REFERENCES plataforma(id)
);

CREATE TABLE IF NOT EXISTS resena (
    id             SERIAL PRIMARY KEY,
    puntuacion     INTEGER     NOT NULL CHECK (puntuacion BETWEEN 1 AND 10),
    comentario     TEXT,
    videojuego_id  INTEGER     NOT NULL REFERENCES videojuego(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS wishlist (
    id            SERIAL PRIMARY KEY,
    titulo        VARCHAR(255) NOT NULL,
    prioridad     VARCHAR(10)  NOT NULL
                      CHECK (prioridad IN ('ALTA','MEDIA','BAJA')),
    notas         TEXT,
    plataforma_id INTEGER REFERENCES plataforma(id),
    categoria_id  INTEGER REFERENCES categoria(id)
);
