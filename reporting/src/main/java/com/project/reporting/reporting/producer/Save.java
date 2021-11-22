package com.project.reporting.reporting.producer;

import java.io.IOException;

public interface Save {
    void doAction(String name, StringBuilder result, String extension) throws IOException;
}
