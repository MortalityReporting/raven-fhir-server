# forte-fhir-server
FHIR server component of FORTE platform.
## Prerequisite
* fhirbase (postgresql) with FHIR-R4 database.
* docker (for docker installation).
* jdk and maven (for manual compilation).

## fhirbase Installation
The following direction uses docker installation of fhirbase.
```
sudo docker pull fhirbase/fhirbase:latest
sudo docker run -d -p 3000:3000 -p 5432:5432 --restart unless-stopped fhirbase/fhirbase:latest
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

postgres=#CREATE <database name>
postgres=#\q
```
Now you created a database in the fhirbase postgresql database. Now, at the prompt (of container), run the follow,
```
postgres@xxxxxxxxx:/$ fhirbase -d <database name> --fhir=4.0.0 init
```
Your database is ready for the FHIR server. Other postgresql tools can be used to manage the database.

## FORTE FHIR Server Installation
FORTE FHIR server can be downloaded from github repo and built as a FHIR server. Do the following to clone the java application from github. 
```
git clone --recurse https://github.com/MortalityReporting/forte-fhir-server.git
```
After cloning the project, you can go into the forte-fhir-server folder and add envrionment variables in the Dockerfile. See the following example. 
```
ENV JDBC_URL="jdbc:postgresql://<fhirbase url>/<database name>"
ENV JDBC_USERNAME="postgres"
ENV JDBC_PASSWORD="postgres"
ENV SMART_INTROSPECTURL="<url for forte-fhir-server>/forte-fhir-server/smart/introspect"
ENV SMART_AUTHSERVERURL="<url for forte-fhir-server>/forte-fhir-server/smart/authorize"
ENV SMART_TOKENSERVERURL="<url for forte-fhir-server>/forte-fhir-server/smart/token"
ENV AUTH_BEARER="<static bearer token>"
ENV AUTH_BASIC="<basic auth - ex) client:secret>"
ENV FHIR_READONLY="<True of False>"
ENV SERVERBASE_URL="<forte server's fhir URL - ex) https://myurl.com/forte-fhir-server/fhir>"
```
Now, you are ready to build and run the container.
```
sudo docker build -t forte-fhir-server .
sudo docker run -d --restart unless-stopped --publish 8080:8080 forte-fhir-server
```
If you did not change the docker file, then your URL will be 
* HAPI Tester UI: http(s)://host-url:8080/forte-fhir-server/
* FHIR API URL: http(s)://host-url:8080/forte-fhir-server/fhir
* SMART on FHIR App Registration: http(s)://host-url:8080/forte-fhir-server/smart/
  
## APIs supported
1. To get all decedents (patients)<br/>
```
GET https://host-url:port/forte-fhir-server/fhir/Patient
```
2. To search (decedent) patient by case number
```
GET https://host-url:port/forte-fhir-server/fhir/Patient?identifier=<cms-system>|<case_number>
```
3. To fetch decedent (patient) records
```
GET https://host-url:port/forte-fhir-server/fhir/Patient/<patient_id>/$everything
note: <patient_id> can be obtained from (2)
```
4. To search Composition by patient's identifier
```
GET https://host-url:port/forte-fhir-server/fhir/Composition?patient.identifier=<cms-system>|<case_number>
```
5. To push batch decedent records
```
POST https://host-url:port/forte-fhir-server/fhir/
note: payload must have a bundle with type.code = batch
```
6. To store VRDR document
```
POST https://host-url:port/forte-fhir-server/fhir/
note: payload must have a bundle in VRDR Death Certificate Document
```
7. To fetch VRDR document
```
GET https://host-url:port/forte-fhir-server/fhir/Composition/<composition_id>/$document
```
8. any FHIR CRUDs for supported resources.

### API header requirement
- Basic Authorization with client:secret credential
- Content-Type should have application/fhir+json
