FROM maven:3.8.1-jdk-11-slim AS build
COPY pom.xml ./
COPY src ./src
RUN mvn -B clean package

FROM openjdk:11.0.6-jre
MAINTAINER Pedro Miguel Rodriguez <petermichael85@gmail.com>
EXPOSE 8081
COPY --from=build /target/backend*.jar backend.jar
ENTRYPOINT java -jar backend.jar