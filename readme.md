# ForoHub - Oracle One Education

El propósito de esta API es proporcionar funcionalidades para un foro en línea, como crear publicaciones, comentar, votar y obtener hilos de discusión.

## Herramientas usadas

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![MySQL](https://img.shields.io/badge/mysql-4479A1.svg?style=for-the-badge&logo=mysql&logoColor=white) ![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)
![Visual Studio Code](https://img.shields.io/badge/Visual%20Studio%20Code-0078d7.svg?style=for-the-badge&logo=visual-studio-code&logoColor=white) 

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

Modificar la variables acorde a sus preferencias.

### Iniciar el proyecto

```sh
docker compose up --build
```



> [!NOTE]
>
> La imagen se crea a partir de `maven:3.9.7-amazoncorretto-17` y `amazoncorretto:17-alpine-jdk`



### Accediendo a la documentación

La documentación con Swagger está disponible en `http://<HOST>:>PORT>/swagger-ui/index.html`



