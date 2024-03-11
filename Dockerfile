FROM openjdk:17-oracle
ARG JAR_FILE=*.jar
COPY build/libs/*.jar application.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "application.jar"]