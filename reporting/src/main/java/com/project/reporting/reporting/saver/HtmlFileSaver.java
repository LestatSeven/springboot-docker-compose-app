package com.project.reporting.reporting.saver;

import lombok.AllArgsConstructor;
import lombok.Builder;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Builder
public class HtmlFileSaver implements Saver {
    private String location;
    private String name;
    private StringBuilder result;

    public void save() throws IOException {
        //var location = Paths.get(documentStorageProperty.getDirectory()).toAbsolutePath().normalize();
        Files.createDirectories(Path.of(location));

        File file = new File(location + "/" + name  + ".html");
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write(result.toString());
        writer.close();
    }
}
