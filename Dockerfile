#Build the Maven project
FROM maven:3.9.6-amazoncorretto-21-al2023 AS builder
# FROM maven:3.9.8-sapmachine-21 AS builder
# FROM maven:3.9.10-eclipse-temurin-21-noble AS builder
COPY . /usr/src/app
WORKDIR /usr/src/app
RUN mvn clean install

#Build the Tomcat container
# FROM tomcat:jre25
FROM tomcat:11.0-jre21-temurin-jammy
#set environment variables below and uncomment the line. Or, you can manually set your environment on your server.
#ENV JDBC_URL=jdbc:postgresql://<host>:<port>/<database> JDBC_USERNAME=<username> JDBC_PASSWORD=<password>

# Replace the hardcoded port="8080" with a dynamic system property ${port.http}
RUN sed -i 's/port="8080"/port="${port.http}"/g' ${CATALINA_HOME}/conf/server.xml

# Define a default runtime environment variable for convenience
ENV TOMCAT_PORT=8080

# Expose the default Tomcat port
EXPOSE 8080

# Copy GT-FHIR war file to webapps.
ENV SERVER_PATH=mdi-fhir-server

COPY --from=builder /usr/src/app/fhir-fhirbase-server/target/fhir-fhirbase-server.war $CATALINA_HOME/webapps/mdi-fhir-server.war
COPY runtime-setup.sh /usr/local/bin/
CMD ["runtime-setup.sh", "catalina.sh", "run"]