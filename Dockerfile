FROM maven:3.9.6-eclipse-temurin-21

COPY . /opt/playground-java-21
WORKDIR /opt/playground-java-21
RUN mvn clean verify

ENTRYPOINT ["java", "-jar", "target/benchmarks.jar"]