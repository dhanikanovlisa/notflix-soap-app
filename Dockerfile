#FROM openjdk:21
#COPY ./target /app
#WORKDIR /app
#EXPOSE 3030
#ENTRYPOINT ["java", "-jar", "service_soap-jar-with-dependencies.jar"]

FROM maven:3.9.5-amazoncorretto-21 AS build
COPY . /app
WORKDIR /app

RUN mvn clean install

FROM amazoncorretto:21

COPY --from=build ./app/target /app
WORKDIR /app

EXPOSE 3030

ENTRYPOINT ["java", "-jar", "service_soap-jar-with-dependencies.jar"]
