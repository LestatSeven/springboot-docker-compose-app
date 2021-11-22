package com.project.reporting.service;

import java.io.IOException;

public interface RabbitReceiverService {
    void receiveMessage(String message) throws IOException;
}
