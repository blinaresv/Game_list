# 📖 Historias de Usuario — GameVault v2.0
# Formato requerido por el enunciado del Proyecto 2

# ══════════════════════════════════════════════════════════════
# SPRINT 1 — Backend + Base de Datos
# ══════════════════════════════════════════════════════════════



## HU-05 | Configuración de base de datos en Cloud SQL
**Rama:** `feature/sprint1-database-setup`
**Responsable:** @Persona1
**Sprint:** 1 | **Estimación:** 5 puntos | **Tipo:** DevOps | **Prioridad:** Alta

### Como equipo de desarrollo
Quiero tener la base de datos PostgreSQL configurada en Google Cloud SQL
Para que la aplicación persista datos de forma segura y accesible en producción

### Criterios de Aceptación:
- [x] Instancia Cloud SQL creada en GCP (región us-central1)
- [x] schema.sql ejecutado correctamente con las 5 tablas (categoria, plataforma, videojuego, resena, wishlist)
- [x] seed.sql con datos de prueba cargado (categorías, plataformas, juegos, reseñas, wishlist)
- [x] Conexión desde Spring Boot via Cloud SQL socket factory funcionando
- [x] Variables de entorno configuradas en Cloud Run (sin credenciales en el código)

---




# ══════════════════════════════════════════════════════════════
# SPRINT 3 — Despliegue, Docker y Documentación
# ══════════════════════════════════════════════════════════════



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
- [ ] Variables de entorno configuradas como secrets en GitHub *(manual: GCP_SA_KEY, GCP_PROJECT_ID, GCP_REGION, PROJECT_TOKEN + variable PROJECT_NUMBER)*



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
