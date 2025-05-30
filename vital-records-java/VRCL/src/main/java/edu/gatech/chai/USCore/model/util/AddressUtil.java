
package edu.gatech.chai.USCore.model.util;

import java.util.Arrays;
import java.util.HashSet;

import org.hl7.fhir.r4.model.Address;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Extension;
import org.hl7.fhir.r4.model.PositiveIntType;
import org.hl7.fhir.r4.model.StringType;

public class AddressUtil {
	public static String withinCityLimitsIndicatorUrl = "http://hl7.org/fhir/us/vrdr/StructureDefinition/WithinCityLimitsIndicator";
	public static String locationJurisdictionIdUrl = "http://hl7.org/fhir/us/vrdr/StructureDefinition/Location-Jurisdiction-Id";
	public static String cityCodeUrl = "http://hl7.org/fhir/us/vrdr/StructureDefinition/CityCode";
	public static String districtCodeUrl = "http://hl7.org/fhir/us/vrdr/StructureDefinition/DistrictCode";
	public static String predirectionalUrl = "http://hl7.org/fhir/us/vrdr/StructureDefinition/PreDirectional";
	public static String streetNumberUrl = "http://hl7.org/fhir/us/vrdr/StructureDefinition/StreetNumber";
	public static String streetNameUrl = "http://hl7.org/fhir/us/vrdr/StructureDefinition/StreetName";
	public static String streetDesignatorUrl = "http://hl7.org/fhir/us/vrdr/StructureDefinition/StreetDesignator";
	public static String postDirectionalUrl = "http://hl7.org/fhir/us/vrdr/StructureDefinition/PostDirectional";
	public static String unitOrApartmentNumberUrl = "http://hl7.org/fhir/us/vrdr/StructureDefinition/UnitOrAptNumber";
	public static String uspsSystemUrl = "https://www.usps.com/";
	
	public static final HashSet<CodeableConcept> locationJurisdictionalConceptSet = new HashSet<>(Arrays.asList(
			new CodeableConcept().addCoding(new Coding(uspsSystemUrl,"AL","Alabama")),
			new CodeableConcept().addCoding(new Coding(uspsSystemUrl,"AK","Alaska")),
			new CodeableConcept().addCoding(new Coding(uspsSystemUrl,"AR", "Arkansas")),
			new CodeableConcept().addCoding(new Coding(uspsSystemUrl,"AS", "American Samoa")),
			new CodeableConcept().addCoding(new Coding(uspsSystemUrl, "AZ", "Arizona")),
			new CodeableConcept().addCoding(new Coding(uspsSystemUrl,"CA", "California")),
			new CodeableConcept().addCoding(new Coding(uspsSystemUrl,"CO", "Colorado")),
			new CodeableConcept().addCoding(new Coding(uspsSystemUrl,"CT", "Connecticut")),
			new CodeableConcept().addCoding(new Coding(uspsSystemUrl,"DE", "Delaware")),
			new CodeableConcept().addCoding(new Coding(uspsSystemUrl,"DC", "District of Columbia")),
			new CodeableConcept().addCoding(new Coding(uspsSystemUrl,"FL", "Florida")),
			new CodeableConcept().addCoding(new Coding(uspsSystemUrl,"GA", "Georgia")),
			new CodeableConcept().addCoding(new Coding(uspsSystemUrl,"GU", "Guam")),
			new CodeableConcept().addCoding(new Coding(uspsSystemUrl,"HI", "Hawaii")),
			new CodeableConcept().addCoding(new Coding(uspsSystemUrl,"ID", "Idaho")),
			new CodeableConcept().addCoding(new Coding(uspsSystemUrl,"IL", "Illinois")),
			new CodeableConcept().addCoding(new Coding(uspsSystemUrl,"IN", "Indiana")),
			new CodeableConcept().addCoding(new Coding(uspsSystemUrl,"IA", "Iowa")),
			new CodeableConcept().addCoding(new Coding(uspsSystemUrl,"KA", "Kansas")),
			new CodeableConcept().addCoding(new Coding(uspsSystemUrl,"KY", "Kentucky")),
			new CodeableConcept().addCoding(new Coding(uspsSystemUrl,"LA", "Louisiana")),
			new CodeableConcept().addCoding(new Coding(uspsSystemUrl,"ME", "Maine")),
			new CodeableConcept().addCoding(new Coding(uspsSystemUrl,"MD", "Maryland")),	
			new CodeableConcept().addCoding(new Coding(uspsSystemUrl,"MA", "Massachusetts")),
			new CodeableConcept().addCoding(new Coding(uspsSystemUrl,"MI", "Michigan")),
			new CodeableConcept().addCoding(new Coding(uspsSystemUrl,"MN", "Minnesota")),
			new CodeableConcept().addCoding(new Coding(uspsSystemUrl,"MS", "Mississippi")),
			new CodeableConcept().addCoding(new Coding(uspsSystemUrl,"MO", "Missouri")),
			new CodeableConcept().addCoding(new Coding(uspsSystemUrl,"MT", "Montana")),
			new CodeableConcept().addCoding(new Coding(uspsSystemUrl,"NE", "Nebraska")),
			new CodeableConcept().addCoding(new Coding(uspsSystemUrl,"NV", "Nevada")),
			new CodeableConcept().addCoding(new Coding(uspsSystemUrl,"NH", "New Hampshire")),
			new CodeableConcept().addCoding(new Coding(uspsSystemUrl,"NJ", "New Jersey")),
			new CodeableConcept().addCoding(new Coding(uspsSystemUrl,"NM", "New Mexico")),
			new CodeableConcept().addCoding(new Coding(uspsSystemUrl,"YC", "New York City")), //Unique codesystem just for New York City
			new CodeableConcept().addCoding(new Coding(uspsSystemUrl,"NY", "New York")),
			new CodeableConcept().addCoding(new Coding(uspsSystemUrl,"MC", "North Carolina")),
			new CodeableConcept().addCoding(new Coding(uspsSystemUrl,"MD", "North Dekota")),
			new CodeableConcept().addCoding(new Coding(uspsSystemUrl,"MP", "Northern Mariana Islands")),
			new CodeableConcept().addCoding(new Coding(uspsSystemUrl,"OH", "Ohio")),
			new CodeableConcept().addCoding(new Coding(uspsSystemUrl,"OK", "Oklahoma")),
			new CodeableConcept().addCoding(new Coding(uspsSystemUrl,"OR", "Oregon")),
			new CodeableConcept().addCoding(new Coding(uspsSystemUrl,"PN", "Pennsylvania")),
			new CodeableConcept().addCoding(new Coding(uspsSystemUrl,"PR", "Puerto Rico")),
			new CodeableConcept().addCoding(new Coding(uspsSystemUrl,"RI", "Rhode Island")),
			new CodeableConcept().addCoding(new Coding(uspsSystemUrl,"SC", "South Carolina")),
			new CodeableConcept().addCoding(new Coding(uspsSystemUrl,"SD", "South Dakota")),
			new CodeableConcept().addCoding(new Coding(uspsSystemUrl,"TN", "Tennessee")),
			new CodeableConcept().addCoding(new Coding(uspsSystemUrl,"TX", "Texas")),
			new CodeableConcept().addCoding(new Coding(uspsSystemUrl,"VI", "Virgin Islands")),
			new CodeableConcept().addCoding(new Coding(uspsSystemUrl,"UT", "Utah")),
			new CodeableConcept().addCoding(new Coding(uspsSystemUrl,"VT", "Vermont")),
			new CodeableConcept().addCoding(new Coding(uspsSystemUrl,"VA", "Virginia")),
			new CodeableConcept().addCoding(new Coding(uspsSystemUrl,"WA", "Washington")),
			new CodeableConcept().addCoding(new Coding(uspsSystemUrl,"WV", "West Virginia")),
			new CodeableConcept().addCoding(new Coding(uspsSystemUrl,"WI", "Wisconsin")),
			new CodeableConcept().addCoding(new Coding(uspsSystemUrl,"WY", "Wyoming"))));
	public static Address addCityLimitsIndicator(CodeableConcept indicator,Address address) {
		Extension extension = new Extension();
		extension.setUrl(withinCityLimitsIndicatorUrl);
		extension.setValue(indicator);
		address.addExtension(extension);
		return address;
	}
	
	public static Address addCityLimitsIndicator(String indicator,Address address) {
		CodeableConcept ccIndicator = CommonUtil.findConceptFromCollectionUsingSimpleString(indicator, CommonUtil.yesNoUnknownSet);
		return addCityLimitsIndicator(ccIndicator, address);
	}
	
	public static Address addCityCode(PositiveIntType integer,Address address) {
		if(integer.getValueAsString().length() > 5) {
			throw new IllegalArgumentException("City code value "+integer.asStringValue()+" is too long, must be 5 characters or less.");
		}
		Extension extension = new Extension();
		extension.setUrl(cityCodeUrl);
		extension.setValue(integer);
		address.addExtension(extension);
		return address;
	}
	
	public static Address addCityCode(int integer,Address address) {
		return addCityCode(new PositiveIntType(integer), address);
	}
	
	public static Address addDistrictCode(PositiveIntType integer,Address address) {
		if(integer.getValueAsString().length() > 5) {
			throw new IllegalArgumentException("District code value "+integer.asStringValue()+" is too long, must be 5 characters or less.");
		}
		Extension extension = new Extension();
		extension.setUrl(districtCodeUrl);
		extension.setValue(integer);
		address.addExtension(extension);
		return address;
	}
	
	public static Address addStateJurisdiction(String stateValue,Address address) {
		if(stateValue.length() > 2) {
			throw new IllegalArgumentException("State Jurisdicition value "+stateValue+" is too long, must be 2 characters or less.");
		}
		Extension extension = new Extension();
		extension.setUrl(locationJurisdictionIdUrl);
		extension.setValue(new StringType(stateValue));
		address.addExtension(extension);
		return address;
	}
	
	public static Address addDistrictCode(int integer,Address address) {
		return addDistrictCode(new PositiveIntType(integer), address);
	}
	
	
	public static Address addPredirectional(StringType str,Address address) {
		if(str.getValueAsString().length() > 10) {
			throw new IllegalArgumentException("Predirectional value "+str.asStringValue()+" is too long, must be 10 characters or less.");
		}
		Extension extension = new Extension();
		extension.setUrl(predirectionalUrl);
		extension.setValue(str);
		address.addExtension(extension);
		return address;
	}
	
	public static Address addPredirectional(String str,Address address) {
		return addPredirectional(new StringType(str), address);
	}
	
	public static Address addStreetNumber(StringType str,Address address) {
		if(str.getValueAsString().length() > 10) {
			throw new IllegalArgumentException("StreetNumber value"+str.asStringValue()+" is too long, must be 10 characters or less.");
		}
		Extension extension = new Extension();
		extension.setUrl(streetNumberUrl);
		extension.setValue(str);
		address.addExtension(extension);
		return address;
	}
	
	public static Address addStreetNumber(String str,Address address) {
		return addStreetNumber(new StringType(str), address);
	}
	
	public static Address addStreetName(StringType str,Address address) {
		if(str.getValueAsString().length() > 50) {
			throw new IllegalArgumentException("StreetName value"+str.asStringValue()+" is too long, must be 50 characters or less.");
		}
		Extension extension = new Extension();
		extension.setUrl(streetNameUrl);
		extension.setValue(str);
		address.addExtension(extension);
		return address;
	}
	
	public static Address addStreetName(String str,Address address) {
		return addStreetName(new StringType(str), address);
	}
	
	public static Address addStreetDesignator(StringType str,Address address) {
		if(str.getValueAsString().length() > 10) {
			throw new IllegalArgumentException("StreetDesignator value"+str.asStringValue()+" is too long, must be 10 characters or less.");
		}
		Extension extension = new Extension();
		extension.setUrl(streetDesignatorUrl);
		extension.setValue(str);
		address.addExtension(extension);
		return address;
	}
	
	public static Address addStreetDesignator(String str,Address address) {
		return addStreetDesignator(new StringType(str), address);
	}
	
	public static Address addPostDirectional(StringType str,Address address) {
		if(str.getValueAsString().length() > 10) {
			throw new IllegalArgumentException("PostDirectional value"+str.asStringValue()+" is too long, must be 50 characters or less.");
		}
		Extension extension = new Extension();
		extension.setUrl(postDirectionalUrl);
		extension.setValue(str);
		address.addExtension(extension);
		return address;
	}
	
	public static Address addPostDirectional(String str,Address address) {
		return addPostDirectional(new StringType(str), address);
	}
	
	public static Address addUnitOrAparmentNumber(StringType str,Address address) {
		if(str.getValueAsString().length() > 10) {
			throw new IllegalArgumentException("UnitOrAparmentNumber value"+str.asStringValue()+" is too long, must be 50 characters or less.");
		}
		Extension extension = new Extension();
		extension.setUrl(unitOrApartmentNumberUrl);
		extension.setValue(str);
		address.addExtension(extension);
		return address;
	}
	
	public static Address addUnitOrAparmentNumber(String str,Address address) {
		return addUnitOrAparmentNumber(new StringType(str), address);
	}
}
