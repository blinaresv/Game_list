-- GameVault — Datos de prueba

INSERT INTO categoria (nombre) VALUES
    ('Acción'),
    ('Aventura'),
    ('RPG'),
    ('Deportes'),
    ('Simulación'),
    ('Estrategia')
ON CONFLICT (nombre) DO NOTHING;

INSERT INTO plataforma (nombre, fabricante) VALUES
    ('PC',       'Various'),
    ('PS5',      'Sony'),
    ('Xbox',     'Microsoft'),
    ('Nintendo Switch', 'Nintendo'),
    ('PS4',      'Sony')
ON CONFLICT (nombre) DO NOTHING;

INSERT INTO videojuego (titulo, anio, estado, categoria_id, plataforma_id) VALUES
    ('The Witcher 3',        2015, 'TERMINADO', 3, 1),
    ('FIFA 23',              2023, 'JUGANDO',   4, 2),
    ('Halo Infinite',        2021, 'PENDIENTE', 1, 3),
    ('Zelda: Tears of the Kingdom', 2023, 'JUGANDO', 2, 4),
    ('God of War Ragnarok',  2022, 'FAVORITO',  2, 2);

INSERT INTO resena (puntuacion, comentario, videojuego_id) VALUES
    (10, 'Obra maestra, la mejor narrativa de un RPG',         1),
    (7,  'Entretenido pero muy parecido al anterior',          2),
    (9,  'Combate fluido y mapa enorme',                       4),
    (10, 'Historia épica, gráficos impresionantes',            5);

INSERT INTO wishlist (titulo, prioridad, notas, plataforma_id, categoria_id) VALUES
    ('Elden Ring',            'ALTA',  'Dicen que es difícil pero increíble', 2, 3),
    ('Cyberpunk 2077',        'MEDIA', 'Esperar parches, ya está bien pulido', 1, 1),
    ('Hollow Knight: Silksong', 'ALTA', 'Secuela muy esperada',               4, 2);
