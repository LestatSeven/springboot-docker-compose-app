package com.project.reporting.reporting.producer;



public interface DataProducer {
    void beforeStart(Commit commit);
    void produce();
    void collect();
    void generateHeader(String name);
    void generateFooter();
    void save();
    void afterEnd(Commit commit);
}
