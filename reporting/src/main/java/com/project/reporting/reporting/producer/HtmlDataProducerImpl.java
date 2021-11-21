package com.project.reporting.reporting.producer;

import com.project.reporting.reporting.collector.DataCollector;

public class HtmlDataProducerImpl implements DataProducer {
    private StringBuilder result = new StringBuilder();
    private String reportName;
    private DataCollector dataCollector;

    public HtmlDataProducerImpl(DataCollector dataCollector) {
        this.dataCollector = dataCollector;
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

    public DataCollector getDataCollector() {
        return dataCollector;
    }

    @Override
    public void produce() {}

    @Override
    public void collect() {
        System.out.println("HTML COLLECT");
        this.dataCollector.collect();
    }

    @Override
    public void generateHeader(String name) {
        this.reportName = name;
        StringBuilder html = new StringBuilder();
        html.append( "<!doctype html>\n" );
        html.append( "<html lang='en'>\n" );

        html.append( "<head>\n" );
        html.append( "<meta charset='utf-8'>\n" );
        html.append( "<title>" + this.reportName + "</title>\n" );
        html.append( "</head>\n\n" );
        html.append( "<body>\n" );

        result.append(html);
    }

    @Override
    public void generateFooter() {
        StringBuilder html = new StringBuilder();
        html.append( "</body>\n\n" );
        html.append( "</html>" );

        result.append(html);
    }

    @Override
    public void save() {
        System.out.println(this.result);
    }

    @Override
    public void afterEnd(Commit commit) {
        commit.doAction();
    }
}
