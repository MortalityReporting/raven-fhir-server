package edu.gatech.chai.VRDR.messaging;

import ca.uhn.fhir.model.api.annotation.ResourceDef;
import edu.gatech.chai.VRDR.messaging.util.MessageParseException;
import edu.gatech.chai.VRDR.model.util.CommonUtil;
import org.hl7.fhir.r4.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@ResourceDef(name = "ExtractionErrorMessage", profile = "http://cdc.gov/nchs/nvss/fhir/vital-records-messaging/StructureDefinition/VRM-ExtractionErrorMessage")
public class ExtractionErrorMessage extends BaseMessage {

    public static final String MESSAGE_TYPE = "http://nchs.cdc.gov/vrdr_extraction_error";

    private OperationOutcome details;

    public ExtractionErrorMessage() {
        super(MESSAGE_TYPE);
    }

    public ExtractionErrorMessage(BaseMessage sourceMessage) {
        this(sourceMessage == null ? null : sourceMessage.getId(),
                sourceMessage == null ? null : sourceMessage.getMessageSource(),
                sourceMessage == null ? null : sourceMessage.getMessageDestination());
        setCertNo(sourceMessage == null ? null : sourceMessage.getCertNo());
        setStateAuxiliaryId(sourceMessage == null ? null : sourceMessage.getStateAuxiliaryId());
        setJurisdictionId(sourceMessage == null ? null : sourceMessage.getJurisdictionId());
        setDeathYear(sourceMessage == null ? null : sourceMessage.getDeathYear());
    }

    public ExtractionErrorMessage(Bundle messageBundle) {
        super(messageBundle);
        try {
            OperationOutcome operationOutcome = CommonUtil.findEntry(messageBundle, OperationOutcome.class);
            if (operationOutcome == null) {
                throw new IllegalArgumentException("No OperationOutcome found in the passed messageBundle");
            }
            this.details = operationOutcome;
        } catch (IllegalArgumentException ex) {
            throw new MessageParseException("Error processing entry in the message: " + ex, messageBundle);
        }
    }

    public ExtractionErrorMessage(String messageId, String destination, String source) {
        super(MESSAGE_TYPE);
        messageHeader.getSource().setEndpoint(source != null ? source : "http://nchs.cdc.gov/vrdr_submission");
        setMessageDestination(destination);
        MessageHeader.MessageHeaderResponseComponent resp = new MessageHeader.MessageHeaderResponseComponent();
        resp.setIdentifier(messageId);
        resp.setCode(MessageHeader.ResponseType.FATALERROR);
        messageHeader.setResponse(resp);

        details = new OperationOutcome();
        details.setId(UUID.randomUUID().toString());
        Bundle.BundleEntryComponent entry = new Bundle.BundleEntryComponent();
        entry.setFullUrl(ensureRefPrefix(details.getId()));
        entry.setResource(details);
        addEntry(entry);
        messageHeader.getResponse().setDetails(new Reference(ensureRefPrefix(details.getId())));
    }

    public ExtractionErrorMessage(String messageId, String destination) {
        this(messageId, destination, DeathRecordSubmissionMessage.MESSAGE_TYPE);
    }

    public String getIGMessageType() {
        return MESSAGE_TYPE;
    }

    public String getFailedMessageId() {
        return messageHeader != null && messageHeader.getResponse() != null ? messageHeader.getResponse().getIdentifier() : null;
    }

    public void setFailedMessageId(String value) {
        if (messageHeader.getResponse() == null) {
            messageHeader.setResponse(new MessageHeader.MessageHeaderResponseComponent());
            messageHeader.getResponse().setCode(MessageHeader.ResponseType.FATALERROR);
        }
        messageHeader.getResponse().setIdentifier(value);
    }

    public void addIssue(OperationOutcome.IssueSeverity issueSeverity, OperationOutcome.IssueType issueType, String message) {
        Issue issue = new Issue(issueSeverity, issueType, message);
        getIssues().add(issue);
    }

    public List<Issue> getIssues() {
        List<Issue> issues = new ArrayList<>();
        for (OperationOutcome.OperationOutcomeIssueComponent detailEntry : details.getIssue()) {
            Issue issue = new Issue(detailEntry.getSeverity(), detailEntry.getCode(), detailEntry.getDiagnostics());
            issues.add(issue);
        }
        return issues;
    }

    public void setIssues(List<Issue> issues) {
        details.getIssue().clear();
        for (Issue issue : issues) {
            OperationOutcome.OperationOutcomeIssueComponent detailEntry = new OperationOutcome.OperationOutcomeIssueComponent();
            detailEntry.setSeverity(issue.getIssueSeverity());
            detailEntry.setCode(issue.getIssueType());
            detailEntry.setDiagnostics(issue.getDescription());
            details.addIssue(detailEntry);
        }
    }

    public static class Issue {
        private OperationOutcome.IssueSeverity issueSeverity;
        private OperationOutcome.IssueType issueType;
        private String description;

        public OperationOutcome.IssueSeverity getIssueSeverity() {
            return issueSeverity;
        }

        public OperationOutcome.IssueType getIssueType() {
            return issueType;
        }

        public String getDescription() {
            return description;
        }

        public Issue(OperationOutcome.IssueSeverity issueSeverity, OperationOutcome.IssueType issueType, String description) {
            this.issueSeverity = issueSeverity;
            this.issueType = issueType;
            this.description = description;
        }
    }

    @Override
    public ResourceType getResourceType() {
        return ResourceType.Bundle;
    }

}