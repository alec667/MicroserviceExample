FROM sapmachine:21-jdk-ubuntu-22.04
LABEL authors="Ale"
ADD target/api-gateway.jar api-gateway.jar
EXPOSE 8765
ENTRYPOINT ["java", "-jar", "api-gateway.jar"]