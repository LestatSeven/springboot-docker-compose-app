package com.project.reporting.service;

public interface RabbitReceiverService {
    void receiveMessage(String message);
}
