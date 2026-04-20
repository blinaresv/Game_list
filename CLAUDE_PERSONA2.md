# CLAUDE.md — GameVault (Persona 2: Frontend + DevOps)

## Instalación de skills al iniciar
Ejecuta estos comandos ANTES de cualquier tarea:

```bash
npx claude-code-templates@latest --skill development/senior-frontend
npx claude-code-templates@latest --skill creative-design/ui-ux-pro-max
npx claude-code-templates@latest --skill development/senior-backend
npx claude-code-templates@latest --skill business-marketing/seo-optimizer
```

---

## Contexto del proyecto
- **Nombre:** GameVault — Gestor de Videojuegos
- **Stack:** Spring Boot 3.2 (Java 21) + HTML/CSS/JS + PostgreSQL 15
- **Cloud:** GCP (Cloud Run + Cloud SQL, región us-central1)
- **Metodología:** Kanban, 3 sprints, una rama feature por historia de usuario
- **Mi rol:** Frontend Developer + DevOps (Persona 2)

---

## Estructura del repositorio
```
GameVault/
├── CLAUDE.md
├── Dockerfile                  # Multi-stage: Maven build + JRE Alpine
├── cloudbuild.yaml             # CI/CD con Cloud Build
├── README.md
├── .github/workflows/
│   ├── kanban-automation.yml
│   └── deploy.yml
├── backend/                    # NO modificar (es del compañero)
│   └── src/main/resources/static/  # ← aquí va el frontend en build
├── frontend/                   # Mi área principal
│   ├── index.html
│   ├── style.css
│   └── app.js
├── database/
│   ├── schema.sql
│   └── seed.sql
└── docs/
    ├── HISTORIAS_USUARIO.md    # ← LEER ANTES DE CADA TAREA
    └── GUIA_GITHUB_PROJECTS.md
```

---

## Reglas de Git — OBLIGATORIAS

### Estructura de ramas
```
main          → producción final (NO tocar directamente)
development   → integración (base para crear feature/*)
feature/*     → una rama por historia de usuario (temporal)
```

### Flujo por cada historia de usuario
```bash
# 1. Siempre partir de development actualizado
git checkout development
git pull origin development

# 2. Crear rama con el nombre exacto de la historia
git checkout -b feature/sprint[N]-[nombre-historia]

# 3. Implementar los cambios
# (solo lo que pide esa historia, nada más)

# 4. Commits descriptivos — uno por cambio lógico
git add .
git commit -m "feat: [descripción corta] - closes #[N]"

# 5. Push de la rama
git push origin feature/sprint[N]-[nombre-historia]

# 6. Notificar: "Rama lista para PR: feature/sprint[N]-[nombre-historia]"
```

### Formato de commits
```
feat: descripción        → nueva funcionalidad
fix: descripción         → corrección de bug  
docs: descripción        → documentación
chore: descripción       → configuración
style: descripción       → cambios de CSS/diseño
```

### NUNCA hacer esto
- ❌ Nunca commit directo a `main` o `development`
- ❌ Nunca modificar archivos del backend sin coordinarlo
- ❌ Nunca mezclar cambios de dos historias en una rama

---

## Mis historias de usuario (Sprint 2 y Sprint 3)

Lee los criterios completos en `docs/HISTORIAS_USUARIO.md`

| # | Historia | Rama | Issue | Sprint |
|---|---|---|---|---|
| HU-07 | Biblioteca visual de juegos | `feature/sprint2-frontend-biblioteca` | #7 | 2 |
| HU-08 | Búsqueda y filtros (fix bug) | `feature/sprint2-frontend-filtros` | #8 | 2 |
| HU-09 | Página de detalle por juego | `feature/sprint2-frontend-detalle` | #9 | 2 |
| HU-10 | Formulario agregar/editar | `feature/sprint2-frontend-formulario` | #10 | 2 |
| HU-11 | Wishlist frontend | `feature/sprint2-frontend-wishlist` | #11 | 2 |
| HU-12 | Estadísticas dashboard | `feature/sprint2-frontend-stats` | #12 | 2 |
| HU-13 | Docker unificado | `feature/sprint3-docker-unificado` | #13 | 3 |
| HU-15 | GitHub Project Kanban | `feature/sprint3-github-project` | #15 | 3 |
| HU-16 | Documentación README | `feature/sprint3-documentacion` | #16 | 3 |

---

## Stack Frontend detallado

### Tecnologías
- **HTML5 + CSS3 + JavaScript vanilla** (sin frameworks)
- **Fetch API** para consumir los endpoints REST
- **Google Fonts:** Bebas Neue (títulos) + DM Sans (cuerpo)
- **Sin dependencias externas** de JS (no jQuery, no React)

### API URL — MUY IMPORTANTE
```javascript
// En app.js, la URL se detecta automáticamente:
const API_URL = window.location.hostname === 'localhost'
  ? 'http://localhost:8080/api'   // desarrollo local
  : '/api';                        // producción (Docker unificado)
```

### Tema visual
```css
/* Variables principales del diseño */
--bg: #0d0f14          /* fondo oscuro */
--surface: #161922     /* cards y paneles */
--accent: #6c63ff      /* color principal (morado) */
--text: #e8eaf0        /* texto principal */

/* Colores por estado de juego */
--JUGANDO: #6c63ff
--PENDIENTE: #f59e0b
--TERMINADO: #22c55e
--FAVORITO: #ff6584
```

### Secciones de la app
```
Biblioteca  → grid de cards de juegos con imagen, estado, filtros
Wishlist    → lista de deseos con prioridad por color
Agregar     → formulario de crear/editar juego
Detalle     → página completa del juego con reseñas
```

### Fix de búsqueda (bug del proyecto anterior)
- La búsqueda anterior comparaba exacto → no encontraba nada
- La nueva usa `.toLowerCase().includes(busq)` en el cliente
- También se puede combinar con filtros de estado y categoría

---

## Stack DevOps detallado

### Dockerfile (multi-stage)
```dockerfile
# Etapa 1: Maven compila el backend Y copia el frontend a resources/static/
FROM maven:3.9.6-eclipse-temurin-21 AS build
COPY frontend/ ./src/main/resources/static/
RUN mvn clean package -DskipTests

# Etapa 2: Solo el JRE liviano con el jar
FROM eclipse-temurin:21-jre-alpine
COPY --from=build /app/target/*.jar app.jar
```

### Resultado del Docker unificado
- Una sola URL de Cloud Run sirve todo
- `/` → Frontend (HTML/CSS/JS)
- `/api/*` → Backend REST API
- No se necesita Firebase Hosting separado

### GitHub Actions
- `kanban-automation.yml` → mueve cards del tablero automáticamente
- `deploy.yml` → push a main dispara Cloud Build → deploy a Cloud Run

---

## Cómo procesar cada historia

1. **Leer** los criterios de aceptación en `docs/HISTORIAS_USUARIO.md`
2. **Revisar** el código frontend existente antes de modificar
3. **Crear** la rama feature correspondiente desde development
4. **Implementar** solo lo que pide esa historia
5. **Verificar** que la UI funcione correctamente
6. **Hacer commits** pequeños (no todo junto)
7. **Push** y notificar que está lista para PR

---

## Al terminar cada historia — checklist

Antes de hacer push, verificar:
- [ ] La página carga sin errores en consola del navegador
- [ ] Todos los criterios de aceptación están implementados
- [ ] El diseño es consistente con el tema oscuro del proyecto
- [ ] El commit incluye `closes #N` con el número correcto
- [ ] La URL de la API usa la variable `API_URL` (no hardcodeada)
- [ ] El diseño funciona en móvil (responsive)

---

## Endpoints del backend que consume el frontend

```
GET  /api/videojuegos              → lista de juegos (con filtros)
GET  /api/videojuegos/{id}         → detalle de un juego
GET  /api/videojuegos/estadisticas → { JUGANDO: 3, TERMINADO: 2, ... }
POST /api/videojuegos              → crear juego
PUT  /api/videojuegos/{id}         → editar juego
DELETE /api/videojuegos/{id}       → eliminar juego

GET  /api/categorias               → para los dropdowns
GET  /api/plataformas              → para los dropdowns

GET  /api/resenas/videojuego/{id}  → reseñas de un juego
POST /api/resenas                  → crear reseña
DELETE /api/resenas/{id}           → eliminar reseña

GET  /api/wishlist                 → lista de deseos
POST /api/wishlist                 → agregar a wishlist
DELETE /api/wishlist/{id}          → quitar de wishlist
```

---

## Contexto de despliegue GCP

```
Proyecto GCP: game-list-cloud
Región: us-central1
Servicio Cloud Run: game-list-app
URL: https://game-list-app-[hash]-uc.a.run.app
```
