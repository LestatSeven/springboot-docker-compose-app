package com.project.reporting.reporting.collector;

import java.util.List;

public interface DataCollector<T> {
    void collect();
    List<T> getData();
}