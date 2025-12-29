# 📚 ClubLectura - Backend

Una API REST para la gestión de clubs de lectura, desarrollada con Spring Boot como proyecto de aprendizaje de tecnologías backend modernas.

Para ver el proyecto del frontend: https://github.com/PMontesi/ClubLecutra-Frontend

## ✨ Características

- **API RESTful**: Endpoints bien estructurados para gestión completa
- **Autenticación JWT**: Sistema seguro de autenticación y autorización
- **Gestión de Clubs**: Crear, administrar y gestionar clubs de lectura
- **Sistema de Items**: Soporte para libros, películas y series con ratings
- **Base de Datos Relacional**: PostgreSQL con JPA/Hibernate

## 🛠️ Tecnologías Utilizadas

- **Java 17** - Lenguaje principal
- **Spring Boot 3** - Framework backend
- **Spring Security** - Autenticación y autorización
- **JWT** - Tokens de autenticación
- **Spring Data JPA** - Acceso a datos
- **PostgreSQL** - Base de datos
- **Maven** - Gestión de dependencias

## 🚀 Instalación

```bash
# Clona el repositorio
git clone https://github.com/PMontesi/ClubLecutra-Backend.git

# Instalar dependencias
mvn install

# Ejecutar el proyecto
mvn spring-boot:run
```

Recuerda configurar la base de datos PostgreSQL antes de ejecutar la aplicación!

## 📁 Estructura del Proyecto

```
src/
├── main/
│   ├── java/com/example/ClubLectura_backend/
│   │   ├── controllers/      # Controladores REST
│   │   ├── entities/         # Entidades JPA
│   │   ├── DTOs/            # Objetos de Transferencia de Datos
│   │   ├── repositories/    # Repositorios de datos
│   │   ├── services/        # Lógica de negocio
│   │   ├── security/        # Configuración de seguridad
│   │   └── exceptions/      # Manejo de excepciones
│   └── resources/
│       ├── application.properties    # Configuración
│       └── static/          # Recursos estáticos
```
