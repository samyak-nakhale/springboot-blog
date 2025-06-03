FROM maven:3.9.5-eclipse-temurin-21-alpine AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:21-jdk-slim
COPY --from=build /target/blog-one-0.0.1-SNAPSHOT.jar blog.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","blog.jar"]
