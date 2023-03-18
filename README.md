# raven-fhir-server
FHIR server component of Raven platform.
## Prerequisite
* fhirbase (postgresql) with FHIR-R4 database.
* docker (for docker installation).
* jdk and maven (for manual compilation).

## fhirbase Installation
The following direction uses docker installation of fhirbase.
```
sudo docker pull fhirbase/fhirbase:latest
sudo docker run -d -p 3000:3000 -p 5432:5432 --name fhir_db --restart unless-stopped fhirbase/fhirbase:latest
```
This will deploy the fhirbase postgresql database. Do the follow to get list of docker containers.
```
sudo docker ps -a
```
The docker id should be the first column of fhirbase/fhirbase:latest container. Copy the docker id and do the following command.
```
sudo docker exec -it [container ID from previous step] /bin/bash
```
This will get you inside the docker image. Run psql to run the psql tool and create a database.
```
postgres@xxxxxxxxx:/$ psql
psql (10.5 (Debian 10.5-1.pgdg90+1))
Type "help" for help.

postgres=#CREATE DATABASE <dbname>;
postgres=\l+ (this is to see if your database was created)
postgres=#\q
```
Now you created a database in the fhirbase postgresql database. Now, at the prompt (of container), run the follow,
```
postgres@xxxxxxxxx:/$ fhirbase -d <database name> --fhir=4.0.0 init
```
The fhirbase is missing bundle resource. Run the SQLs in the "bundle_table.ddl.txt". This will create two tables to hold Bundle resources.
```
psql -d <database name> -U <username> -h localhost -a -f ./bundle_table.ddl.txt
```
Or, you can use any DB tool to run the SQLs in the file. 

Your database is ready for the FHIR server. Other postgresql tools can be used to manage the database.

## Raven FHIR Server Installation
Raven FHIR server can be downloaded from github repo and built as a FHIR server. Do the following to clone the java application from github. 
```
git clone --recurse https://github.com/MortalityReporting/raven-fhir-server.git
```
After cloning the project, you can go into the raven-fhir-server folder and add envrionment variables in the Dockerfile. See the following example. Only AUTH_BEARER or AUTH_BASIC is needed. 
```
ENV JDBC_URL="jdbc:postgresql://fhir_db:5432/<database name>"
ENV JDBC_USERNAME="postgres"
ENV JDBC_PASSWORD="postgres"
ENV SMART_INTROSPECTURL="<url for raven-fhir-server>/raven-fhir-server/smart/introspect"
ENV SMART_AUTHSERVERURL="<url for raven-fhir-server>/raven-fhir-server/smart/authorize"
ENV SMART_TOKENSERVERURL="<url for raven-fhir-server>/raven-fhir-server/smart/token"
ENV AUTH_BEARER="<static bearer token>"
ENV AUTH_BASIC="<basic auth - ex) client:secret>"
ENV FHIR_READONLY="<True of False>"
ENV SERVERBASE_URL="<raven server's fhir URL - ex) https://myurl.com/raven-fhir-server/fhir>"
ENV INTERNAL_FHIR_REQUEST_URL="<url for raven-fhir-server>/raven-fhir-server/fhir"
```
Now, you are ready to build and run the container.
```
sudo docker build -t raven-fhir-server .
sudo docker run -d --restart unless-stopped --publish 8080:8080 --link fhir_db:fhir_db raven-fhir-server
```
If you did not change the docker file, then your URL will be 
* HAPI Tester UI: http(s)://host-url:8080/raven-fhir-server/
* FHIR API URL: http(s)://host-url:8080/raven-fhir-server/fhir
* SMART on FHIR App Registration: http(s)://host-url:8080/raven-fhir-server/smart/
  
## APIs supported
1. To get all decedents (patients)<br/>
```
GET https://host-url:port/raven-fhir-server/fhir/Patient
```
2. To search (decedent) patient by case number
```
GET https://host-url:port/raven-fhir-server/fhir/Patient?identifier=<cms-system>|<case_number>
```
3. To fetch decedent (patient) records
```
GET https://host-url:port/raven-fhir-server/fhir/Patient/<patient_id>/$everything
note: <patient_id> can be obtained from (2)
```
4. To search Composition by patient's identifier
```
GET https://host-url:port/raven-fhir-server/fhir/Composition?patient.identifier=<cms-system>|<case_number>
```
5. To push batch decedent records
```
POST https://host-url:port/raven-fhir-server/fhir/
note: payload must have a bundle with type.code = batch
```
6. To store VRDR document
```
POST https://host-url:port/raven-fhir-server/fhir/
note: payload must have a bundle in VRDR Death Certificate Document
```
7. To fetch VRDR document
```
GET https://host-url:port/raven-fhir-server/fhir/Composition/<composition_id>/$document
```
8. any FHIR CRUDs for supported resources.

### API header requirement
- Basic Authorization with client:secret credential
- Content-Type should have application/fhir+json
