package uk.gov.dvsa.notify.poc.app;

import com.google.inject.Guice;
import com.google.inject.Injector;
import uk.gov.dvsa.notify.poc.app.config.AppConfig;
import uk.gov.dvsa.notify.poc.app.config.AppConfigBuilder;
import uk.gov.dvsa.notify.poc.app.infra.GovNotifyPocModule;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws Throwable {

        new Main().bootstrap(new AppConfigBuilder(), args);
    }


    private void bootstrap(AppConfigBuilder builder, String[] args) throws Throwable {

        AppConfig config = builder.build(args);
        if (config == null) return;

        Injector injector = Guice.createInjector(new GovNotifyPocModule(config));
        App app = injector.getInstance(App.class);
        Path srcDir = Paths.get(config.getSourceDirectory());

        try {
            app.runFor(srcDir);
        } catch (App.ProcessingException pe) {
            throw pe.getCause();
        }
    }
}

