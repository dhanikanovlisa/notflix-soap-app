FROM openjdk:11
COPY ./target /app
WORKDIR /app
EXPOSE 3030
ENTRYPOINT ["java", "-jar", "service_soap-jar-with-dependencies.jar"]