FROM openjdk:21-jdk

WORKDIR /app
COPY target/product-maintainer-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app/app.jar"]