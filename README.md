# Vehicle Renting Approval

API REST de Scoring con autoaprobación de solicitudes. Se pueden consultar sus endpoints actualizados después de instalarlo en la documentación de swagger pudiendo ser consultada [aquí](http://localhost:8080/swagger-ui/index.html). A continuación se muestra el estado de la documentación a dia 13/04/2023:

![plot](src/main/resources/images/documentacion.png)



# Reglas de aprobación

Para que una solicitud de renting sea preaprobada automáticamente deben cumplirse todas las reglas de aprobación y ninguna de denegación.

![plot](src/main/resources/images/reglas_aprobacion.png)

![plot](src/main/resources/images/reglas_denegacion.png)



# Cómo instalar
## Dependencias


### Java 11 -> [Enlace](https://oracle.com/es/java/technologies/javase/jdk11-archive-downloads.html)

### Maven-> [Enlace](https://maven.apache.org/download.cgi)

## Instalación y ejecución
+ Clonar el repositorio
```
git clone https://github.com/andresguijarro-babel/VehicleRentingApproval.git
```

+ Entrar en el directorio
  
```
cd VehicleRentingApproval
```

+ Arrancarlo con Maven

 ```
mvn spring:boot run
```
## Consultar la documentación una vez instalado y arrancado

```
http://localhost:8080/swagger-ui/index.html
```

## Spring Security
+ Solicitar autenticación: recibir Bearer token
> Debe existir una persona en la BD con email y password encryptada (Bcrypt)
```
via Postman
http://localhost:8080/login
{
    "email": "rafael.vera@babelgroup.com",
    "password": "Marte2025"
}
```
+ Enviar token recibido junto a llamada a endpoints:

```
  via Postman. Ejemplo:
  http://localhost:8080/listarTiposResolucion
    Authorization > Type: Bearer token > Token: (pegar token recibido anteriormente)
```

## Jascrypt Encriptación
+ Encriptar: sustituir Password y value por tu contraseña de encriptación y el valor a encriptar
```
En la Terminal apuntando al directorio del proyecto:
mvn jasypt:encrypt-value "-Djasypt.encryptor.password=Password"-Djasypt.plugin.value=Valor"
```
+ En application properties sustituir la clave a encriptar por el resultado obtenido de encriptación
```
spring.datasource.password=ENC(5uyc6cFIFqQgka+Yx2txrbg+NS1z91HPYjMruBd8KkK0r6EeVMWWyhLuP8Yn/+lI)
```
+ anotación @@EnableEncryptableProperties en el método MAIN

+ Run/debug Configuration (en el IDE Intellij)
```
Habilitar MV options y pegar en el campo corespondiente (sustituye pasword por la constraseña):
-Djasypt.encryptor.password=Password
```