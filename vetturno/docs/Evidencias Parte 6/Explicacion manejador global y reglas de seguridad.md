# ¿Por qué el manejador global no reemplaza las reglas de seguridad?

El manejador global de excepciones (`GlobalExceptionHandler`) y las reglas de seguridad cumplen funciones diferentes dentro de la aplicación.

El **manejador global** se encarga de capturar y estandarizar los errores que ocurren durante el procesamiento de las solicitudes. Por ejemplo, puede transformar errores de validación o reglas de negocio en respuestas HTTP con código `400`, y manejar errores inesperados con código `500`, evitando mostrar información interna de la aplicación.

Sin embargo, el manejador global **no reemplaza las reglas de seguridad**, porque no es el encargado de decidir si un usuario tiene permiso para acceder a un recurso.

Las reglas de seguridad se encargan de controlar el **acceso y la autorización** de los usuarios según su autenticación y rol. En el proyecto VetTurno, por ejemplo, cualquier usuario puede realizar determinadas operaciones, mientras que el registro de veterinarios está restringido a usuarios con rol `ADMIN`.

Por lo tanto, si un usuario con rol `USER` intenta registrar un veterinario, la solicitud debe ser bloqueada por las reglas de seguridad y responder con un **`403 Forbidden`**. No corresponde tratar este caso como una excepción de validación o una regla de negocio que el `GlobalExceptionHandler` deba convertir en `400`.

En resumen:

* **Seguridad:** determina si el usuario tiene permiso para realizar una operación (`403` cuando no está autorizado).
* **Validaciones:** comprueban que los datos recibidos tengan el formato y contenido requerido (`400`).
* **Reglas de negocio:** comprueban condiciones propias de la operación, como evitar una cita duplicada (`400`).
* **Manejador global:** centraliza la forma en que se responden los errores y evita exponer detalles internos.

Por esta razón, el `GlobalExceptionHandler` complementa el sistema de seguridad, pero **no lo reemplaza**. Cada componente mantiene una responsabilidad específica dentro de la aplicación.
