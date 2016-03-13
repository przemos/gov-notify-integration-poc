package uk.gov.dvsa.notify.poc.app;

import uk.gov.dvsa.notify.poc.app.service.client.INotifyClient;
import uk.gov.dvsa.notify.poc.app.service.locator.IngestionResourceLocator;
import uk.gov.dvsa.notify.poc.app.service.parser.IngestionItemParser;

import java.nio.file.Path;
import java.util.Collection;
import java.util.stream.Collectors;

public class App {

    private final IngestionResourceLocator locator;
    private final IngestionItemParser parser;
    private final INotifyClient client;

    public App(IngestionResourceLocator locator, IngestionItemParser parser, INotifyClient client) {
        this.locator = locator;
        this.parser = parser;
        this.client = client;
    }


    public void runFor(Path directory) {

        locator.locate(directory)
                .stream()
                .map(f -> unchecked(() -> parser.parse(f)))
                .flatMap(Collection::stream)
                .map(m -> unchecked(() -> client.push(m)))
                .collect(Collectors.toList());
    }



    public static class ProcessingException extends RuntimeException {
        public ProcessingException(Throwable t) {
            super(t);
        }
    }

    @FunctionalInterface
    private interface CheckedSupplier<R> {
        R get() throws Throwable;
    }

    private <R> R unchecked(CheckedSupplier<R> function) {
        try {
            return function.get();
        } catch (Throwable e) {
            throw new ProcessingException(e);
        }
    }
}
