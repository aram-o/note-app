FROM openjdk:11-jdk-slim
COPY target/noteservice.jar /opt/noteservice.jar

ENTRYPOINT ["java", "-Djava.net.preferIPv4Stack=true", "-jar", "/opt/noteservice.jar"]
EXPOSE 8080