package com.project.reporting.reporting.producer;

import com.project.reporting.reporting.collector.DataCollector;
import com.project.reporting.reporting.saver.HtmlFileSaver;
import com.project.reporting.reporting.saver.Saver;

import java.io.IOException;
import java.nio.file.Paths;


public class HtmlDataProducerImpl<T> implements DataProducer<T> {
    private StringBuilder result = new StringBuilder();
    private String reportName;
    private final DataCollector<T> dataCollector;
    private HtmlFileSaver saver;

    public HtmlDataProducerImpl(DataCollector<T> dataCollector, HtmlFileSaver saver) {
        this.dataCollector = dataCollector;
        this.saver = saver;
    }

    @Override
    public Saver getSaver() {
        return saver;
    }

    public StringBuilder getResult() {
        return result;
    }

    public StringBuilder appendResult(StringBuilder appended) {
        return result.append(appended);
    }

    @Override
    public void beforeStart(Commit commit) {
        commit.doAction();
    }

    public DataCollector<T> getDataCollector() {
        return dataCollector;
    }

    @Override
    public void produce() {}

    @Override
    public void collect() {
        this.dataCollector.collect();
    }

    @Override
    public void generateHeader(String name) {
        this.reportName = name;
        StringBuilder html = new StringBuilder();
        html.append("<!doctype html>\n");
        html.append("<html lang='en'>\n");

        html.append("<head>\n");
        html.append("<meta charset='utf-8'>\n");
        html.append("<title>");
        html.append(this.reportName);
        html.append("</title>\n");
        html.append("</head>\n\n");
        html.append("<body>\n");

        result.append(html);
    }

    @Override
    public void generateFooter() {
        StringBuilder html = new StringBuilder();
        html.append("</body>\n\n");
        html.append("</html>");

        result.append(html);
    }

    @Override
    public void save() throws Exception {
        this.saver.setName(this.reportName);
        this.saver.setResult(this.result);
        this.saver.save();
    }

    @Override
    public void afterEnd(Commit commit) {
        commit.doAction();
    }
}
