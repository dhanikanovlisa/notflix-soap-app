# FROM amazoncorretto:19
# COPY ./target /app
# WORKDIR /app
# EXPOSE 3030
# ENTRYPOINT ["java", "-jar", "service_soap-jar-with-dependencies.jar"]

# FROM maven:3.8-amazoncorretto-19 AS build
# WORKDIR /app
# COPY pom.xml ./

# COPY src ./src

# RUN mvn clean install

# FROM amazoncorretto:19

# COPY --from=build ./app/target /app
# WORKDIR /app

# EXPOSE 3030

# ENTRYPOINT ["java", "-jar", "service_soap-jar-with-dependencies.jar"]

FROM maven:3-amazoncorretto-8 AS build

WORKDIR /app

COPY pom.xml ./

COPY src ./src

RUN --mount=type=cache,target=/root/.m2 mvn clean install assembly:single

FROM amazoncorretto:8

WORKDIR /app

COPY --from=build /app/target/service_soap-jar-with-dependencies.jar /app/service_soap-jar-with-dependencies.jar

EXPOSE 3003

ENTRYPOINT ["java","-jar","service_soap-jar-with-dependencies.jar"]