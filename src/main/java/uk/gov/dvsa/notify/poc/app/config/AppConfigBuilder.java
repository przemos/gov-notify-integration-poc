package uk.gov.dvsa.notify.poc.app.config;

import org.apache.commons.cli.*;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class AppConfigBuilder {

    private static final String OPT_CONFIG_FILE = "configFile";

    public AppConfig build(String[] args) {

        Options options = OptionsProvider.get();
        CommandLine cmdLine;

        try {

            cmdLine = new DefaultParser().parse(options, args);
            return parseConfig(cmdLine);

        } catch (ParseException e) {
            onParseException(e, options);
        }

        return null;
    }


    private AppConfig parseConfig(CommandLine cmdLine) {

        String configFilePath = cmdLine.getOptionValue(OPT_CONFIG_FILE);

        try {

            InputStream configFileIs = Files.newInputStream(Paths.get(configFilePath));
            return new Yaml().loadAs(configFileIs, AppConfig.class);

        } catch (IOException configFileEx) {
            System.err.println("Problems reading file: " + configFilePath);
            configFileEx.printStackTrace(System.err);
        }

        return null;
    }

    private void onParseException(ParseException e, Options options) {

        HelpFormatter formatter = new HelpFormatter();
        System.err.println("Parsing failed, reason: " + e.getMessage());
        System.err.flush();
        formatter.printHelp("GovNotifyPoc", options, true);
    }
}
