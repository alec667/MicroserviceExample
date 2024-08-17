FROM sapmachine:21-jdk-ubuntu-22.04
LABEL authors="Ale"
ADD target/ventas-service.jar ventas-service.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "ventas-service.jar"]