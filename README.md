
# proyecto_freemarket
--Requerimientos 
1. JDK 11+
2. Mongo DB

--Pasos de Instalación con su IDE favorito recomendado STS(SUIT TOOLS DE SPRING) O INTELLJ IDEA
1. Instalar MONGO DB
2. Importar proyecto desde maven
3. complilar 
4. Empaquetar en jar
5. en el directorio del proyecto una subcarpeta target(C:\proyecto_freemarket\mutant\target), ubicar el jar 
6. Copiarlo en la carpeta de su preferencia y desde consola ejecutar java -jar miapp.jar
7. probar servicios desde postman URLS (http://localhost:8080/api/stats =GET, http://localhost:8080/api/mutant =POST, http://localhost:8080/api/mutantes =GET)