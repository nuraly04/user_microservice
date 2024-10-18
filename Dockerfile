FROM gradle:7.6.0-jdk17 AS build
WORKDIR /app

COPY build.gradle settings.gradle ./
COPY src ./src
RUN gradle build --no-daemon -x test

FROM openjdk:17-jdk-slim as runtime
WORKDIR /app

COPY --from=build /app/build/libs/user_microservice-0.0.1-SNAPSHOT.jar /app/service.jar
COPY --from=build /app/src/main/resources /app/resources

EXPOSE 8082
ENTRYPOINT ["java", "-jar", "/app/service.jar"]