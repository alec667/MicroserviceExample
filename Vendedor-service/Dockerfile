FROM sapmachine:21-jdk-ubuntu-22.04
LABEL authors="Ale"
ADD target/vendedor-service.jar vendedor-service.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "vendedor-service.jar"]