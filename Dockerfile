#Build the Maven project
# FROM maven:3.9.6-amazoncorretto-21-al2023 as builder
# FROM maven:3.9.8-sapmachine-21 AS builder
FROM maven:4.0.0-rc-4-eclipse-temurin-25 AS builder
COPY . /usr/src/app
WORKDIR /usr/src/app
RUN mvn clean install

#Build the Tomcat container
FROM tomcat:jre25
#set environment variables below and uncomment the line. Or, you can manually set your environment on your server.
#ENV JDBC_URL=jdbc:postgresql://<host>:<port>/<database> JDBC_USERNAME=<username> JDBC_PASSWORD=<password>

# Copy GT-FHIR war file to webapps.
COPY --from=builder /usr/src/app/fhir-fhirbase-server/target/fhir-fhirbase-server.war $CATALINA_HOME/webapps/mdi-fhir-server.war

EXPOSE 8080
