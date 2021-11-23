package com.project.reporting.reporting.collector;

import java.util.List;

public interface DataCollector<T> {
    List<T> getData();
}
