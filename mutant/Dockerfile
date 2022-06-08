#step 1
#docker build -t dockerrestpg -f Dockerfile .
#docker run -p 8080:8080 -t dockerrestpg
#paso dos y tres no se utilizan pero se puede utilizar ciando necesitemos enviar archivos al contenedor de docker
#step 2 #revisamos dentro del contenedor los directorioa para la ruta de wapapps de tomcat pero no se utiliza
#docker exec -it 4c59aa855f80 bash /bin/sh -- ls appdoc inicio el basch de docker en tomcat
#step 3 //copiandolo al directorio del contenedor de docker pero se omotio paso 3
#ya que al crear el jar me encapsula un tomcat dentrol del jar que tiene el app
#docker cp 'C:\projects_spring_tools_suite\gs-rest-service\complete\target\rest-service-0.0.1-SNAPSHOT.war' 53716fbdf054:'/usr/local/tomcat/webapps'
#step 4 #ejecutamos comandos composed
#docker-compose stop #para el composedque tiene los contenedores del archivo docker-compose.yml
#docker-compose rm #eliminamos el compose actual
#docker-compose up -d #ejecuta el compose para subir los servicios de los contenedores en background
#docker-compose up --build #Ejecutamos la imagenes para lanzar los contenedores de postgres y el cliente aap.jar
#FROM openjdk:8-jdk-alpine
#docker run -d -p 5432:5432 --name postgres -e POSTGRES_PASSWORD=JAmbg172 -e POSTGRES_USER=postgres -e POSTGRES_URL=jdbc:postgresql://localhost:5432/usuarios -e POSTGRES_DB=usuarios  -v posgresdocker-data:/var/lib/postgres 
#docker run -d -p 5432:5432 -v pgdata:/var/lib/postgresql/data -e POSTGRES_USER=postgres -e POSTGRES_URL=jdbc:postgresql://localhost:5432/usuarios -e POSTGRES_DB=usuarios -e POSTGRES_PASSWORD=JAmbg172 postgres
FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]