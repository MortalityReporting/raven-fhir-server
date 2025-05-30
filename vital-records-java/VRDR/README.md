# VRDR_javalib

Java library implementation of the FHIR VRDR and FHIR VRDR Messaging standards.  This repository includes Java code for:

- Producing and consuming the Vital Records Death Reporting (VRDR) Health Level 7 (HL7) Fast Healthcare Interoperability Resources (FHIR) standard. [Click here to view the FHIR Implementation Guide STU2.1](http://hl7.org/fhir/us/vrdr/).
- Producing and consuming FHIR messages for the exchange of VRDR documents.
- Example VRDR FHIR documents and messages in JSON and XML formats
- Numerous test cases illustrating proper usage of the library

# Standards

This java library implements the FHIR VRDR and VRDR Messaging standards ```http://hl7.org/fhir/us/vrdr/```, explained in the implementation guides below:

* VRDR IG: ```http://build.fhir.org/ig/HL7/vrdr/branches/master/index.html```
* VRDR Messaging IG: ```http://build.fhir.org/ig/nightingaleproject/vital_records_fhir_messaging_ig/branches/main/index.html```

# Info

This project uses the hapi-fhir java libraries extensively to create its representation.

It is recommended to have a strong understanding of the resource extension section of the hapi-fhir library before diving into the code. Info can be found here. https://hapifhir.io/hapi-fhir/docs/model/custom_structures.html

This project is made up of several major components:

* ```edu.gatech.VRDR.model``` Contains all the custom resources needed for the VRDR IG.
* ```edu.gatech.VRDR.model.util``` Contains utility methods like custom codes and static definitions for data structure resources.
* ```edu.gatech.VRDR.messaging``` Contains all the custom resources needed for the VRDR Messaging IG.
* ```edu.gatech.VRDR.messaging.util``` Contains utility methods like custom codes and static definitions for messaging resources.

You can refer to the ```src/test``` directory for an in-depth unit test example on how to construct a full EDRS record, and serialize it to JSON

## Versions
Interactions with NCHS are governed by the CI build version of the VRDR and Vital Records Messaging IGs, and should use the latest, supported releases of the VRDR .NET software and Canary.

<table class="versionTable" border="3">
<tbody>
<tr>
<td style="text-align: center;"><strong>VRDR IG</strong></td>
<td style="text-align: center;"><strong>Messaging IG</strong></td>
<td style="text-align: center;"><strong>FHIR</strong></td>
<td style="text-align: center;"><strong>Version</strong></td>
<td style="text-align: center;"><strong>VRDR</strong></td>
<td style="text-align: center;"><strong>VRDR.Messaging</strong></td>
</tr>
</tr>
<tr>
<td style="text-align: center;"><a href="https://build.fhir.org/ig/HL7/vrdr/branches/STU2.2-preview1/">STU2.2-preview1</a></td>
<td style="text-align: center;"><a href="http://build.fhir.org/ig/nightingaleproject/vital_records_fhir_messaging_ig/branches/main/index.html">v0.9.1</a></td>
<td style="text-align: center;">R4</td>
<td style="text-align: center;">V4.0.4</td>
<td style="text-align: center;"><a href="https://www.nuget.org/packages/VRDR/4.1.4">nuget</a> <a href="https://github.com/nightingaleproject/vrdr-dotnet/releases/tag/v4.1.4"> github</a></td>
<td style="text-align: center;"><a href="https://www.nuget.org/packages/VRDR.Messaging/4.1.4">nuget</a> <a href="https://github.com/nightingaleproject/vrdr-dotnet/releases/tag/v4.1.4"> github</a></td>
</tr>
<tr>
<td style="text-align: center;"><a href="http://build.fhir.org/ig/HL7/vrdr/">STU2.1 CI build version</a></td>
<td style="text-align: center;"><a href="http://build.fhir.org/ig/nightingaleproject/vital_records_fhir_messaging_ig/branches/main/index.html">v0.9.1</a></td>
<td style="text-align: center;">R4</td>
<td style="text-align: center;">V1.4.3</td>
<td style="text-align: center;"><a href="https://www.nuget.org/packages/VRDR/4.0.0-preview21">nuget</a> <a href="https://github.com/nightingaleproject/VRDR_javalib/releases/tag/v1.4.3-STU2.1"> github</a></td>
<td style="text-align: center;"><a href="https://www.nuget.org/packages/VRDR.Messaging/4.0.0-preview21">nuget</a> <a href="https://github.com/nightingaleproject/VRDR_javalib/releases/tag/v1.4.3-STU2.1"> github</a></td>
</tr>
<tr>
<td style="text-align: center;"><a href="http://hl7.org/fhir/us/vrdr/STU2.1/">STU2.1 Published</a></td>
<td style="text-align: center;"><a href="http://build.fhir.org/ig/nightingaleproject/vital_records_fhir_messaging_ig/branches/main/index.html">v0.9.1</a></td>
<td style="text-align: center;">R4</td>
<td style="text-align: center;">V1.4.3</td>
<td style="text-align: center;"><a href="https://www.nuget.org/packages/VRDR/4.0.0-preview21">nuget</a> <a href="https://github.com/nightingaleproject/VRDR_javalib/releases/tag/v1.4.3-STU2.1"> github</a></td>
<td style="text-align: center;"><a href="https://www.nuget.org/packages/VRDR.Messaging/4.0.0-preview21">nuget</a> <a href="https://github.com/nightingaleproject/VRDR_javalib/releases/tag/v1.4.3-STU2.1"> github</a></td>
</tr>
<tr>
<td style="text-align: center;"><a href="http://hl7.org/fhir/us/vrdr/STU2/">STU2 Published</a></td>
<td style="text-align: center;"><a href="http://build.fhir.org/ig/nightingaleproject/vital_records_fhir_messaging_ig/branches/main/index.html">v0.9.1</a></td>
<td style="text-align: center;">R4</td>
<td style="text-align: center;">V1.4.3</td>
<td style="text-align: center;"><a href="https://www.nuget.org/packages/VRDR/4.0.0-preview21">nuget</a> <a href="https://github.com/nightingaleproject/VRDR_javalib/releases/tag/v1.4.3-STU2.1"> github</a></td>
<td style="text-align: center;"><a href="https://www.nuget.org/packages/VRDR.Messaging/4.0.0-preview21">nuget</a> <a href="https://github.com/nightingaleproject/VRDR_javalib/releases/tag/v1.4.3-STU2.1"> github</a></td>
</tr>
<tr>
<td style="text-align: center;"><a href="http://build.fhir.org/ig/HL7/vrdr/">STU2 v1.3</a></td>
<td style="text-align: center;"><a href="http://build.fhir.org/ig/nightingaleproject/vital_records_fhir_messaging_ig/branches/main/index.html">v0.9</a></td>
<td style="text-align: center;">R4</td>
<td style="text-align: center;">V1.4.3</td>
<td style="text-align: center;"><a href="https://www.nuget.org/packages/VRDR/4.0.0-preview21">nuget</a> <a href="https://github.com/nightingaleproject/VRDR_javalib/releases/tag/v1.4.3-STU2.1"> github</a></td>
<td style="text-align: center;"><a href="https://www.nuget.org/packages/VRDR.Messaging/4.0.0-preview21">nuget</a> <a href="https://github.com/nightingaleproject/VRDR_javalib/releases/tag/v1.4.3-STU2.1"> github</a></td>
</tr>

<tr>
<td style="text-align: center;"><a href="http://hl7.org/fhir/us/vrdr/STU2.1/">STU2.1</a></td>
<td style="text-align: center;"><a href="http://build.fhir.org/ig/nightingaleproject/vital_records_fhir_messaging_ig/branches/main/index.html">v0.9.1</a></td>
<td style="text-align: center;">R4</td>
<td style="text-align: center;">V1.4.2</td>
<td style="text-align: center;"><a href="https://www.nuget.org/packages/VRDR/4.0.0-preview21">nuget</a> <a href="https://github.com/nightingaleproject/VRDR_javalib/releases/tag/v1.4.2-STU2.1"> github</a></td>
<td style="text-align: center;"><a href="https://www.nuget.org/packages/VRDR.Messaging/4.0.0-preview21">nuget</a> <a href="https://github.com/nightingaleproject/VRDR_javalib/releases/tag/v1.4.2-STU2.1"> github</a></td>
</tr>
<tr>
<td style="text-align: center;"><a href="http://hl7.org/fhir/us/vrdr/STU2/">STU2.1</a></td>
<td style="text-align: center;"><a href="http://build.fhir.org/ig/nightingaleproject/vital_records_fhir_messaging_ig/branches/main/index.html">v1.4.1</a></td>
<td style="text-align: center;">R4</td>
<td style="text-align: center;">V1.4.1</td>
<td style="text-align: center;"><a href="https://www.nuget.org/packages/VRDR/4.0.0-preview21">nuget</a> <a href="https://github.com/nightingaleproject/VRDR_javalib/releases/tag/v1.4.1-STU2.1"> github</a></td>
<td style="text-align: center;"><a href="https://www.nuget.org/packages/VRDR.Messaging/4.0.0-preview21">nuget</a> <a href="https://github.com/nightingaleproject/VRDR_javalib/releases/tag/v1.4.1-STU2.1"> github</a></td>
</tr>
<tr>
<td style="text-align: center;"><a href="http://build.fhir.org/ig/HL7/vrdr/">STU2</a></td>
<td style="text-align: center;"><a href="http://build.fhir.org/ig/nightingaleproject/vital_records_fhir_messaging_ig/branches/main/index.html">v1.4.0</a></td>
<td style="text-align: center;">R4</td>
<td style="text-align: center;">V1.4.0</td>
<td style="text-align: center;"><a href="https://www.nuget.org/packages/VRDR/4.0.0-preview21">nuget</a> <a href="https://github.com/nightingaleproject/VRDR_javalib/releases/tag/v1.4.0-STU2"> github</a></td>
<td style="text-align: center;"><a href="https://www.nuget.org/packages/VRDR.Messaging/4.0.0-preview21">nuget</a> <a href="https://github.com/nightingaleproject/VRDR_javalib/releases/tag/v1.4.0-STU2"> github</a></td>
</tr>
<tr>
<td style="text-align: center;"><a href="http://build.fhir.org/ig/HL7/vrdr/">STU2</a></td>
<td style="text-align: center;"><a href="http://build.fhir.org/ig/nightingaleproject/vital_records_fhir_messaging_ig/branches/main/index.html">v.1.3.5</a></td>
<td style="text-align: center;">R4</td>
<td style="text-align: center;">V1.3.5</td>
<td style="text-align: center;"><a href="https://www.nuget.org/packages/VRDR/4.0.0-preview21">nuget</a> <a href="https://github.com/nightingaleproject/VRDR_javalib/releases/tag/v.1.3.5-STU2"> github</a></td>
<td style="text-align: center;"><a href="https://www.nuget.org/packages/VRDR.Messaging/4.0.0-preview21">nuget</a> <a href="https://github.com/nightingaleproject/VRDR_javalib/releases/tag/v.1.3.5-STU2"> github</a></td>
</tr>
<tr>
<td style="text-align: center;"><a href="http://build.fhir.org/ig/HL7/vrdr/">STU1.3</a></td>
<td style="text-align: center;"><a href="http://build.fhir.org/ig/nightingaleproject/vital_records_fhir_messaging_ig/branches/main/index.html">v1.3.4</a></td>
<td style="text-align: center;">R4</td>
<td style="text-align: center;">V1.3.4</td>
<td style="text-align: center;"><a href="https://www.nuget.org/packages/VRDR/4.0.0-preview21">nuget</a> <a href="https://github.com/nightingaleproject/VRDR_javalib/releases/tag/v1.3.4-STU1.3"> github</a></td>
<td style="text-align: center;"><a href="https://www.nuget.org/packages/VRDR.Messaging/4.0.0-preview21">nuget</a> <a href="https://github.com/nightingaleproject/VRDR_javalib/releases/tag/v1.3.4-STU1.3"> github</a></td>
</tr>
<tr>
<td style="text-align: center;"><a href="http://build.fhir.org/ig/HL7/vrdr/">STU1.3 2nd try</a></td>
<td style="text-align: center;"><a href="http://build.fhir.org/ig/nightingaleproject/vital_records_fhir_messaging_ig/branches/main/index.html">v1.3.3</a></td>
<td style="text-align: center;">R4</td>
<td style="text-align: center;">V1.3.3</td>
<td style="text-align: center;"><a href="https://www.nuget.org/packages/VRDR/4.0.0-preview21">nuget</a> <a href="https://github.com/nightingaleproject/VRDR_javalib/releases/tag/v1.3.3-STU1.3-2ndtry"> github</a></td>
<td style="text-align: center;"><a href="https://www.nuget.org/packages/VRDR.Messaging/4.0.0-preview21">nuget</a> <a href="https://github.com/nightingaleproject/VRDR_javalib/releases/tag/v1.3.3-STU1.3-2ndtry"> github</a></td>
</tr>
<tr>
<td style="text-align: center;"><a href="http://build.fhir.org/ig/HL7/vrdr/">STU1.3</a></td>
<td style="text-align: center;"><a href="http://build.fhir.org/ig/nightingaleproject/vital_records_fhir_messaging_ig/branches/main/index.html">v1.3.2</a></td>
<td style="text-align: center;">R4</td>
<td style="text-align: center;">V1.3.2</td>
<td style="text-align: center;"><a href="https://www.nuget.org/packages/VRDR/4.0.0-preview21">nuget</a> <a href="https://github.com/nightingaleproject/VRDR_javalib/releases/tag/v1.3.2-STU1.3"> github</a></td>
<td style="text-align: center;"><a href="https://www.nuget.org/packages/VRDR.Messaging/4.0.0-preview21">nuget</a> <a href="https://github.com/nightingaleproject/VRDR_javalib/releases/tag/v1.3.2-STU1.3"> github</a></td>
</tr>
<tr>
<td style="text-align: center;"><a href="http://build.fhir.org/ig/HL7/vrdr/">STU1.3</a></td>
<td style="text-align: center;"><a href="http://build.fhir.org/ig/nightingaleproject/vital_records_fhir_messaging_ig/branches/main/index.html">v1.3.1</a></td>
<td style="text-align: center;">R4</td>
<td style="text-align: center;">V1.3.1</td>
<td style="text-align: center;"><a href="https://www.nuget.org/packages/VRDR/4.0.0-preview21">nuget</a> <a href="https://github.com/nightingaleproject/VRDR_javalib/releases/tag/v1.3.1-STU1.3"> github</a></td>
<td style="text-align: center;"><a href="https://www.nuget.org/packages/VRDR.Messaging/4.0.0-preview21">nuget</a> <a href="https://github.com/nightingaleproject/VRDR_javalib/releases/tag/v1.3.1-STU1.3"> github</a></td>
</tr>
<tr>
<td style="text-align: center;"><a href="http://build.fhir.org/ig/HL7/vrdr/">STU1.3%60</a></td>
<td style="text-align: center;"><a href="http://build.fhir.org/ig/nightingaleproject/vital_records_fhir_messaging_ig/branches/main/index.html">v1.3</a></td>
<td style="text-align: center;">R4</td>
<td style="text-align: center;">V1.3</td>
<td style="text-align: center;"><a href="https://www.nuget.org/packages/VRDR/4.0.0-preview21">nuget</a> <a href="https://github.com/nightingaleproject/VRDR_javalib/releases/tag/V1.3-STU1.3%60"> github</a></td>
<td style="text-align: center;"><a href="https://www.nuget.org/packages/VRDR.Messaging/4.0.0-preview21">nuget</a> <a href="https://github.com/nightingaleproject/VRDR_javalib/releases/tag/V1.3-STU1.3%60"> github</a></td>
</tr>

<tr>
<td style="text-align: center;"><a href="http://build.fhir.org/ig/HL7/vrdr/">STU1.2</a></td>
<td style="text-align: center;"><a href="http://build.fhir.org/ig/nightingaleproject/vital_records_fhir_messaging_ig/branches/main/index.html">v1.2.3</a></td>
<td style="text-align: center;">R4</td>
<td style="text-align: center;">V1.2.3</td>
<td style="text-align: center;"><a href="https://www.nuget.org/packages/VRDR/4.0.0-preview21">nuget</a> <a href="https://github.com/nightingaleproject/VRDR_javalib/releases/tag/v.1.2.3-R4-STU1.2"> github</a></td>
<td style="text-align: center;"><a href="https://www.nuget.org/packages/VRDR.Messaging/4.0.0-preview21">nuget</a> <a href="https://github.com/nightingaleproject/VRDR_javalib/releases/tag/v.1.2.3-R4-STU1.2"> github</a></td>
</tr>
<tr>
<td style="text-align: center;"><a href="http://build.fhir.org/ig/HL7/vrdr/">STU1.2</a></td>
<td style="text-align: center;"><a href="http://build.fhir.org/ig/nightingaleproject/vital_records_fhir_messaging_ig/branches/main/index.html">v1.2.2</a></td>
<td style="text-align: center;">R4</td>
<td style="text-align: center;">V1.2.2</td>
<td style="text-align: center;"><a href="https://www.nuget.org/packages/VRDR/4.0.0-preview21">nuget</a> <a href="https://github.com/nightingaleproject/VRDR_javalib/releases/tag/v1.2.2-R4-STU1.2"> github</a></td>
<td style="text-align: center;"><a href="https://www.nuget.org/packages/VRDR.Messaging/4.0.0-preview21">nuget</a> <a href="https://github.com/nightingaleproject/VRDR_javalib/releases/tag/v1.2.2-R4-STU1.2"> github</a></td>
</tr>
<tr>
<td style="text-align: center;"><a href="http://build.fhir.org/ig/HL7/vrdr/">STU1.2</a></td>
<td style="text-align: center;"><a href="http://build.fhir.org/ig/nightingaleproject/vital_records_fhir_messaging_ig/branches/main/index.html">v1.2.1</a></td>
<td style="text-align: center;">R4</td>
<td style="text-align: center;">V1.2.1</td>
<td style="text-align: center;"><a href="https://www.nuget.org/packages/VRDR/4.0.0-preview21">nuget</a> <a href="https://github.com/nightingaleproject/VRDR_javalib/releases/tag/v1.2.1-R4-STU1.2"> github</a></td>
<td style="text-align: center;"><a href="https://www.nuget.org/packages/VRDR.Messaging/4.0.0-preview21">nuget</a> <a href="https://github.com/nightingaleproject/VRDR_javalib/releases/tag/v1.2.1-R4-STU1.2"> github</a></td>
</tr>
<tr>
<td style="text-align: center;"><a href="http://build.fhir.org/ig/HL7/vrdr/">STU1.2</a></td>
<td style="text-align: center;"><a href="http://build.fhir.org/ig/nightingaleproject/vital_records_fhir_messaging_ig/branches/main/index.html">v1.2.0</a></td>
<td style="text-align: center;">R4</td>
<td style="text-align: center;">V1.2.0</td>
<td style="text-align: center;"><a href="https://www.nuget.org/packages/VRDR/4.0.0-preview21">nuget</a> <a href="https://github.com/nightingaleproject/VRDR_javalib/releases/tag/v1.2.0-R4-STU1.2"> github</a></td>
<td style="text-align: center;"><a href="https://www.nuget.org/packages/VRDR.Messaging/4.0.0-preview21">nuget</a> <a href="https://github.com/nightingaleproject/VRDR_javalib/releases/tag/v1.2.0-R4-STU1.2"> github</a></td>
</tr>
<tr>
<td style="text-align: center;"><a href="http://build.fhir.org/ig/HL7/vrdr/">Updated test cases and added HelperFunctions</a></td>
<td style="text-align: center;"><a href="http://build.fhir.org/ig/nightingaleproject/vital_records_fhir_messaging_ig/branches/main/index.html">v1.0.1</a></td>
<td style="text-align: center;">R4</td>
<td style="text-align: center;">V1.0.1</td>
<td style="text-align: center;"><a href="https://www.nuget.org/packages/VRDR/4.0.0-preview21">nuget</a> <a href="https://github.com/nightingaleproject/VRDR_javalib/releases/tag/1.0.1-R4"> github</a></td>
<td style="text-align: center;"><a href="https://www.nuget.org/packages/VRDR.Messaging/4.0.0-preview21">nuget</a> <a href="https://github.com/nightingaleproject/VRDR_javalib/releases/tag/1.0.1-R4"> github</a></td>
</tr>
<tr>
<td style="text-align: center;"><a href="http://build.fhir.org/ig/HL7/vrdr/">VRDR-STU1 Compatible</a></td>
<td style="text-align: center;"><a href="http://build.fhir.org/ig/nightingaleproject/vital_records_fhir_messaging_ig/branches/main/index.html">v1.0.0</a></td>
<td style="text-align: center;">R4</td>
<td style="text-align: center;">V1.0.0</td>
<td style="text-align: center;"><a href="https://www.nuget.org/packages/VRDR/4.0.0-preview21">nuget</a> <a href="https://github.com/nightingaleproject/VRDR_javalib/releases/tag/1.0.0-R4"> github</a></td>
<td style="text-align: center;"><a href="https://www.nuget.org/packages/VRDR.Messaging/4.0.0-preview21">nuget</a> <a href="https://github.com/nightingaleproject/VRDR_javalib/releases/tag/1.0.0-R4"> github</a></td>
</tr>

<tr>
<td style="text-align: center;"><a href="http://build.fhir.org/ig/HL7/vrdr/">R4</a></td>
<td style="text-align: center;"><a href="http://build.fhir.org/ig/nightingaleproject/vital_records_fhir_messaging_ig/branches/main/index.html">v0.1.0 STU Ballot #1 REVISED [BETA]</a></td>
<td style="text-align: center;">N/A</td>
<td style="text-align: center;">V0.1.0 Revised Beta/td>
<td style="text-align: center;"><a href="https://www.nuget.org/packages/VRDR/4.0.0-preview21">nuget</a> <a href="https://github.com/nightingaleproject/VRDR_javalib/releases/tag/v0.1.0-REVISED-BETA"> github</a></td>
<td style="text-align: center;"><a href="https://www.nuget.org/packages/VRDR.Messaging/4.0.0-preview21">nuget</a> <a href="https://github.com/nightingaleproject/VRDR_javalib/releases/tag/v0.1.0-REVISED-BETA"> github</a></td>
</tr>
<tr>
<td style="text-align: center;"><a href="http://build.fhir.org/ig/HL7/vrdr/">R4</a></td>
<td style="text-align: center;"><a href="http://build.fhir.org/ig/nightingaleproject/vital_records_fhir_messaging_ig/branches/main/index.html">v0.1.0 STU Ballot #1</a></td>
<td style="text-align: center;">N/A</td>
<td style="text-align: center;">V0.1.0</td>
<td style="text-align: center;"><a href="https://www.nuget.org/packages/VRDR/4.0.0-preview21">nuget</a> <a href="https://github.com/nightingaleproject/VRDR_javalib/releases/tag/0.1.0"> github</a></td>
<td style="text-align: center;"><a href="https://www.nuget.org/packages/VRDR.Messaging/4.0.0-preview21">nuget</a> <a href="https://github.com/nightingaleproject/VRDR_javalib/releases/tag/0.1.0"> github</a></td>
</tr>

</tbody>
</table>


# Build

Build the library using maven:

* ```mvn clean install```

Optionally, build the command line tool using maven command:

* ```mvn clean package appassembler:assemble```


# Running

For the library, simply place the compiled target jar in your classpath and import the VRDR classes to use the library.

Optionally, use the command line tool to generate example records for Canary testing:

* ```sh target/appassembler/bin/app create submission canary-submission-test.json```

* ```sh target/appassembler/bin/app create update canary-update-test.json```

* ```sh target/appassembler/bin/app create void canary-void-test.json```

You can also output XML instead of JSON by passing the ```--xml``` option.

# Coding Examples
#### Creating death record
```cs
VRDRFhirContext ctx = new VRDRFhirContext();

// creating death record from xml data file 
DeathCertificateDocument deathRecordFromXML = BaseMessage.parseXMLFile(DeathCertificateDocument.class, ctx, "path-to-xml-data-file/DeathRecord.xml");

// creating death record from json data file
DeathCertificateDocument deathRecordFromJson = BaseMessage.parseJsonFile(DeathCertificateDocument.class, ctx, "path-to-json-data-file/DeathRecord.json");

// creating death record with no identifiers from json data file
DeathCertificateDocument deathRecordNoIdentifiers = BaseMessage.parseJsonFile(DeathCertificateDocument.class, ctx, "path-to-json-data-file/DeathRecordNoIdentifiers.json");

// adding fullUrl to death record's component and resource
Generic: deathCertificateDocument.addEntry(new BundleEntryComponent().setResource(resource).setFullUrl(uuidPrefix+resource.getId()));
Example: deathCertificateDocument.addEntry(new BundleEntryComponent().setResource(deathCertificate).setFullUrl(uuidPrefix + deathCertificate.getId()));
```

#### Create submission message for death record
```cs
// Create submission message from Bundle
DeathRecordSubmissionMessage submission = new DeathRecordSubmissionMessage();
submission.setDeathRecord(new DeathCertificateDocument());
submission.setCertNo(42);
submission.setStateAuxiliaryId("identifier");
final String submissionBundleStr = submission.toJson(ctx);
DeathRecordSubmissionMessage parsed = BaseMessage.parseJson(DeathRecordSubmissionMessage.class, ctx, submissionBundleStr);

// Create submission message from death record with no identifiers in json data file
VRDRFhirContext ctx = new VRDRFhirContext();
DeathCertificateDocument deathRecordNoIdentifiers = BaseMessage.parseJsonFile(DeathCertificateDocument.class, ctx, "path-to-json-data-file/DeathRecordNoIdentifiers.json");
DeathRecordSubmissionMessage submission = DeathRecordSubmissionMessage.fromDeathRecord(deathRecordNoIdentifiers);
        
// Create submission message from death record in xml data file
VRDRFhirContext ctx = new VRDRFhirContext();
DeathCertificateDocument deathRecordFromXML = BaseMessage.parseXMLFile(DeathCertificateDocument.class, ctx, "path-to-xml-data-file/DeathRecord.xml");
DeathRecordSubmissionMessage submission = DeathRecordSubmissionMessage.fromDeathRecord(deathRecordFromXML);
        
// Create submission message from death record in json data file
VRDRFhirContext ctx = new VRDRFhirContext();
DeathCertificateDocument deathRecordFromJson = BaseMessage.parseJsonFile(DeathCertificateDocument.class, ctx, "path-to-json-data-file/DeathRecord.json");
DeathRecordSubmissionMessage submission = DeathRecordSubmissionMessage.fromDeathRecord(deathRecordFromJson);

// Create submission message in json data file
VRDRFhirContext ctx = new VRDRFhirContext();
DeathRecordSubmissionMessage submission = BaseMessage.parseJsonFile(DeathRecordSubmissionMessage.class, ctx, "path-to-json-data-file/Submission.json"); 

// Create submission message from empty submission in json data file
VRDRFhirContext ctx = new VRDRFhirContext();
DeathRecordSubmissionMessage submission = BaseMessage.parseJsonFile(DeathRecordSubmissionMessage.class, ctx, "path-to-json-data-file/EmptySubmission.json");

// Create submission message from wrong structure in json data file
VRDRFhirContext ctx = new VRDRFhirContext();
AcknowledgementMessage acknowledgementMessage = BaseMessage.parseJsonFile(AcknowledgementMessage.class, ctx, "path-to-json-data-file/DeathRecordSubmissionMessage.json");
```

#### Create acknowledgenent for submission message
```java
// Create general Ack
VRDRFhirContext ctx = new VRDRFhirContext();
DeathRecordSubmissionMessage submission = BaseMessage.parseJsonFile(DeathRecordSubmissionMessage.class, ctx, "path-to-json-data-file/DeathRecordSubmissionMessage.json");
AcknowledgementMessage ack = new AcknowledgementMessage(submission);

// Create Ack for message with no identifiers
DeathRecordSubmissionMessage submission = new DeathRecordSubmissionMessage();
AcknowledgementMessage ack = new AcknowledgementMessage(submission);

// Create Ack from json data file
VRDRFhirContext ctx = new VRDRFhirContext();
AcknowledgementMessage ack = BaseMessage.parseJsonFile(AcknowledgementMessage.class, ctx, "path-to-json-data-file/AcknowledgementMessage.json");

// Create Ack from XML data file
VRDRFhirContext ctx = new VRDRFhirContext();
AcknowledgementMessage ack = BaseMessage.parseXMLFile(AcknowledgementMessage.class, ctx, "path-to-xml-data-file/AcknowledgementMessage.xml");
        
// Create Ack from Bundle
VRDRFhirContext ctx = new VRDRFhirContext();
AcknowledgementMessage ackBundle = BaseMessage.parseJsonFile(AcknowledgementMessage.class, ctx, "path-to-json-data-file/AcknowledgementMessage.json");
String bundleString = ackBundle.toJson(ctx, false);
AcknowledgementMessage ack = BaseMessage.parseJson(AcknowledgementMessage.class, ctx, bundleString);
```

#### Create coding message
```java
// Create cause of death coding message
VRDRFhirContext ctx = new VRDRFhirContext();
DeathRecordSubmissionMessage submission = BaseMessage.parseJsonFile(DeathRecordSubmissionMessage.class, ctx, "path-to-json-data-file/DeathRecordSubmissionMessage.json");
CauseOfDeathCodingMessage coding = new CauseOfDeathCodingMessage(submission);

// Create cause of death coding message with no identifiers
CauseOfDeathCodingMessage coding = new CauseOfDeathCodingMessage((DeathRecordSubmissionMessage)null);
        
// Create cause of death coding message with no message
DeathRecordSubmissionMessage submission = new DeathRecordSubmissionMessage();
CauseOfDeathCodingMessage coding = new CauseOfDeathCodingMessage(submission);

// Create Ack for status message
VRDRFhirContext ctx = new VRDRFhirContext();
StatusMessage statusMessage = BaseMessage.parseJsonFile(StatusMessage.class, ctx, "src/test/resources/json/StatusMessage.json");
AcknowledgementMessage ack = new AcknowledgementMessage(statusMessage);
```

#### Create coding response
```java
// Create cause of death coding response from json data file
VRDRFhirContext ctx = new VRDRFhirContext();
CauseOfDeathCodingMessage message = BaseMessage.parseJsonFile(CauseOfDeathCodingMessage.class, ctx, "path-to-json-data-file/CauseOfDeathCodingMessage.json");
CauseOfDeathCodedContentBundle bundle = message.getCauseOfDeathCodedContentBundle();

// By getting list of RecordAxisCauseOfDeath from bundle
List<RecordAxisCauseOfDeath> recordAxis = bundle.getRecordAxisCauseOfDeath();

// By getting list of EntityAxisCauseOfDeath from bundle
List<EntityAxisCauseOfDeath> entityAxis = bundle.getEntityAxisCauseOfDeath();

// By getting CodingStatusValues from bundle
CodingStatusValues codingStatusValues = bundle.getCodingStatusValues();
```

#### Create status message
```java
VRDRFhirContext ctx = new VRDRFhirContext();
DeathRecordSubmissionMessage submission = BaseMessage.parseJsonFile(DeathRecordSubmissionMessage.class, ctx, "src/test/resources/json/DeathRecordSubmissionMessage.json");
StatusMessage status = new StatusMessage(submission, "manualCauseOfDeathCoding");
```

#### Batch processing production or submission
```java
// create the outer bundle
BaseMessage responseBundle = BaseMessage.parseJsonFile(AcknowledgementMessage.class, ctx, "src/test/resources/json/AcknowledgementMessage.json");

// add different types of messages or inner bundles to the outer bundle
responseBundle.addEntry(new Bundle.BundleEntryComponent().setResource(BaseMessage.parseJsonFile(DeathRecordSubmissionMessage.class, ctx, "src/test/resources/json/DeathRecordSubmissionMessage.json")));

// convert the loaded outer bundle, or bundle of bundles, to String
String bundleString = responseBundle.toJson(ctx);
```

#### Batch processing consumption or reception
```java
// parse the stringified bundle of bundles by invoking static function parseBundleOfBundles
List<BaseMessage> listOfMessages = BaseMessage.parseBundleOfBundles(ctx, bundleString);
```

# Publishing a Version

To create a new release of the VRDR Java Library:

1. Incorporate the changes into the `CHANGELOG.md` file, providing a summary account of the modifications made in this release.
1. Modify the `pom.xml` file by updating the release version, specifically adjusting the version element.
1. Generate a pull request to merge the aforementioned modifications into the master branch.
1. Publish a new GitHub release by following these steps:
    1. Go to the Releases page
    1. Select "Draft a new release"
    1. Create a new release version on the tag and release (e.g., v1.4.0-STU2.1)
    1. Copy the information from the CHANGELOG.md file relevant to this version and paste it into the release description
    1. Ensure that the "pre-release" button remains unchecked, even for preview releases, as pre-releases will not be displayed on the main GitHub page
1. Review the recently created [package](https://github.com/orgs/nightingaleproject/packages) and update the package description as necessary.

Following each release, the compiled package is published on GitHub Packages, accessible through https://github.com/orgs/nightingaleproject/packages and the repository landing page.
