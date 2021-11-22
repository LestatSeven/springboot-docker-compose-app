package com.project.reporting.reporting.saver;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class HtmlFileSaver implements Saver {
    @Value("${web.upload.path}")
    private String documentDirectory;

    private String name;
    private StringBuilder result;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public StringBuilder getResult() {
        return result;
    }

    public void setResult(StringBuilder result) {
        this.result = result;
    }

    public void save() throws IOException {
        String reportsLocation = new File("reporting\\src\\main\\resources\\static\\reports").getAbsolutePath();
        System.out.println(reportsLocation);
        FileOutputStream fileOutputStream = new FileOutputStream(reportsLocation + "\\" + name + ".html");
        fileOutputStream.write(result.toString().getBytes());
        fileOutputStream.close();
    }}
