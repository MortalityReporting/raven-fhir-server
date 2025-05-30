package edu.gatech.chai.VRDR.context;

import org.hl7.fhir.r4.model.Bundle;

public class VRDRFhirContext extends VRDRFhirContextDataStructuresOnly {
    public VRDRFhirContext() {
        super();
        ctx.setDefaultTypeForProfile("http://cdc.gov/nchs/nvss/fhir/vital-records-messaging/StructureDefinition/VRM-DeathRecordSubmissionMessage",
                Bundle.class);
        ctx.setDefaultTypeForProfile("http://cdc.gov/nchs/nvss/fhir/vital-records-messaging/StructureDefinition/VRM-DeathRecordUpdateMessage",
                Bundle.class);
        ctx.setDefaultTypeForProfile("http://cdc.gov/nchs/nvss/fhir/vital-records-messaging/StructureDefinition/VRM-DeathRecordVoidMessage",
                Bundle.class);
        ctx.setDefaultTypeForProfile("http://cdc.gov/nchs/nvss/fhir/vital-records-messaging/StructureDefinition/VRM-DeathRecordAliasMessage",
                Bundle.class);
        ctx.setDefaultTypeForProfile("http://cdc.gov/nchs/nvss/fhir/vital-records-messaging/StructureDefinition/VRM-StatusMessage",
                Bundle.class);
        ctx.setDefaultTypeForProfile("http://cdc.gov/nchs/nvss/fhir/vital-records-messaging/StructureDefinition/VRM-CauseOfDeathCodingMessage",
                Bundle.class);
        ctx.setDefaultTypeForProfile("http://cdc.gov/nchs/nvss/fhir/vital-records-messaging/StructureDefinition/VRM-CauseOfDeathCodingUpdateMessage",
                Bundle.class);
        ctx.setDefaultTypeForProfile("http://cdc.gov/nchs/nvss/fhir/vital-records-messaging/StructureDefinition/VRM-DemographicsCodingMessage",
                Bundle.class);
        ctx.setDefaultTypeForProfile("http://cdc.gov/nchs/nvss/fhir/vital-records-messaging/StructureDefinition/VRM-DemographicsCodingUpdateMessage",
                Bundle.class);
        ctx.setDefaultTypeForProfile("http://cdc.gov/nchs/nvss/fhir/vital-records-messaging/StructureDefinition/VRM-AcknowledgementMessage",
                Bundle.class);
        ctx.setDefaultTypeForProfile("http://cdc.gov/nchs/nvss/fhir/vital-records-messaging/StructureDefinition/VRM-ExtractionErrorMessage",
                Bundle.class);
    }
}
