package edu.gatech.chai.VRCL.util;

import java.util.Arrays;
import java.util.StringJoiner;

import org.hl7.fhir.r4.model.HumanName;

public class HumanNameParser {
	private static final String[] prefixList = {"Mr.", "Mrs.", "Ms.", "Dr.", "Adm", "Capt", "Chief", "Gen", "Gov", "Hon", "Maj", "Prof", "Rabbi", "Rev", "Sister"};
	private static final String[] suffixList = {"II", "III", "IV", "CPA", "DDS", "Esq", "Jr", "LLD", "MD", "PhD", "Ret", "Sr", "DO"};
	public static HumanName createHumanName(String nameString) {
		HumanName returnName = new HumanName();
		String[] nameParts = nameString.split("\\s+");
		if(nameParts.length == 1) {
			returnName.addGiven(nameParts[0]);
			return returnName;
		}
		if(nameParts.length == 2) {
			returnName.addGiven(nameParts[0]);
			returnName.setFamily(nameParts[1]);
			return returnName;
		}
		
		boolean hasSuffix = false;
		int familyIndex = nameParts.length-1;
		int givenStartIndex = 0;
		if(Arrays.asList(prefixList).contains(nameParts[0])) {
			returnName.addPrefix(nameParts[0]);
			givenStartIndex = 1;
		}
		if(Arrays.asList(suffixList).contains(nameParts[nameParts.length-1])) {
			returnName.addSuffix(nameParts[nameParts.length-1]);
			familyIndex = nameParts.length-2;
		}
		String familyName = "";
		String givenName = "";
		if(hasSuffix) {
			familyIndex = nameParts.length - 2;
		}
		StringJoiner joiner = new StringJoiner(" ");
		for(int i = givenStartIndex; i < familyIndex; i++) {
			joiner.add(nameParts[i]);
		}
		givenName = joiner.toString();
		familyName = nameParts[familyIndex];
		returnName.addGiven(givenName);
		returnName.setFamily(familyName);
		return returnName;
	}
}
