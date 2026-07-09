#!/bin/bash
set -e

# Set CATALINA_OPTS based on TOMCAT_PORT (from env.list at runtime)
if [ -n "${TOMCAT_PORT}" ]; then
  export CATALINA_OPTS="-Dcatalina.http.port=${TOMCAT_PORT} -Dport.http=${TOMCAT_PORT}"
  echo "Setting CATALINA_OPTS to: ${CATALINA_OPTS}"
else
  echo "TOMCAT_PORT not set, using default"
fi

# Handle SERVER_PATH for WAR file naming
if [ -n "${SERVER_PATH}" ]; then
  WAR_SOURCE="${CATALINA_HOME}/webapps/mdi-fhir-server.war"
  WAR_TARGET="${CATALINA_HOME}/webapps/${SERVER_PATH}.war"
  
  if [ -f "${WAR_SOURCE}" ] && [ "${SERVER_PATH}" != "mdi-fhir-server" ]; then
    echo "Renaming WAR file from mdi-fhir-server.war to ${SERVER_PATH}.war"
    mv "${WAR_SOURCE}" "${WAR_TARGET}"
  elif [ ! -f "${WAR_SOURCE}" ] && [ -f "${WAR_TARGET}" ]; then
    echo "WAR file already renamed to ${SERVER_PATH}.war"
  fi
fi

# Execute the command passed to the script
exec "$@"