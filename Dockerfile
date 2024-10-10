FROM docker.io/maven:3.9.9-eclipse-temurin-17

WORKDIR /app

COPY . .

RUN mvn clean package verify

ENTRYPOINT [ "/bin/sh", "-c", "/app/target/server/bin/standalone.sh" ]