# Gestor-Presupuestos-Personales-Microservicios

## Descripción

**Gestor de Presupuestos Personales - Microservicios** es el backend basado en una arquitectura de microservicios que soporta la aplicación frontend Gestor de Presupuestos Personales - Angular. Este proyecto gestiona la lógica de negocio y el almacenamiento de datos para la gestión de finanzas personales, permitiendo añadir ingresos y gastos, establecer metas financieras (por ejemplo, ahorrar para comprar un coche) y generar reportes financieros. Los datos se almacenan en bases de datos MySQL separadas para usuarios, metas y transacciones, y el backend proporciona una API REST para interactuar con el frontend.

El proyecto está diseñado para ser escalable y modular, utilizando microservicios para separar funcionalidades clave. **El proyecto está finalizado**, pero abierto a mejoras y contribuciones futuras.

## Características principales

- 📊 **Registro de transacciones**: Soporte para registrar ingresos y gastos en categorías específicas.
- 💰 **Metas financieras**: Permite crear y gestionar metas de ahorro, como ahorrar una cantidad específica para un objetivo (por ejemplo, comprar un coche).
- 📈 **Reportes**: Proporciona datos para generar gráficos financieros en el frontend.
- 🔒 **Autenticación**: Gestiona tokens JWT para usuarios (almacenados en LocalStorage en el frontend).
- 🐳 **Dockerizado**: Configurado para ejecutarse en contenedores Docker usando Docker Compose.

## Tecnologías utilizadas

- **Spring Boot**: Framework principal para el desarrollo de los microservicios.
- **Spring Cloud Config Server**: Gestión centralizada de configuraciones.
- **Eureka Discovery Server**: Registro y descubrimiento de servicios.
- **Spring Data JPA & MySQL**: Persistencia de datos relacionales en bases de datos separadas para usuarios, metas y transacciones.
- **Feign Client**: Comunicación entre microservicios.
- **Maven**: Gestor de dependencias.
- **Docker y Docker Compose**: Para contenedorización y despliegue consistente.
- **API REST**: Endpoints para comunicación con el frontend Angular.

## Requisitos previos

- **Docker** y **Docker Compose**: Requerido para la ejecución en contenedores.
- **Git**: Para clonar el repositorio.
- **Git Bash**: Necesario para ejecutar el script `run.sh` en la raíz del proyecto (en Windows o entornos compatibles).
- **MySQL**: Bases de datos configuradas y accesibles (definidas en `docker-compose.yml` como `mysql-usuario`, `mysql-metas`, y `mysql-transacciones`).
- Frontend configurado: Ver Gestor-Presupuestos-Personales-Angular.

## Instalación

El proyecto se ejecuta utilizando **Docker Compose** para gestionar múltiples contenedores (microservicios y bases de datos MySQL). Sigue estos pasos para configurarlo:

1. **Clona el repositorio**:

   ```bash
   git clone https://github.com/CarlosPachecoFr/Gestor-Presupuestos-Personales-Microservicios.git
   ```

2. **Accede al directorio del proyecto**:

   ```bash
   cd Gestor-Presupuestos-Personales-Microservicios
   ```

3. **Configura las variables de entorno en** `run.sh`:

   - Copia el archivo `run.sh.example` a `run.sh`:

     ```bash
     cp run.sh.example run.sh
     ```
   - Edita `run.sh` para definir las variables sensibles (por ejemplo, `PASSWORD_DATABASE`, `JWT_SECRET_KEY`). Ejemplo:

     ```bash
     export PASSWORD_DATABASE="tu_contraseña"
     export USERNAME_DATABASE="tu_usuario"
     export URL_DATABASE_USUARIOS="jdbc:mysql://mysql-usuario:3306/tu_base_de_datos_usuarios"
     export URL_DATABASE_METAS="jdbc:mysql://mysql-metas:3306/tu_base_de_datos_metas"
     export URL_DATABASE_TRANSACCIONES="jdbc:mysql://mysql-transacciones:3306/tu_base_de_datos_transacciones"
     export JWT_SECRET_KEY="tu_clave_secreta_de_32_caracteres"
     ```
   - Asegúrate de personalizar estas variables con valores reales para tu entorno. El archivo `run.sh` está excluido en `.gitignore` para evitar subir información sensible.

4. **Construye y ejecuta los contenedores**:

   - Usa el script `run.sh` para crear la red `gpp_network` y ejecutar Docker Compose. Desde **Git Bash**, en la raíz del proyecto, ejecuta:

     ```bash
     chmod +x run.sh
     ./run.sh
     ```
   - Esto construye las imágenes Docker y despliega los contenedores, incluyendo los microservicios y las bases de datos MySQL. La API estará disponible en `http://localhost:8080` (o el puerto configurado en tu `docker-compose.yml`).

**Nota**: Asegúrate de que las bases de datos MySQL (`mysql-usuario`, `mysql-metas`, `mysql-transacciones`) estén definidas en tu `docker-compose.yml` y sean accesibles. El frontend ya está configurado para conectarse a la API del backend.

## Conexión con el frontend

El backend proporciona una API REST que el frontend Angular consume para gestionar datos. Para conectar el frontend:

1. Clona el repositorio del frontend:

   ```bash
   git clone https://github.com/CarlosPachecoFr/Gestor-Presupuestos-Personales-Angular.git
   ```

2. Sigue las instrucciones del README del frontend para ejecutarlo. La URL de la API ya está configurada en los archivos `src/environments/environment.ts` y `src/environments/environment.prod.ts`.

## Uso

- **Endpoints principales**:

  - `POST /gpp/transaccion/crearTransaccion`: Añade un ingreso o gasto.
  - `GET /gpp/transacciones/obtenerTransacciones`: Obtiene la lista de transacciones.
  - `POST /gpp/metas/crearMeta`: Crea una meta financiera.
  - `GET /gpp/metas/obtenerMetasUsuarioId`: Obtiene la lista de metas financieras.
  - `GET /gpp/transacciones/exportarArchivo`: Genera datos para reportes financieros.

- Los datos se almacenan en bases de datos MySQL separadas para usuarios, metas y transacciones, y el frontend utiliza los endpoints para mostrar y gestionar la información.

## Contribuir

¡Las contribuciones son bienvenidas! Si deseas proponer mejoras o nuevas funcionalidades, sigue estos pasos:

1. Haz un **fork** del repositorio.
2. Crea una nueva rama (`git checkout -b feature/nueva-funcionalidad`).
3. Realiza tus cambios y haz **commit** (`git commit -m 'Añadir nueva funcionalidad'`).
4. Sube los cambios a tu fork (`git push origin feature/nueva-funcionalidad`).
5. Abre un **Pull Request** en este repositorio.

Por favor, asegúrate de seguir las guías de contribución (en desarrollo) y mantener un código limpio y bien documentado.

## Estado del proyecto

Este proyecto está **finalizado**, pero abierto a mejoras futuras, como la posibilidad de editar o eliminar transacciones. Revisa las issues para conocer las propuestas de mejora o errores reportados.

## Licencia

Este proyecto está bajo la Licencia MIT. Consulta el archivo `LICENSE` para más detalles.

## Contacto

Si tienes preguntas, sugerencias o quieres colaborar, puedes contactarme a través de:

- **GitHub**: CarlosPachecoFr
- **Correo**: carlospachecofrutos@gmail.com

¡Gracias por tu interés en **Gestor de Presupuestos Personales - Microservicios**! 🚀
