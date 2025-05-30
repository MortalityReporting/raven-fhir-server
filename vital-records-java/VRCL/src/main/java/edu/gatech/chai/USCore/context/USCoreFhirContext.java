package edu.gatech.chai.USCore.context;

import ca.uhn.fhir.context.FhirContext;
import edu.gatech.chai.USCore.model.USCorePatient;
import edu.gatech.chai.USCore.model.USCorePractitioner;
import edu.gatech.chai.USCore.model.USCoreRelatedPerson;

public class USCoreFhirContext {
    public FhirContext ctx;
    public USCoreFhirContext() {
        super();
        ctx = FhirContext.forR4();
        ctx.setDefaultTypeForProfile("http://hl7.org/fhir/us/core/StructureDefinition/us-core-patient",
                USCorePatient.class);
        ctx.setDefaultTypeForProfile("http://hl7.org/fhir/us/core/StructureDefinition/us-core-practitioner",
                USCorePractitioner.class);
        ctx.setDefaultTypeForProfile("http://hl7.org/fhir/us/core/StructureDefinition/us-core-relatedperson",
                USCoreRelatedPerson.class);
    }
}
