package com.project.reporting.reporting.collector;

import org.springframework.jdbc.core.RowMapper;

public interface DatabaseDataCollector<T> extends DataCollector<T> {
    void collect(String query, RowMapper<T> mapper);
}
