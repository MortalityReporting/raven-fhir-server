package edu.gatech.chai.VRDR.validation;
/*package edu.gatech.chai.validation;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.hl7.fhir.dstu3.hapi.ctx.IValidationSupport;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Bundle.BundleEntryComponent;
import org.hl7.fhir.r4.model.StructureDefinition;
import org.hl7.fhir.r4.model.ValueSet;
import org.hl7.fhir.r4.model.ValueSet.ConceptSetComponent;
import org.hl7.fhir.r4.model.ValueSet.ValueSetExpansionComponent;
import org.hl7.fhir.instance.hapi.validation.DefaultProfileValidationSupport;
import org.hl7.fhir.instance.hapi.validation.FhirInstanceValidator;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.utilities.validation.ValidationMessage.IssueSeverity;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.server.exceptions.InternalErrorException;
import edu.gatech.chai.context.VRDRFhirContext;

public class VRDRProfileValidationSupport implements IValidationSupport {
	VRDRFhirContext ctx;

	public VRDRProfileValidationSupport(VRDRFhirContext ctx) {
		this.ctx = ctx;
	}

	@SuppressWarnings("unchecked")
	public <T extends IBaseResource> T fetchResource(FhirContext theContext, Class<T> theClass, String theUri) {
		if (theUri.startsWith("http://hl7.org/fhir/StructureDefinition/")) {
			return (T) FhirInstanceValidator.loadProfileOrReturnNull(null, theContext, theUri.substring("http://hl7.org/fhir/StructureDefinition/".length()));
		}
		if(theUri.startsWith("http://hl7.org/fhir/us/vrdr/StructureDefinition/")) {
			return (T) FhirInstanceValidator.loadProfileOrReturnNull(null, theContext, theUri.substring("http://hl7.org/fhir/us/vrdr/StructureDefinition/".length()));
		}
		if (theUri.startsWith("http://hl7.org/fhir/ValueSet/")) {
			Map<String, ValueSet> defaultValueSets = myDefaultValueSets;
			if (defaultValueSets == null) {
				String path = theContext.getVersion().getPathToSchemaDefinitions().replace("/schema", "/valueset") + "/valuesets.xml";
				InputStream valuesetText = DefaultProfileValidationSupport.class.getResourceAsStream(path);
				if (valuesetText == null) {
					return null;
				}
				InputStreamReader reader;
				try {
					reader = new InputStreamReader(valuesetText, "UTF-8");
				} catch (UnsupportedEncodingException e) {
					// Shouldn't happen!
					throw new InternalErrorException("UTF-8 encoding not supported on this platform", e);
				}

				defaultValueSets = new HashMap<>();

				Bundle bundle = ctx.getCtx().newXmlParser().parseResource(Bundle.class, reader);
				for (BundleEntryComponent next : bundle.getEntry()) {
					IdType nextId = new IdType(next.getFullUrl());
					if (nextId.isEmpty() || !nextId.getValue().startsWith("http://hl7.org/fhir/ValueSet/")) {
						continue;
					}
					defaultValueSets.put(nextId.toVersionless().getValue(), (ValueSet) next.getResource());
				}

				myDefaultValueSets = defaultValueSets;
			}

			return (T) defaultValueSets.get(theUri);
		}

		return null;
	}

	public boolean isCodeSystemSupported(FhirContext theContext, String theSystem) {
		return false;
	}

	public List<StructureDefinition> allStructures() {
		// TODO Auto-generated method stub
		ArrayList<StructureDefinition> retVal = new ArrayList<StructureDefinition>();

		for (String next : ctx.getCtx().getResourceNames()) {
			StructureDefinition profile = FhirInstanceValidator.loadProfileOrReturnNull(null, ctx, next);
			retVal.add(profile);
		}

		return retVal;
		return null;
	}

	public ValueSet fetchCodeSystem(FhirContext theContext, String theSystem) {
		synchronized (this) {
			Map<String, ValueSet> valueSets = myCodeSystems;
			if (valueSets == null) {
				valueSets = new HashMap<>();

				loadValueSets(theContext, valueSets, "/org/hl7/fhir/instance/model/valueset/valuesets.xml");
				loadValueSets(theContext, valueSets, "/org/hl7/fhir/instance/model/valueset/v2-tables.xml");
				loadValueSets(theContext, valueSets, "/org/hl7/fhir/instance/model/valueset/v3-codesystems.xml");
				loadValueSets(theContext, valueSets, "/VRDR/valuesets/");


				myCodeSystems = valueSets;
			}

			return valueSets.get(theSystem);
		}
	}

	public ValueSetExpansionComponent expandValueSet(FhirContext theContext,
			ConceptSetComponent theInclude) {
		// TODO Auto-generated method stub
		return null;
	}

	private void loadValueSets(FhirContext theContext, Map<String, ValueSet> theValueSets, String theFile) {
		File inputFile = new File(theFile);
		if(inputFile.isDirectory()) {
			loadValueSetFromDirectory(theContext, theValueSets, theFile);
		}
		else {
			loadValueSetFromFile(theContext, theValueSets, theFile);
		}
	}

	private void loadValueSetFromDirectory(FhirContext theContext, Map<String, ValueSet> theValueSets, String theFile) {
		List<String> valueSetResourcePaths = getResourceFiles(theFile);
		for(String valueSetResourcePath:valueSetResourcePaths) {
			loadValueSetFromFile(theContext,theValueSets,valueSetResourcePath);
		}
	}

	private void loadValueSetFromFile(FhirContext theContext, Map<String, ValueSet> theValueSets, String theFile) {
		InputStream valuesetText = DefaultProfileValidationSupport.class.getResourceAsStream(theFile);
		try {
			if (valuesetText != null) {
				InputStreamReader reader = null;
				try {
					reader = new InputStreamReader(valuesetText, "UTF-8");

					Bundle bundle = ctx.getCtx().newXmlParser().parseResource(Bundle.class, reader);
					for (BundleEntryComponent next : bundle.getEntry()) {
						ValueSet nextValueSet = (ValueSet) next.getResource();
						String system = nextValueSet.getCodeSystem().getSystem();
						if (isNotBlank(system)) {
							theValueSets.put(system, nextValueSet);
						}
					}

				} catch (UnsupportedEncodingException e) {
					// Shouldn't happen!
					throw new InternalErrorException("UTF-8 encoding not supported on this platform", e);
				} finally {
					IOUtils.closeQuietly(reader);
				}

			}
		} finally {
			IOUtils.closeQuietly(valuesetText);
		}
	}

	private List<String> getResourceFiles(String path) throws IOException {
	    List<String> filenames = new ArrayList<String>();

	    try (
	            InputStream in = getResourceAsStream(path);
	            BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
	        String resource;

	        while ((resource = br.readLine()) != null) {
	            filenames.add(resource);
	        }
	    }

	    return filenames;
	}

	private InputStream getResourceAsStream(String resource) {
	    final InputStream in
	            = getContextClassLoader().getResourceAsStream(resource);

	    return in == null ? getClass().getResourceAsStream(resource) : in;
	}

	private ClassLoader getContextClassLoader() {
	    return Thread.currentThread().getContextClassLoader();
	}

	@Override
	public List<IBaseResource> fetchAllConformanceResources(FhirContext theContext) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CodeValidationResult validateCode(FhirContext theContext, String theCodeSystem, String theCode,
			String theDisplay, String theValueSetUrl) {
		ValueSet vs = fetchCodeSystem(theContext, theCodeSystem);
		if (vs != null) {
			for (ValueSet.ConceptDefinitionComponent nextConcept : vs.getCodeSystem().getConcept()) {
				if (nextConcept.getCode().equals(theCode)) {
					ValueSet.ConceptDefinitionComponent component = new ValueSet.ConceptDefinitionComponent(new CodeType(theCode));
					return new CodeValidationResult(component);
				}
			}
		}
		return new CodeValidationResult(IssueSeverity.WARNING, "Unknown code: " + theCodeSystem + " / " + theCode);
	}

	@Override
	public LookupCodeResult lookupCode(FhirContext theContext, String theSystem, String theCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StructureDefinition> fetchAllStructureDefinitions(FhirContext theContext) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ValueSet fetchValueSet(FhirContext theContext, String uri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StructureDefinition fetchStructureDefinition(FhirContext theCtx, String theUrl) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StructureDefinition generateSnapshot(StructureDefinition theInput, String theUrl, String theName) {
		// TODO Auto-generated method stub
		return null;
	}

}
*/