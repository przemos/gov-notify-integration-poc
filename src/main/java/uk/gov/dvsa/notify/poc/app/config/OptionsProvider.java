package uk.gov.dvsa.notify.poc.app.config;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

class OptionsProvider {

    public static Options get() {

        Options options = new Options();
        Option configFile = Option.builder("configFile").argName("configFile")
                .hasArg()
                .desc("path to a config file")
                .required()
                .build();
        options.addOption(configFile);

        return options;
    }
}
