FROM openjdk:21-slim-jdk

WORKDIR /app
COPY ./target/product-maintainer-0.0.1-SNAPSHOT.jar product-maintainer.jar

RUN addgroup --system javauser && adduser --system --ingroup javauser javauser
USER javauser

EXPOSE 8080
ENTRYPOINT ["java","-jar","product-maintainer.jar"]