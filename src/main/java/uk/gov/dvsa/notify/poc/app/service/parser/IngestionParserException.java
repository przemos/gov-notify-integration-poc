package uk.gov.dvsa.notify.poc.app.service.parser;


class IngestionParserException extends Exception {

    public IngestionParserException(String filepath, Exception sourceException) {
        super("Parsing file: " + filepath + " failed", sourceException);
    }
}
