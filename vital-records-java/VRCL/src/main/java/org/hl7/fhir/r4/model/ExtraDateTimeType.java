package org.hl7.fhir.r4.model;

import ca.uhn.fhir.model.api.TemporalPrecisionEnum;


public class ExtraDateTimeType extends BaseDateTimeType {
    private String missingOrUnknownDeathTime;
    private String missingOrUnknownInjuryTime;

    public String getMissingOrUnknownDeathTime() {
        return missingOrUnknownDeathTime;
    }
    public void setMissingOrUnknownDeathTime(String missingOrUnknownDeathTime) {
        this.missingOrUnknownDeathTime = missingOrUnknownDeathTime;
    }

    public String getMissingOrUnknownInjuryTime() {
        return missingOrUnknownInjuryTime;
    }

    public void setMissingOrUnknownInjuryTime(String missingOrUnknownInjuryTime) {
        this.missingOrUnknownInjuryTime = missingOrUnknownInjuryTime;
    }


    @Override
    protected TemporalPrecisionEnum getDefaultPrecisionForDatatype() {
        return null;
    }

    @Override
    boolean isPrecisionAllowed(TemporalPrecisionEnum temporalPrecisionEnum) {
        return false;
    }

    @Override
    public Type copy() {
        return null;
    }
}
