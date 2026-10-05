# VetTurno

## 1. Historia

**Veterinaria Huellitas** es una clínica veterinaria atendida por Doña Marta, el doctor Andrés y Paula, quien recibe llamadas, responde mensajes y organiza las citas.

Actualmente, la agenda se maneja entre un cuaderno y conversaciones de WhatsApp. Esta forma de trabajo puede generar problemas como reservar dos consultas para el mismo veterinario a la misma hora, registrar incorrectamente los datos de una mascota o perder información del responsable.

**VetTurno** nace como una API REST para centralizar esta información, organizar propietarios, mascotas, veterinarios y citas, y controlar el acceso mediante autenticación y autorización.

---

## 2. Alcance

El proyecto permite:

- Registrar usuarios e iniciar sesión.
- Autenticar usuarios mediante **JWT**.
- Gestionar propietarios.
- Gestionar mascotas y relacionarlas con un propietario.
- Gestionar veterinarios.
- Crear y consultar citas.
- Validar que las citas tengan una fecha futura.
- Evitar que un veterinario tenga dos citas en la misma fecha y hora.
- Validar que las relaciones requeridas existan en la base de datos.
- Controlar el acceso mediante los roles `USER` y `ADMIN`.
- Permitir únicamente a `ADMIN` registrar veterinarios.
- Documentar y probar la API mediante Swagger / OpenAPI.
- Centralizar los errores mediante un manejador global.

El proyecto está orientado al backend de la gestión veterinaria; no incluye una interfaz web independiente para los usuarios finales.

---

## 3. Tecnologías utilizadas

- **Java 17**
- **Spring Boot 4.1.1**
- **Maven**
- **Spring Web MVC**
- **Spring Data JPA**
- **Hibernate**
- **MySQL**
- **Spring Security**
- **JWT (JJWT 0.13.0)**
- **BCrypt**
- **Bean Validation**
- **Swagger / OpenAPI (Springdoc 3.1.0)**
- **Git / GitHub**

---

## 4. Modelo de datos

El sistema utiliza las siguientes entidades principales:

### Usuario

Representa las cuentas que pueden autenticarse en el sistema.

- `id`
- `email` — único
- `password` — almacenada mediante BCrypt
- `rol` — `USER` o `ADMIN`

El registro público asigna el rol `USER` por defecto.

### Propietario

Representa al responsable de una o varias mascotas.

- `id`
- `nombre`
- `telefono`
- `email`

Relación:

```text
Propietario 1 ─────── N Mascota
```

### Mascota

Representa a los animales registrados.

- `id`
- `nombre`
- `especie`
- `raza`
- `propietario`

La mascota pertenece a un propietario.

### Veterinario

Representa a los profesionales que atienden las citas.

- `id`
- `nombre`
- `especialidad`

El registro de veterinarios está restringido al rol `ADMIN`.

### Cita

Representa una cita veterinaria.

- `id`
- `fechaHora`
- `motivo`
- `mascotaId`
- `veterinarioId`

Reglas principales:

- La fecha y hora son obligatorias.
- La fecha y hora deben ser futuras.
- La mascota debe existir.
- El veterinario debe existir.
- No se permite que el mismo veterinario tenga dos citas en la misma fecha y hora.

---

## 5. Roles y seguridad

La aplicación utiliza Spring Security y JWT.

### `USER`

Puede:

- Consultar mascotas.
- Crear mascotas.
- Consultar propietarios.
- Crear propietarios.
- Consultar veterinarios.
- Consultar y crear citas.

### `ADMIN`

Tiene los permisos de `USER` y además puede:

- Registrar veterinarios.

### Rutas públicas

Las siguientes rutas no requieren JWT:

```text
/api/auth/**
/swagger-ui/**
/swagger-ui.html
/v3/api-docs/**
/v3/api-docs
```

### Rutas protegidas

Los endpoints de negocio requieren autenticación.

El acceso se controla mediante:

```text
USER / ADMIN → operaciones permitidas para recepción
ADMIN        → creación de veterinarios
```

La aplicación utiliza sesiones **STATELESS**, por lo que la autenticación se realiza mediante JWT en cada petición protegida.

---

## 6. Endpoints

Base URL:

```text
http://localhost:8080
```

### Autenticación

#### Registrar usuario

```http
POST /api/auth/register
```

Ejemplo:

```json
{
  "email": "usuario@correo.com",
  "password": "123456"
}
```

El registro asigna automáticamente el rol `USER`.

#### Iniciar sesión

```http
POST /api/auth/login
```

Ejemplo:

```json
{
  "email": "usuario@correo.com",
  "password": "123456"
}
```

La respuesta contiene un JWT.

---

### Propietarios

#### Listar propietarios

```http
GET /api/propietarios/listar
```

Requiere autenticación.

#### Crear propietario

```http
POST /api/propietarios/crear
```

Ejemplo:

```json
{
  "nombre": "Paula",
  "telefono": "3001234567",
  "email": "paula@correo.com"
}
```

Requiere autenticación.

---

### Mascotas

#### Listar mascotas

```http
GET /api/mascotas/listar
```

Requiere autenticación.

#### Crear mascota

```http
POST /api/mascotas/crear
```

Ejemplo:

```json
{
  "nombre": "Max",
  "especie": "Perro",
  "raza": "Labrador",
  "propietarioId": 1
}
```

El `propietarioId` debe corresponder a un propietario existente.

---

### Veterinarios

#### Listar veterinarios

```http
GET /api/veterinarios/listar
```

Requiere autenticación.

#### Crear veterinario

```http
POST /api/veterinarios/crear
```

Ejemplo:

```json
{
  "nombre": "Dr. Andrés",
  "especialidad": "Medicina general"
}
```

Requiere rol `ADMIN`.

---

### Citas

#### Crear/agendar cita

```http
POST /api/citas/agendar
```

Ejemplo:

```json
{
  "fechaHora": "2026-10-10T15:00:00",
  "motivo": "Consulta general",
  "mascotaId": 1,
  "veterinarioId": 1
}
```

Requiere autenticación.

#### Listar citas

```http
GET /api/citas/listar
```

Requiere autenticación.

#### Filtrar citas por veterinario

```http
GET /api/citas/veterinario/{veterinarioId}
```

Ejemplo:

```http
GET /api/citas/veterinario/1
```

Requiere autenticación.

---

## 7. Validaciones y errores

Las validaciones de entrada se realizan mediante **Bean Validation** y se activan en los controllers mediante `@Valid`.

Ejemplo:

```java
@PostMapping("/crear")
public ResponseEntity<MascotaDTO> crearMascota(
        @Valid @RequestBody MascotaRequest request) {
    ...
}
```

### Validaciones principales

#### Registro

- Email obligatorio.
- Email con formato válido.
- Contraseña obligatoria.
- Contraseña con mínimo 6 caracteres.

#### Propietario

- Nombre obligatorio.
- Teléfono obligatorio.
- Teléfono de 10 cifras.
- Email obligatorio y válido.

#### Mascota

- Nombre obligatorio.
- Especie obligatoria.
- `propietarioId` obligatorio.
- El propietario debe existir.

La raza es opcional.

#### Veterinario

- Nombre obligatorio.
- Especialidad obligatoria.

#### Cita

- `fechaHora` obligatoria.
- `fechaHora` futura.
- Motivo obligatorio.
- `mascotaId` obligatorio.
- `veterinarioId` obligatorio.
- Mascota existente.
- Veterinario existente.
- No duplicar horario para el mismo veterinario.

### Respuestas HTTP utilizadas

| Código | Significado |
|---|---|
| `200 OK` | Consulta o autenticación realizada correctamente |
| `201 Created` | Recurso creado correctamente |
| `400 Bad Request` | Datos inválidos o regla de negocio incumplida |
| `403 Forbidden` | Usuario autenticado sin permisos suficientes |
| `500 Internal Server Error` | Error inesperado del servidor |

Los errores de validación y reglas de negocio se centralizan mediante `GlobalExceptionHandler`.

Los errores inesperados responden con un mensaje genérico para no exponer información interna.

---

## 8. Configuración de MySQL

La aplicación utiliza una base de datos MySQL llamada:

```text
vetturno
```

La configuración se encuentra en:

```text
src/main/resources/application.properties
```

Configuración base:

```properties
spring.application.name=vetturno

spring.datasource.url=jdbc:mysql://localhost:3306/vetturno
spring.datasource.username=root
spring.datasource.password=TU_CONTRASEÑA

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

> **Importante:** no publicar contraseñas reales en GitHub. Se recomienda utilizar variables de entorno o una configuración local para las credenciales.

Antes de iniciar la aplicación, MySQL debe estar disponible y debe existir la base de datos `vetturno`.

Con `ddl-auto=update`, Hibernate mantiene/actualiza la estructura de las tablas a partir de las entidades JPA sin eliminar los datos existentes.

---

## 9. Orden general del flujo

### Flujo de autenticación

```text
Cliente
   ↓
POST /api/auth/register
   ↓
Validación del RegistroRequest
   ↓
BCrypt para la contraseña
   ↓
Rol USER
   ↓
Guardar Usuario en MySQL
   ↓
Generar JWT
   ↓
Respuesta
```

### Flujo de login

```text
Cliente
   ↓
POST /api/auth/login
   ↓
AuthenticationManager
   ↓
Validación de email y contraseña
   ↓
Generación de JWT
   ↓
Respuesta con token
```

### Flujo de endpoint protegido

```text
Cliente
   ↓
Envía JWT
   ↓
JwtAuthFilter
   ↓
Spring Security
   ↓
Verificación de autenticación y rol
   ↓
Controller
   ↓
@Valid
   ↓
Service
   ↓
Repository
   ↓
MySQL
   ↓
Respuesta HTTP
```

### Flujo de creación de una cita

```text
POST /api/citas/agendar
          ↓
CitaRequest + @Valid
          ↓
Fecha futura
          ↓
Mascota existente
          ↓
Veterinario existente
          ↓
Horario disponible
          ↓
CitaRepository
          ↓
MySQL
          ↓
201 Created
```

---

## 10. Swagger / OpenAPI

La documentación de la API está disponible en:

```text
http://localhost:8080/swagger-ui/index.html
```

OpenAPI está configurado con:

- Nombre: **VetTurno**
- Descripción: **Veterinaria Huellitas**
- Esquema de seguridad: **Bearer JWT**

Swagger permite utilizar el botón:

```text
Authorize
```

para introducir el JWT y probar los endpoints protegidos.

Las rutas técnicas de Swagger están permitidas públicamente en Spring Security, sin abrir los endpoints de negocio.

---

## 11. Ejecución del proyecto

### Windows

Desde la carpeta raíz del proyecto:

```bash
.\mvnw.cmd spring-boot:run
```

### Linux / macOS

```bash
./mvnw spring-boot:run
```

### Empaquetar con Maven

Para generar el archivo `.jar`:

```bash
.\mvnw.cmd clean package
```

El resultado se genera dentro de:

```text
target/
```

y puede ejecutarse con:

```bash
java -jar target/vetturno-0.0.1-SNAPSHOT.jar
```

---

## 12. Pruebas realizadas

La aplicación fue probada mediante Swagger y se cuenta con evidencias para los principales escenarios del taller.

### Casos de prueba

| # | Caso | Resultado esperado |
|---:|---|---|
| 1 | Inicio de aplicación con MySQL disponible | Aplicación inicia correctamente |
| 2 | Registro válido | Usuario registrado correctamente |
| 3 | Registro con email inválido y contraseña corta | `400 Bad Request` |
| 4 | Login con credenciales válidas | `200 OK` y JWT |
| 5 | GET protegido sin token | `403 Forbidden` |
| 6 | Crear veterinario con USER | `403 Forbidden` |
| 7 | Crear veterinario con ADMIN | `201 Created` |
| 8 | Crear propietario válido | `201 Created` |
| 9 | Crear mascota con propietario existente | `201 Created` |
| 10 | Crear mascota con propietario inexistente | `400 Bad Request` |
| 11 | Crear cita futura con referencias válidas | `201 Created` |
| 12 | Crear cita con fecha pasada | `400 Bad Request` |
| 13 | Segundo intento con mismo veterinario y horario | `400 Bad Request` |
| 14 | Filtrar citas por veterinario | `200 OK` |
| 15 | Reinicio y prueba de persistencia con Swagger/Authorize | Datos persisten y flujo protegido funciona |

Las evidencias se encuentran organizadas en:

```text
docs/Evidencias Parte 1/
docs/Evidencias Parte 2/
docs/Evidencias Parte 3/
docs/Evidencias Parte 4/
docs/Evidencias Parte 5/
docs/Evidencias Parte 6/
docs/Evidencias Parte 7/
```

---

## 13. Errores frecuentes

### `403 Forbidden` al consumir un endpoint

Verificar:

- Que el JWT haya sido generado correctamente.
- Que se haya utilizado el botón **Authorize** de Swagger.
- Que el token se esté enviando en la petición.
- Que el usuario tenga el rol requerido.

### `403` al crear un veterinario

El endpoint:

```text
POST /api/veterinarios/crear
```

requiere:

```text
ADMIN
```

Un usuario con rol `USER` debe recibir `403`.

### `400` al crear una mascota

Comprobar:

- Que `nombre` no esté vacío.
- Que `especie` no esté vacía.
- Que `propietarioId` esté presente.
- Que el propietario exista en MySQL.

### `400` al crear una cita

Comprobar:

- Que `fechaHora` sea futura.
- Que `motivo` no esté vacío.
- Que `mascotaId` exista.
- Que `veterinarioId` exista.
- Que el veterinario no tenga otra cita en la misma fecha y hora.

### `500` ante una regla de negocio

Las reglas de negocio deben utilizar `BusinessException` para que `GlobalExceptionHandler` las convierta en `400`.

Por ejemplo:

```java
throw new BusinessException(
    "El veterinario ya tiene una cita en esa fecha y hora"
);
```

### Swagger no carga

Verificar:

- Que la aplicación esté ejecutándose.
- Que se esté utilizando el puerto `8080`.
- Que las rutas técnicas de Swagger estén permitidas en `SecurityConfig`.

### La aplicación no conecta con MySQL

Verificar:

- Que MySQL esté iniciado.
- Que exista la base de datos `vetturno`.
- Que el usuario sea correcto.
- Que la contraseña configurada sea correcta.
- Que el puerto de MySQL sea `3306`.
- Que la URL de conexión corresponda a la base de datos.

### Los datos desaparecen al reiniciar

Verificar la propiedad:

```properties
spring.jpa.hibernate.ddl-auto=update
```

No utilizar configuraciones que eliminen y reconstruyan las tablas en cada inicio si se necesita conservar los datos.

---

## 14. Estructura principal del proyecto

```text
vetturno/
├── docs/
│   └── Evidencias/
├── src/
│   ├── main/
│   │   ├── java/com/vetturno/vetturno/
│   │   │   ├── config/
│   │   │   ├── controller/
│   │   │   ├── dto/
│   │   │   ├── exception/
│   │   │   ├── model/
│   │   │   ├── repository/
│   │   │   ├── security/
│   │   │   └── service/
│   │   └── resources/
│   │       └── application.properties
│   └── test/
├── pom.xml
└── README.md
```

---

## 15. Resumen

VetTurno implementa una API REST para la gestión básica de una veterinaria, utilizando Spring Boot, JPA/Hibernate, MySQL y Spring Security.

El sistema combina:

- Persistencia de datos con MySQL.
- Relaciones mediante JPA.
- Validaciones con Bean Validation.
- Reglas de negocio en los servicios.
- Autenticación mediante JWT.
- Autorización mediante roles `USER` y `ADMIN`.
- Manejo global de errores.
- Documentación mediante Swagger/OpenAPI.
- Pruebas funcionales y de seguridad mediante Swagger.
