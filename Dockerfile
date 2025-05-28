# STAGE 1: build WAR
FROM maven:3-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# ==== STAGE 2: runtime pe WildFly ====
FROM quay.io/wildfly/wildfly:26.1.0.Final
WORKDIR /opt/jboss/wildfly

# deploy la context /demo1
COPY --from=build /app/target/demo1-1.0-SNAPSHOT.war \
     standalone/deployments/demo1.war
# deploy ca ROOT.war (context path "/")
COPY --from=build /app/target/demo1-1.0-SNAPSHOT.war \
     standalone/deployments/ROOT.war

EXPOSE 8080
