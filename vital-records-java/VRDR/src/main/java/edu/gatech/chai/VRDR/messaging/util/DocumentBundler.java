package edu.gatech.chai.VRDR.messaging.util;

import org.hl7.fhir.r4.model.Bundle;

public interface DocumentBundler<T extends Bundle> {
    public T getDocumentBundle();
    public void setDocumentBundle(T documentBundle);
}
