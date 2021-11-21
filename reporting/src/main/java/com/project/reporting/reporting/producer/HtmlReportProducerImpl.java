package com.project.reporting.reporting.producer;

import com.project.reporting.reporting.collector.DataCollector;

public class HtmlReportProducerImpl implements ReportProducer {
    DataCollector dataCollector;

    public HtmlReportProducerImpl(DataCollector dataCollector) {
        this.dataCollector = dataCollector;
    }

    @Override
    public void collect() {
        this.dataCollector.collect();
    }

    @Override
    public void produce() {
        System.out.println("produce");
    }

    @Override
    public void commit() {
        System.out.println("commit");
    }
}
