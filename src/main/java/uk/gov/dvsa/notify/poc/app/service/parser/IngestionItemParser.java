package uk.gov.dvsa.notify.poc.app.service.parser;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class IngestionItemParser {

    private static final Logger log = LoggerFactory.getLogger(IngestionItemParser.class);

    public IngestionItemParser() {
    }

    public List<Map<String, String>> parse(Path filepath) throws IngestionParserException {

        List<Map<String, String>> records = new ArrayList<>();

        try (BufferedReader fileReader = Files.newBufferedReader(filepath)) {

            try (CSVParser csvReader = CSVFormat.DEFAULT.withHeader().parse(fileReader)) {

                for (CSVRecord record : csvReader) {
                    records.add(record.toMap());
                }
            }
        } catch (IOException e) {
            throw new IngestionParserException(filepath.toFile().getAbsolutePath(), e);
        }

        log.info("Parsed {} records for {}", records.size(), filepath);
        return records;
    }
}
