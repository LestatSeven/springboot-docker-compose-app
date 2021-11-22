package com.project.reporting.reporting.producer;


import com.project.reporting.reporting.saver.Saver;

import java.io.IOException;

public interface DataProducer<T> {
    void beforeStart(Commit commit);
    void produce();
    void collect();
    void generateHeader(String name);
    void generateFooter();
    void save() throws Exception;
    void afterEnd(Commit commit);
    Saver getSaver();
}
