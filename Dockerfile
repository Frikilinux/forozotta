FROM maven:3.9.7-amazoncorretto-17 as builder

COPY src /usr/src/app/src
COPY pom.xml /usr/src/app

RUN mvn -f /usr/src/app/pom.xml clean package

FROM amazoncorretto:17-alpine-jdk

COPY --from=builder /usr/src/app/target/forozotta-0.0.1-SNAPSHOT.jar /usr/app/forozotta-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-cp", "/usr/app/forozotta-0.0.1-SNAPSHOT.jar"]
