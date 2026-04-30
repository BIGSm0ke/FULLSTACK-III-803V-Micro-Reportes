# Usamos una imagen base de Java 17
FROM eclipse-temurin:17-jdk-alpine

# Directorio de trabajo
WORKDIR /app

# Copiamos el JAR generado (asegúrate de hacer 'mvn clean package' antes)
COPY target/ms-reportes-0.0.1-SNAPSHOT.jar app.jar

# Exponemos el puerto
EXPOSE 8082

# Comando para ejecutar
ENTRYPOINT ["java", "-jar", "app.jar"]