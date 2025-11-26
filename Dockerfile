# 1. Etapa de Construcción (Build)
# Usamos una imagen que YA tiene Gradle instalado (más seguro y rápido)
FROM gradle:8.5-jdk17 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
# Compilamos saltando los tests para ir más rápido
RUN gradle bootJar --no-daemon -x test

# 2. Etapa de Ejecución (Run)
# Usamos Eclipse Temurin (El reemplazo oficial y seguro de openjdk)
FROM eclipse-temurin:17-jdk-alpine
EXPOSE 8080
# El truco del asterisco: Copia cualquier JAR que encuentre
COPY --from=build /home/gradle/src/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]