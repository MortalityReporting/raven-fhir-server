# raven-fhir-server
FHIR server component of Raven platform.
## Prerequisite
* fhirbase (postgresql) with FHIR-R4 database.
* docker (for docker installation).
* jdk and maven (for manual compilation).

## fhirbase Installation
The following direction uses docker installation of fhirbase. If you are running other containers to access this database, then we recommend to create a network.

```
sudo docker network create <your_network>
```
sudo docker pull fhirbase/fhirbase:latest
sudo docker run -d -p 3000:3000 -p 5432:5432 --name fhir_db --restart unless-stopped --network <your_network> fhirbase/fhirbase:latest
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

The fhirbase however has an issue with a function that is deployed from the fhirbase installation. Once the postgres and fhirbase are installed, go to
Functions/ in the postgres database schema. In the Functions, there should be *fhirbase_delete(text,text,int8)* function. And, replace the following line 
in the fhirbase_delete(text,text,int8).

In fhirbase_delete(text,text,int8) function, replace the following line
```
$SQL$,
rt || '_history', rt, rt || '_history', rt, rt);
```
with this,
```
$SQL$,
rt || '_history', '"' || rt || '"', rt || '_history', '"' || rt || '"', '"' || rt || '"');
```

Your database is ready for the FHIR server. Other postgresql tools can be used to manage the database.

## Raven FHIR Server Installation
Raven FHIR server can be downloaded from github repo and built as a FHIR server. Do the following to clone the java application from github. 
```
git clone --recurse https://github.com/MortalityReporting/raven-fhir-server.git
```
After cloning the project, you can go into the raven-fhir-server folder and modify envrionment variables in the env.list file. See the env.list file. Please note that either AUTH_BEARER or AUTH_BASIC is needed. 

Set up docker network if you haven't done so. You need to use the same network for all the containers that you want to connect to each other.
```
sudo docker network create <your_network>
```

Now, you are ready to build and run the container. The docker network should have the database container unless external database is running.
```
sudo docker build -t raven-fhir-server .
sudo docker run -d --restart unless-stopped --publish 8080:8080 --network <your_network> --env-file ./env.list raven-fhir-server
```
If you did not change the docker file, then your URL will be 
* HAPI Tester UI: http(s)://host-url:8080/mdi-fhir-server/
* FHIR API URL: http(s)://host-url:8080/mdi-fhir-server/fhir
* SMART on FHIR App Registration: http(s)://host-url:8080/mdi-fhir-server/smart/
  
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
