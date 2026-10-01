## Explicación de la diferencia entre JPA, Hibernate y JpaRepository.

### JPA (Java Persistence API)
- JPA es una especificación de Java que define cómo interactuar con bases de datos relacionales utilizando objetos Java. Proporciona un conjunto de reglas y anotaciones para mapear clases Java a tablas de bases de datos y realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) de manera sencilla.
- JPA no es una implementación concreta, sino una interfaz que permite a los desarrolladores trabajar con diferentes proveedores de persistencia, como Hibernate, EclipseLink, etc.

### Hibernate
- Hibernate es una implementación concreta de JPA. Es un framework ORM (Object-Relational Mapping) que facilita la persistencia de objetos Java en bases de datos relacionales. Hibernate proporciona características adicionales, como caché de segundo nivel, consultas HQL (Hibernate Query Language) y soporte para transacciones.
- Hibernate implementa las especificaciones de JPA y agrega funcionalidades propias, lo que lo convierte en una opción popular para la persistencia de datos en aplicaciones Java.

### JpaRepository
- JpaRepository es una interfaz proporcionada por Spring Data JPA que extiende la funcionalidad de JPA y Hibernate. Proporciona métodos predefinidos para realizar operaciones CRUD y consultas personalizadas de manera más sencilla. Al extender JpaRepository, los desarrolladores pueden aprovechar la implementación automática    de métodos como `save()`, `findById()`, `findAll()`, `deleteById()`, entre otros, sin necesidad de escribir código adicional.