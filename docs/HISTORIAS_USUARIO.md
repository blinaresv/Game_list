# 📖 Historias de Usuario — GameVault v2.0
# Formato requerido por el enunciado del Proyecto 2
# Mínimo 2 historias por persona por sprint = 6 historias por persona

# ══════════════════════════════════════════════════════════════
# SPRINT 1 — Backend + Base de Datos
# ══════════════════════════════════════════════════════════════

---

## HU-01 | Gestión de videojuegos
**Rama:** `feature/sprint1-backend-videojuegos`
**Responsable:** @Persona1
**Sprint:** 1 | **Estimación:** 5 puntos | **Tipo:** Feature | **Prioridad:** Alta

### Como usuario de GameVault
Quiero poder crear, ver, editar y eliminar videojuegos de mi biblioteca
Para llevar un registro organizado de todos los juegos que tengo o he jugado

### Criterios de Aceptación:
- [x] GET /api/videojuegos retorna lista de todos los juegos con categoría y plataforma
- [x] POST /api/videojuegos crea un juego con validación de campos obligatorios
- [x] PUT /api/videojuegos/{id} actualiza los datos de un juego existente
- [x] DELETE /api/videojuegos/{id} elimina el juego y sus reseñas (cascade)
- [x] Si el ID no existe, retorna 404 con mensaje descriptivo en JSON
- [x] El campo `estado` solo acepta: PENDIENTE, JUGANDO, TERMINADO, FAVORITO

---

## HU-02 | Clasificación por categorías
**Rama:** `feature/sprint1-backend-categorias`
**Responsable:** @Persona1
**Sprint:** 1 | **Estimación:** 3 puntos | **Tipo:** Feature | **Prioridad:** Alta

### Como usuario de GameVault
Quiero poder organizar mis juegos por categorías (Acción, RPG, Deportes, etc.)
Para encontrar juegos del mismo género fácilmente y filtrar mi biblioteca

### Criterios de Aceptación:
- [x] CRUD completo de categorías en /api/categorias
- [x] Al crear un videojuego se puede asignar una categoría existente
- [x] GET /api/videojuegos?categoriaId={id} filtra juegos por categoría
- [x] GET /api/videojuegos/{id}/categoria retorna la categoría del juego
- [x] No se puede crear categoría con nombre duplicado (unique constraint)

---

## HU-03 | Gestión de plataformas
**Rama:** `feature/sprint1-backend-plataformas`
**Responsable:** @Persona1
**Sprint:** 1 | **Estimación:** 3 puntos | **Tipo:** Feature | **Prioridad:** Alta

### Como usuario de GameVault
Quiero registrar en qué plataforma (PS5, PC, Xbox, etc.) tengo cada juego
Para saber rápidamente dónde puedo jugarlo y filtrar por consola

### Criterios de Aceptación:
- [x] CRUD completo de plataformas en /api/plataformas
- [x] Plataforma tiene campos: nombre y fabricante
- [x] Al crear/editar videojuego se puede asignar plataforma
- [x] GET /api/videojuegos?plataformaId={id} filtra por plataforma
- [x] No se pueden duplicar nombres de plataforma

---

## HU-04 | Búsqueda y filtros en la biblioteca
**Rama:** `feature/sprint1-backend-filtros`
**Responsable:** @Persona1
**Sprint:** 1 | **Estimación:** 3 puntos | **Tipo:** Feature | **Prioridad:** Media

### Como usuario de GameVault
Quiero buscar juegos por nombre y filtrar por estado o categoría
Para encontrar rápidamente un juego específico sin tener que revisar toda la lista

### Criterios de Aceptación:
- [x] GET /api/videojuegos?titulo=wit encuentra "The Witcher 3" (búsqueda parcial)
- [x] La búsqueda es insensible a mayúsculas ("WITCHER" = "witcher")
- [x] Se pueden combinar filtros: ?estado=JUGANDO&categoriaId=3
- [x] Sin parámetros retorna todos los juegos
- [x] GET /api/videojuegos/estadisticas retorna conteo por estado

---

## HU-05 | Configuración de base de datos en Cloud SQL
**Rama:** `feature/sprint1-database-setup`
**Responsable:** @Persona1
**Sprint:** 1 | **Estimación:** 5 puntos | **Tipo:** DevOps | **Prioridad:** Alta

### Como equipo de desarrollo
Quiero tener la base de datos PostgreSQL configurada en Google Cloud SQL
Para que la aplicación persista datos de forma segura y accesible en producción

### Criterios de Aceptación:
- [x] Instancia Cloud SQL creada en GCP (región us-central1)
- [x] schema.sql ejecutado correctamente con las 5 tablas
- [x] seed.sql con datos de prueba cargado
- [x] Conexión desde Spring Boot via Cloud SQL socket factory funcionando
- [x] Variables de entorno configuradas en Cloud Run (sin credenciales en el código)

---

## HU-06 | Sistema de reseñas de videojuegos
**Rama:** `feature/sprint1-backend-resenas`
**Responsable:** @Persona1
**Sprint:** 1 | **Estimación:** 3 puntos | **Tipo:** Feature | **Prioridad:** Media

### Como usuario de GameVault
Quiero poder escribir reseñas con puntuación para los juegos de mi biblioteca
Para recordar mi opinión sobre cada juego y ver el promedio de valoraciones

### Criterios de Aceptación:
- [x] POST /api/resenas crea reseña vinculada a un videojuego
- [x] Puntuación debe estar entre 1 y 10
- [x] GET /api/resenas/videojuego/{id} retorna todas las reseñas del juego
- [x] DELETE /api/resenas/{id} elimina una reseña
- [x] Al eliminar un videojuego, sus reseñas se eliminan automáticamente (cascade)

---

## HU-18 | Lista de deseos en el backend (Wishlist API)
**Rama:** `feature/sprint1-backend-wishlist`
**Responsable:** @Persona1
**Sprint:** 1 | **Estimación:** 3 puntos | **Tipo:** Feature | **Prioridad:** Media

### Como usuario de GameVault
Quiero poder guardar juegos en una lista de deseos con prioridad desde la API
Para que el frontend pueda mostrar y gestionar mi wishlist de forma persistente

### Criterios de Aceptación:
- [x] CRUD completo en /api/wishlist
- [x] Campos: titulo, plataforma, categoria, prioridad (ALTA/MEDIA/BAJA), notas
- [x] GET /api/wishlist?prioridad=ALTA filtra por prioridad
- [x] GET /api/wishlist?titulo=star busca por título parcial
- [x] Relaciones con Plataforma y Categoria opcionales

---

# ══════════════════════════════════════════════════════════════
# SPRINT 3 — Despliegue, Docker y Documentación
# ══════════════════════════════════════════════════════════════

---

## HU-14 | CI/CD con Cloud Build y GitHub Actions
**Rama:** `feature/sprint3-cicd`
**Responsable:** @Persona1
**Sprint:** 3 | **Estimación:** 5 puntos | **Tipo:** DevOps | **Prioridad:** Alta

### Como equipo de desarrollo
Quiero que cada push a main dispare automáticamente el despliegue en GCP
Para no tener que hacer el deploy manualmente y reducir errores humanos

### Criterios de Aceptación:
- [x] cloudbuild.yaml actualizado para build de imagen Docker unificada
- [x] GitHub Actions workflow mueve cards del tablero Kanban automáticamente
- [x] Al asignar un issue, la card pasa a "In Progress"
- [x] Al cerrar un PR merged, la card pasa a "Done"
- [x] Cloud Run actualizado con la nueva imagen en cada deploy
- [ ] Variables de entorno configuradas como secrets en GitHub *(manual: configurar en Settings → Secrets)*

---

## HU-17 | Manejo de errores y Swagger con HTTPS
**Rama:** `feature/sprint3-backend-fixes`
**Responsable:** @Persona1
**Sprint:** 3 | **Estimación:** 3 puntos | **Tipo:** Bug | **Prioridad:** Alta

### Como desarrollador y usuario de la API
Quiero que los errores devuelvan mensajes claros en JSON y que Swagger funcione con HTTPS
Para depurar problemas fácilmente y documentar la API correctamente en producción

### Criterios de Aceptación:
- [x] 404 con JSON descriptivo cuando el recurso no existe
- [x] 400 con mensaje del campo inválido cuando fallan validaciones
- [x] Swagger accesible en https://[url]/swagger-ui.html (no en http://)
- [x] server.forward-headers-strategy=framework configurado
- [x] springdoc-openapi 2.5 en lugar de springfox (compatible con Spring Boot 3)

<!-- HU-02 implementada y verificada en feature/sprint1-backend-categorias -->
<!-- HU cerrada via feature/sprint3-backend-fixes -->
