package edu.gatech.chai.VRDR.cli;

import edu.gatech.chai.VRDR.context.VRDRFhirContext;
import edu.gatech.chai.VRDR.messaging.util.MessagingExample;

import org.hl7.fhir.r4.model.Bundle;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Spec;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Command(name = "vrdr-cli", version = "VRDR CLI v1.4.0-STU2", mixinStandardHelpOptions = true,
        description = "Generate example records for testing and pasting in Canary")
public class Console {
    private VRDRFhirContext ctx = new VRDRFhirContext();

    @Spec CommandSpec spec;

    @Option(names = { "-x", "--xml" }, description = "Output in XML format instead of JSON")
    boolean outputXml = false;

    @Command(name = "create",
        subcommands = { CommandLine.HelpCommand.class },
        description = "Creates an example message for pasting in Canary in JSON format")
    public void createSubmission(
        @Parameters(arity = "1..1", paramLabel = "<message-type>", description = "Type of message to generate, valid values: ${COMPLETION-CANDIDATES}")
        MessageTypeEnum messageType,
        @Parameters(arity = "1..1", paramLabel = "<output-flie>", description = "Path to the output file of the example")
        String outputFile) {

        MessagingExample example = new MessagingExample();
        Bundle message;
        switch (messageType) {
            case SUBMISSION:
                message = example.createDeathRecordSubmissionMessage();
                break;
            case UPDATE:
                message = example.createDeathRecordUpdateMessage();
                break;
            case VOID:
                message = example.createDeathRecordVoidMessage();
                break;
            default:
                throw new CommandLine.ParameterException(spec.commandLine(), "Invalid message type: " + messageType);
        }

        if (outputXml) {
            String bundleStr = ctx
                    .getCtx()
                    .newXmlParser()
                    .setPrettyPrint(true)
                    .encodeResourceToString(message);
            writeToFile(bundleStr, outputFile);
        }
        else {
            String bundleStr = ctx
                    .getCtx()
                    .newJsonParser()
                    .setPrettyPrint(true)
                    .encodeResourceToString(message);
            writeToFile(bundleStr, outputFile);
        }
    }

    public enum MessageTypeEnum {
        SUBMISSION("submission"),
        UPDATE("update"),
        VOID("void");
        private final String name;
        MessageTypeEnum(String name) {
            this.name = name;
        }
    }

    private void writeToFile(String str, String outputFile) {
        try {
            Path path = Paths.get(outputFile);
            byte[] strToBytes = str.getBytes();
            Files.write(path, strToBytes);
        }
        catch (Exception e) {
            System.out.println("Error writing to file: " + e);
        }
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new Console())
                .setSubcommandsCaseInsensitive(true)
                .setCaseInsensitiveEnumValuesAllowed(true)
                .execute(args);
        System.exit(exitCode);
    }

}
