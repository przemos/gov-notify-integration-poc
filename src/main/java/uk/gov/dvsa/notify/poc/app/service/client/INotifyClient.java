package uk.gov.dvsa.notify.poc.app.service.client;

import java.util.Map;

public interface INotifyClient {
    NotifyClientResponse push(Map<String, String> data) throws Exception;
}
