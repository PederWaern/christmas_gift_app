FROM openjdk:8

RUN mkdir /app

COPY ./target/christmas_gift_backend-0.0.1-SNAPSHOT.jar /app

WORKDIR /app

CMD ["java", "-jar", "christmas_gift_backend-0.0.1-SNAPSHOT.jar"]
