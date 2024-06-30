# ForoHub - Oracle One Education

El propósito de esta API es proporcionar funcionalidades para un foro en línea, como crear publicaciones, comentar u obtener lista de publicaciones.

## Herramientas usadas

![Java](https://img.shields.io/badge/Java-EC2025?style=for-the-badge&logo=openjdk&logoColor=white) ![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white) ![MySQL](https://img.shields.io/badge/mysql-4479A1.svg?style=for-the-badge&logo=mysql&logoColor=white) ![Visual Studio Code](https://img.shields.io/badge/Visual%20Studio%20Code-0078d7.svg?style=for-the-badge&logo=visual-studio-code&logoColor=white) 

## Despliegue con Docker

### Clonar el repositorios

```shell
git clone https://github.com/Frikilinux/forozotta.git
```

### Configuraciones

Copiar el archivo en la raís del proyecto [env_example](https://github.com/Frikilinux/forozotta/blob/main/env_example) a `.env` con el contenido:

```ini
# .env

DB_HOST = mysql:3306
DB_NAME = forozotta
DB_USERNAME = root
DB_PASSWORD = TestSpring
JWT_SECRET = tskx2XbCLhfrYb5hSsoQSJ5Ar3sVWaPzhLwNhaocw2EggqRiSjFuQjYEkLmEuP4EWrh3ri

MYSQL_ROOT_PASSWORD = TestSpring
MYSQL_DATABASE = forozotta
PORT = 59375
```
> [!NOTE]
> Modificar la variables acorde a sus preferencias.

### Iniciar el proyecto

```shell
docker compose up --build
```

> [!NOTE]
>
> La imagen se crea a partir de `maven:3.9.7-amazoncorretto-17` y `amazoncorretto:17-alpine-jdk`

### Accediendo a la documentación

La documentación con Swagger está disponible en `http://<HOST>:>PORT>/swagger-ui.html`

![Documentación Swagger](https://ipfs.filebase.io/ipfs/QmezqdhBioKmBAV6AJcKbDb3yfSMQe5PB4WikCWwx3vU2m)

### Diagrama MySQL

Las Tablas será creadas con Flyway la primera vez que inicie el proyecto.

![Diagrama MySQL](https://ipfs.filebase.io/ipfs/Qmf5RzPd58wn3rFDUQetDsqhxKnxQm9mMitPsTiBRGmFCS)

> [!NOTE]
>
> El archivo [V1_1__InsertExampleData.sql](https://github.com/Frikilinux/forozotta/blob/main/src/main/resources/db/migration/V1_1__InsertExampleData.sql) contiene unos registros de ejemplo, que serán añadidos a la base de datos automáticamente.
> La contraseña cifrada con Bcrypt para todos los usuarios es: 654321
