FROM openjdk:8-jdk-alpine
FROM maven:3.5.2-jdk-8-alpine AS MAVEN_TOOL_CHAIN
COPY pom.xml /tmp/
COPY src /tmp/src/
WORKDIR /tmp/
RUN mvn package
ARG JAR_FILE=/tmp/target/swagger-spring-1.0.0.jar
COPY /tmp/target/swagger-spring-1.0.0.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]