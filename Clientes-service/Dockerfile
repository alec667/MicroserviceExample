FROM ubuntu:latest
LABEL authors="Ale"
ADD target/clientes-service.jar clientes-service.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "clientes-service.jar"]