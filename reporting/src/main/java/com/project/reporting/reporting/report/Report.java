package com.project.reporting.reporting.report;

import com.project.reporting.reporting.collector.DataCollector;
import com.project.reporting.reporting.producer.DataProducer;
import com.project.reporting.reporting.saver.Saver;

public interface Report<T> {
    DataCollector<T> getCollector();
    DataProducer<T> getProducer();
    Saver getSaver();
}
