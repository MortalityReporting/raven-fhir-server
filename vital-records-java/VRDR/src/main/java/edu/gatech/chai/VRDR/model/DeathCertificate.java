package edu.gatech.chai.VRDR.model;

import java.util.Date;

import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Composition;
import org.hl7.fhir.r4.model.Organization;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.Practitioner;
import org.hl7.fhir.r4.model.Reference;
import org.hl7.fhir.r4.model.Resource;

import ca.uhn.fhir.model.api.annotation.ResourceDef;
import edu.gatech.chai.VRDR.model.util.CommonUtil;
import edu.gatech.chai.VRDR.model.util.DeathCertificateUtil;

@ResourceDef(name = "Composition", profile = "http://hl7.org/fhir/us/vrdr/StructureDefinition/vrdr-death-certificate")
public class DeathCertificate extends Composition {
	/**
	 *
	 */
	private static final long serialVersionUID = -2537704080131333367L;

	public DeathCertificate() {
		super();
		CommonUtil.initResource(this);
		setType(DeathCertificateUtil.typeFixedValue);
		setStatus(DeathCertificateUtil.status);
		setTitle(DeathCertificateUtil.title);
		setDate(new Date());
	}

	public DeathCertificate(CompositionStatus status, Decedent decedent, DeathCertificationProcedure resource) {
		this();
		setStatus(status);
		setSubject(new Reference(decedent));
		addEvent(resource);
	}

	public DeathCertificate(DeathCertificationProcedure resource) {
		this();
		addEvent(resource);
	}

	private CompositionAttesterComponent addAttesterCommon() {
		CompositionAttesterComponent component = new CompositionAttesterComponent();
		component.setMode(CompositionAttestationMode.LEGAL);
		return component;
	}

	public void addAttester(Patient resource, Date time) {
		addAttesterBase(resource,time);
	}

	public void addAttester(Practitioner resource, Date time) {
		addAttesterBase(resource,time);
	}

	public void addAttester(Organization resource, Date time) {
		addAttesterBase(resource,time);
	}

	private void addAttesterBase(Resource resource,Date time) {
		CompositionAttesterComponent component = addAttesterCommon();
		component.setTime(time);
		String resourceId = resource.getId();
		Reference resourceRef = !resourceId.startsWith("urn:uuid:")
			? new Reference("urn:uuid:" + resourceId)
			: new Reference(resourceId);
		component.setParty(resourceRef);
		addAttester(component);
		// author is required from VRDR IG http://hl7.org/fhir/us/vrdr/StructureDefinition-vrdr-death-certificate.html
		addAuthor(resourceRef);
	}

	public void addEvent(DeathCertificationProcedure resource) {
		CompositionEventComponent component = new CompositionEventComponent();
		component.addCode(DeathCertificateUtil.eventCodeFixedValue);
		component.addDetail(new Reference(resource.getId()));
		addEvent(component);
	}

	public void addResource(Resource resource) {
		if(CommonUtil.assignableFrom(resource.getClass(), DeathCertificateUtil.decedentDemographicResources)) {
			addResource(resource, DeathCertificateUtil.decendentDemographicsSectionCode);
		}
		else if(CommonUtil.assignableFrom(resource.getClass(), DeathCertificateUtil.deathInvestigationResources)) {
			addResource(resource, DeathCertificateUtil.deathInvestigationSectionCode);
		}
		else if(CommonUtil.assignableFrom(resource.getClass(), DeathCertificateUtil.deathCertificationResources)) {
			addResource(resource, DeathCertificateUtil.deathCertificationSectionCode);
		}
		else if(CommonUtil.assignableFrom(resource.getClass(), DeathCertificateUtil.decedentDispositionResources)) {
			addResource(resource, DeathCertificateUtil.decedentDispositionSectionCode);
		}
		else if(CommonUtil.assignableFrom(resource.getClass(), DeathCertificateUtil.codedContentResources)) {
			addResource(resource, DeathCertificateUtil.codedContentSectionCode);
		}
		else {
			throw new IllegalArgumentException("Resource not added successfully. " +
				"id: "	+ resource.getId() +
				"resourceType: " + resource.getClass().getCanonicalName());
		}
	}

	public void addResource(Resource resource, CodeableConcept sectionCode) {
		SectionComponent section = findOrCreateSection(sectionCode);
		section.addEntry(new Reference(resource.getId()));
	}

	public SectionComponent findOrCreateSection(CodeableConcept sectionCode) {
		for(SectionComponent section: this.getSection()) {
			if(section.getCode().equals(sectionCode)) {
				return section;
			}
		}
		SectionComponent newSection = new SectionComponent();
		newSection.setCode(sectionCode);
		this.addSection(newSection);
		return newSection;
	}
}