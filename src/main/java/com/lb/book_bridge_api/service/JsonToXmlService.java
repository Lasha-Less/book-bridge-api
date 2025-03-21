package com.lb.book_bridge_api.service;

import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JsonToXmlService {

    @Autowired
    private ProducerTemplate producerTemplate;

    public String convertJsonToXml(String jsonBody) {
        return producerTemplate.requestBody("direct:jsonToXmlFromBody", jsonBody, String.class);
    }

}
