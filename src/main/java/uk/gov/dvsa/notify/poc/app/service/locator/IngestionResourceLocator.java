package uk.gov.dvsa.notify.poc.app.service.locator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class IngestionResourceLocator {

    private static final Logger log = LoggerFactory.getLogger(IngestionResourceLocator.class);

    public IngestionResourceLocator() {
    }

    public List<Path> locate(Path directory) {

        List<Path> list = Stream.of(directory.toFile().listFiles((dir, name) -> name.endsWith("csv")))
                .map(File::toPath)
                .collect(Collectors.toList());

        log.info("Found resources: {}", list.size());

        return list;
    }
}
