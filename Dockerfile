FROM amazoncorretto:19
COPY ./target /app
WORKDIR /app
EXPOSE 3030
ENTRYPOINT ["java", "-jar", "service_soap-jar-with-dependencies.jar"]

#FROM maven:3.8-amazoncorretto-19 AS build
#COPY . /app
#WORKDIR /app
#
#RUN mvn clean install
#
#FROM amazoncorretto:19
#
#COPY --from=build ./app/target /app
#WORKDIR /app
#
#EXPOSE 3030
#
#ENTRYPOINT ["java", "-jar", "service_soap-jar-with-dependencies.jar"]
