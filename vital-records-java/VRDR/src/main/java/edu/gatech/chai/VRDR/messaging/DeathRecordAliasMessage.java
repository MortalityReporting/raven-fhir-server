package edu.gatech.chai.VRDR.messaging;

import ca.uhn.fhir.model.api.annotation.ResourceDef;
import edu.gatech.chai.VRDR.model.DeathCertificateDocument;
import edu.gatech.chai.VRDR.model.Decedent;
import edu.gatech.chai.VRDR.model.DecedentFather;
import edu.gatech.chai.VRDR.model.util.DecedentUtil;
import org.hl7.fhir.r4.model.*;

@ResourceDef(name = "DeathRecordAliasMessage", profile = "http://cdc.gov/nchs/nvss/fhir/vital-records-messaging/StructureDefinition/VRM-DeathRecordAliasMessage")
public class DeathRecordAliasMessage extends BaseMessage {

    public static final String MESSAGE_TYPE = "http://nchs.cdc.gov/vrdr_alias";

    public DeathRecordAliasMessage() {
        super(MESSAGE_TYPE);
    }

    public DeathRecordAliasMessage(Bundle messageBundle) {
        super(messageBundle);
    }

    public DeathRecordAliasMessage(DeathCertificateDocument record) {
        super(MESSAGE_TYPE);
        extractBusinessIdentifiers(record);
        if (record.getDecedent() != null && record.getDecedent().size() > 0 && record.getDecedent().get(0) != null) {
            Decedent decedent = record.getDecedent().get(0);
            if (decedent.hasName()) {
                HumanName name = decedent.getName().get(0);
                if (name.hasGiven()) {
                    String firstName = name.getGiven().get(0).getValue();
                    setAliasDecedentFirstName(firstName);
                }
                if (name.hasGiven() && name.getGiven().size() > 1 && name.getGiven().get(1) != null) {
                    String middleName = name.getGiven().get(1).getValue();
                    setAliasDecedentMiddleName(middleName);
                }
                if (name.hasFamily()) {
                    String lastName = name.getFamily();
                    setAliasDecedentLastName(lastName);
                }
                if (name.hasSuffix()) {
                    String suffix = name.getSuffixAsSingleString();
                    setAliasDecedentNameSuffix(suffix);
                }
            }
            if (decedent.hasIdentifier()) {
                for (Identifier identifier : decedent.getIdentifier()) {
                    if (identifier.hasSystem() && identifier.getSystem().equals(DecedentUtil.identifierSystem)
                            && identifier.getType().hasCoding()) {
                        String ssn = identifier.getValue();
                        setAliasSocialSecurityNumber(ssn);
                        break;
                    }
                }
            }
        }
        if (record.getDecedentFather() != null && record.getDecedentFather().size() > 0 && record.getDecedentFather().get(0) != null) {
            DecedentFather father = record.getDecedentFather().get(0);
            if (father.hasName()) {
                String fatherSurname = father.getName().get(0).getFamily();
                setAliasFatherSurname(fatherSurname);
            }
        }
    }

    public String getIGMessageType() {
        return MESSAGE_TYPE;
    }

    public String getAliasDecedentFirstName() {
        Type type = messageParameters.getParameter("alias_decedent_first_name");
        if (type instanceof StringType) {
            return ((StringType) type).getValue();
        }
        return null;
    }

    public void setAliasDecedentFirstName(String aliasDecedentFirstName) {
        setSingleStringValue("alias_decedent_first_name", aliasDecedentFirstName);
    }

    public String getAliasDecedentLastName() {
        Type type = messageParameters.getParameter("alias_decedent_last_name");
        if (type instanceof StringType) {
            return ((StringType) type).getValue();
        }
        return null;
    }

    public void setAliasDecedentLastName(String aliasDecedentLastName) {
        setSingleStringValue("alias_decedent_last_name", aliasDecedentLastName);
    }

    public String getAliasDecedentMiddleName() {
        Type type = messageParameters.getParameter("alias_decedent_middle_name");
        if (type instanceof StringType) {
            return ((StringType) type).getValue();
        }
        return null;
    }

    public void setAliasDecedentMiddleName(String aliasDecedentMiddleName) {
        setSingleStringValue("alias_decedent_middle_name", aliasDecedentMiddleName);
    }

    public String getAliasDecedentNameSuffix() {
        Type type = messageParameters.getParameter("alias_decedent_name_suffix");
        if (type instanceof StringType) {
            return ((StringType) type).getValue();
        }
        return null;
    }

    public void setAliasDecedentNameSuffix(String aliasDecedentNameSuffix) {
        setSingleStringValue("alias_decedent_name_suffix", aliasDecedentNameSuffix);
    }

    public String getAliasFatherSurname() {
        Type type = messageParameters.getParameter("alias_father_surname");
        if (type instanceof StringType) {
            return ((StringType) type).getValue();
        }
        return null;
    }

    public void setAliasFatherSurname(String aliasFatherSurname) {
        setSingleStringValue("alias_father_surname", aliasFatherSurname);
    }

    public String getAliasSocialSecurityNumber() {
        Type type = messageParameters.getParameter("alias_social_security_number");
        if (type instanceof StringType) {
            return ((StringType) type).getValue();
        }
        return null;
    }

    public void setAliasSocialSecurityNumber(String aliasSocialSecurityNumber) {
        setSingleStringValue("alias_social_security_number", aliasSocialSecurityNumber);
    }

}
