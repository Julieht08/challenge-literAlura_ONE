# challenge-literAlura_ONE 📚✨

Desafío de una pequeña app de consola, haciendo uso de la **API gutendex** para trabajar los datos de libros, con un **menú interactivo** con el usuario, 
para que seleccione las opciones e ingrese datos. Incluyendo uso de gestor de bases de datos como **PostgreSQL**, para el uso de una **base de datos** con 
el fin de almacenar y recuperar datos acerca de los libos y de los autores de la API. El desarrollo de este proyecto es para completar un challenge para 
**Alura Latam y ONE - Oracle Next Education**.

## 📋 Características principales:

- Aplicación de consola con un menú interactivo para que el usuario pueda seleccionar la opción que desee.
![1](https://github.com/user-attachments/assets/200db9b4-14aa-4b3f-a81b-30d5f44a74ad)

## 🛠️ Tecnologías utilizadas:

- **Java**: Lenguaje principal para el desarrollo del proyecto.
- **Spring Boot**: Framework para facilitar el desarrollo de aplicaciones basadas en Java.
- **JPA (Java Persistence API)**: Para la gestión de la base de datos.
- **Gutendex API**: Fuente de datos externa para información sobre libros y autores.
- **PostgreSQL**: Gestor de base de datos.
- **Maven**: Herramienta de gestión de dependencias y construcción.

## 📋 Requisitos previos:

Antes de ejecutar el proyecto, asegúrate de tener instalado en su equipo lo siguiente:

- **Intellij IDEA**
- **Java 17 o superior**
- **PostgreSQL**

## 🚀 Instalación:

Siga los siguientes pasos para poder hacer uso del proyecto:

1. Clona este repositorio:

   ```bash
   git clone https://github.com/tu_usuario/challenge_literAlura_one.git
   ```

2. O descarge directamente a su equipo la carpeta:

3. Cree una base de datos en postgreSQL:

4. Ajuste las variables de entorno para poder usar la base de datos:

DB_HOST: es el nombre que le da al  equipo.

DB_NAME: es el nombre que le da a la base de datos.

DB_USER: es el nombre de usuario en postgreSQL.

DB_PASSWORD: es la contraseña en postgreSQL.

## 📚 Uso:

Al iniciar la aplicación, se mostrará un menú interactivo en la consola con las siguientes opciones:

1. 🔍** Buscar Libros por Título/Nombre**: Ingresa un título para buscar información en la API de Gutendex y guardarlo en la base de datos si no está registrado.
![2](https://github.com/user-attachments/assets/ad18a065-7220-4ba1-a36c-034fa975bd9a)

2. 📋** Mostrar lista de los Libros**: Lista todos los libros registrados en la base de datos.
![3](https://github.com/user-attachments/assets/050b209b-6a4b-46a1-8b0a-63f033b08fe9)

3. 👥** Mostrar lista de los Autores**: Lista todos los autores registrados en la base de datos.
![4](https://github.com/user-attachments/assets/708a4b60-ebca-4328-a6df-21b3fc959145)

4. 📆** Mostrar lista de los Autores vivos según un determinado año**: Filtra y muestra autores vivos en un año específico.
![5](https://github.com/user-attachments/assets/d7d2b10b-8a7c-4dce-897a-2f3a87441c15)

5. 🌍** Mostrar lista de la cantidad de libros por determinado idioma**: Filtra libros por idioma (inglés, español, francés, portugués).
![6](https://github.com/user-attachments/assets/67fbbb96-1f33-4358-aec7-93f55b30546b)

6. 🔎** Buscar Autores por su nombre**: Permite buscar autores registrados por nombre.
![7](https://github.com/user-attachments/assets/efd2d9ef-f687-4316-8a9c-5f38c156e7df)

7. 🏆** Mostrar el Top 10 de los Libros más descargados**: Lista los 1o libros más descargados según la base de datos.
![8](https://github.com/user-attachments/assets/f919e195-1c79-45e5-b960-1cb8333f3dd4)

O. ❌** Salir**: Cierra la aplicación.

## ⚙️ Configuración de la base de datos:

El proyecto utiliza una base de datos postgreSQL. La configuración se encuentra en el archivo `application.properties`:

```properties
spring.application.name=chalenge-literAlura-one
spring.datasource.url=jdbc:postgresql://${DB_HOST}/${DB_NAME}
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASSWORD}
spring.datasource.driver-class-name=org.postgresql.Driver
hibernate.dialect=org.hibernate.dialect.HSQLDialect

spring.jpa.hibernate.ddl-auto=update

spring.jpa.show-sql=true
spring.jpa.format-sql=true
```
## Visualización de las tablas en la base de datos:

De esta manera se observan los datos gusrdados en la base de datos:

- Tabla libros:
![9](https://github.com/user-attachments/assets/2e66d319-5998-4ae1-9a0c-92b0c7352572)

- Tabla autores:

![10](https://github.com/user-attachments/assets/a1943967-185e-4237-9ed1-63e79eead0b0)






