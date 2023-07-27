FROM gradle:6.8.0-jdk11 AS build
COPY . /app
WORKDIR /app
COPY . /src
WORKDIR /src
RUN gradle fatJar
FROM openjdk:11-jre-slim
EXPOSE 8090
RUN mkdir application
COPY --from=build /src/app/build/libs/app-1.0.jar application/app-1.0.jar
WORKDIR /application
ENTRYPOINT ["java", "-jar", "app-1.0.jar"]
