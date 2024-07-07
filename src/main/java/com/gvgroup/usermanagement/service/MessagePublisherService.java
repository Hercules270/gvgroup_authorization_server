package com.gvgroup.usermanagement.service;

public interface MessagePublisherService {

    void publish(String destination, Object message);

}
