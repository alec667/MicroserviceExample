FROM sapmachine:21-jdk-ubuntu-22.04
LABEL authors="Ale"
ADD target/service-registry.jar service-registry.jar
EXPOSE 8761
ENTRYPOINT ["java", "-jar", "service-registry.jar"]