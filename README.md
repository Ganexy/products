### Versiones 

* Java: 17
* Spring Boot: 3.4.1
* OpenAPI: 2.1.0

### Dependencias 

**OpenAPI**

-  Documentación de API con Swagger

- [Local Swagger](http://localhost:8080/swagger-ui/index.html)

**H2 Database**

- Base de datos en memoria para las pruebas

- Usuario: as
- Password:
- JDBC URL: jdbc:h2:mem:testdb
- [H2 Loggin](http://localhost:8080/h2-console/login.jsp)


### Comentarios
* En este escenario, al no encontrar un precio que cumpla con los requisitos de brandId, productId y la fecha requerida he decidido devolver una excepción personalizada.