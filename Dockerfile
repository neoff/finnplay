FROM openjdk:17-jdk-slim-buster AS builder

RUN apt-get update -y
RUN apt-get install -y binutils

WORKDIR /app

COPY . .

RUN ./mvnw clean package -DskipTests

# lightweight image
FROM bellsoft/liberica-openjre-alpine-musl:17.0.5-8
WORKDIR /app
COPY --from=builder /app/target/finnplay-0.0.1-SNAPSHOT.jar /app

ENTRYPOINT java -jar /app/finnplay-0.0.1-SNAPSHOT.jar