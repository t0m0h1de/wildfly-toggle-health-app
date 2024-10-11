FROM docker.io/maven:3.9.9-eclipse-temurin-17 as builder

WORKDIR /workspace

COPY . .

RUN mvn clean package verify

FROM quay.io/wildfly/wildfly:33.0.2.Final-jdk17

COPY --from=builder /workspace/target/ROOT.war /opt/jboss/wildfly/standalone/deployments/

ENTRYPOINT [ "/bin/sh", "-c", "/opt/jboss/wildfly/bin/standalone.sh -b 0.0.0.0" ]