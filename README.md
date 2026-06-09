# Micro-Reportes - Microservicio de Reportes

Microservicio Spring Boot para la gestión de reportes de incendios. Recibe reportes ciudadanos y los publica en Kafka para su procesamiento.

## Tecnologías

- Spring Boot 3.3.4, Spring Data JPA, Spring Validation
- PostgreSQL (AWS RDS), H2 (tests)
- Apache Kafka
- JaCoCo, Mockito, JUnit 5, Springdoc OpenAPI

## Endpoints

| Método | Ruta | Descripción |
|--------|------|-------------|
| POST | `/api/reportes` | Crear reporte |
| GET | `/api/reportes` | Listar reportes |

Swagger: `http://localhost:8084/swagger-ui/index.html`

## Ejecutar

```bash
.\mvnw.cmd spring-boot:run
```

## Pruebas

```bash
.\mvnw.cmd test        # ejecutar tests
.\mvnw.cmd verify      # tests + JaCoCo report
```

Cobertura: **89.4%**

## Capturas

> _(Agregar captura del reporte JaCoCo y Swagger UI)_
