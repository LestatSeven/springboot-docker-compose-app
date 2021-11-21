package com.project.reporting.reporting.producer;

public interface ReportProducer {
    void collect();
    void produce();
    void commit();
}
