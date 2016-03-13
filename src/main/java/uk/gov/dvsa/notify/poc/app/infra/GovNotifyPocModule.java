package uk.gov.dvsa.notify.poc.app.infra;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import uk.gov.dvsa.notify.poc.app.App;
import uk.gov.dvsa.notify.poc.app.config.AppConfig;
import uk.gov.dvsa.notify.poc.app.service.client.INotifyClient;
import uk.gov.dvsa.notify.poc.app.service.client.NotifyClient;
import uk.gov.dvsa.notify.poc.app.service.locator.IngestionResourceLocator;
import uk.gov.dvsa.notify.poc.app.service.parser.IngestionItemParser;

public class GovNotifyPocModule extends AbstractModule {

    private final AppConfig config;

    public GovNotifyPocModule(AppConfig config) {
        this.config = config;
    }

    @Override
    protected void configure() {
        bind(AppConfig.class).toInstance(config);
        bind(IngestionResourceLocator.class);
        bind(IngestionItemParser.class);
    }

    @Provides
    @Singleton
    @Inject
    public INotifyClient provideNotifyClient(AppConfig config) {
        return new NotifyClient(config.getServiceUrl());
    }


    @Provides
    @Singleton
    @Inject
    public App provideApp(IngestionResourceLocator locator, IngestionItemParser parser, INotifyClient client) {
        return new App(locator, parser, client);
    }
}
