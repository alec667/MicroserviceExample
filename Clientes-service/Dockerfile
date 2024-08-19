FROM sapmachine:21-jdk-ubuntu-22.04
LABEL authors="Ale"
ADD target/clientes-service.jar clientes-service.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "clientes-service.jar"]