package uk.gov.dvsa.notify.poc.app.service.client;


import org.apache.http.Header;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.message.BasicHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class NotifyClient implements INotifyClient {

    private static final Logger logger = LoggerFactory.getLogger(NotifyClient.class);

    private final String serviceUrl;

    public NotifyClient(String serviceUrl) {
        this.serviceUrl = serviceUrl;
    }

    public NotifyClientResponse push(Map<String, String> data) throws Exception {

        logger.info("Pushing {}", data);

        Response r = Request.Post(serviceUrl)
                .connectTimeout(1000)
                .socketTimeout(1000)
                .addHeader(prepareToken())
                .execute();
        logger.info("status {}", r.returnResponse().getStatusLine().getStatusCode());

        // build an envelope
        // encrypt payload
        // push to notification service

        return null;
    }

    private Header prepareToken() {
        return new BasicHeader("Authorization", "Bearer xxx.yyy.zzz");
    }

}
