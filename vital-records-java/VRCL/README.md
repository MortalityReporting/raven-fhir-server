# VRCL_javalib

Java library implementation of the FHIR Vital Records Common Libary(VRCL).  This repository includes Java code for:

- Producing and consuming the Vital Records Common Libary(VRCL) Health Level 7 (HL7) Fast Healthcare Interoperability Resources (FHIR) standard. [Click here to view the FHIR Implementation Guide STU2..0.0-ballot](https://hl7.org/fhir/us/vr-common-library/2024Jan/).
- Producing and consuming FHIR for the exchange of VRCL Resource.

# Standards

This java library implements the FHIR VRCL and VRDR Messaging standards ```https://hl7.org/fhir/us/vr-common-library/2024Jan/```, explained in the implementation guides below:

* VRCL IG: ```https://hl7.org/fhir/us/vr-common-library/2024Jan/index.html```

# Info

This project uses the hapi-fhir java libraries extensively to create its representation.

It is recommended to have a strong understanding of the resource extension section of the hapi-fhir library before diving into the code. Info can be found here. https://hapifhir.io/hapi-fhir/docs/model/custom_structures.html

This project is made up of several major components:

* ```edu.gatech.VRCL.model``` Contains all the custom resources needed for the VRCL IG.
* ```edu.gatech.VRCL.model.util``` Contains utility methods like custom codes and static definitions for data structure resources.
* ```edu.gatech.USCore``` Contains all the custom resources needed for underlying USCore IG resources
* ```edu.gatech.USCore.util``` Contains utility methods like custom codes and static definitions for data structure resources..

# Build

Build the library using maven:

* ```mvn clean install```

Optionally, build the command line tool using maven command:

* ```mvn clean package appassembler:assemble```