package com.project.reporting.reporting.saver;

import com.project.reporting.entity.ReportStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
@Slf4j
public class FileReader {
    @Value("${web.upload.path}")
    private String reportFolder;

    public byte[] read(ReportStatus reportStatus) {

        char separatorChar = File.separatorChar;
        String reportsLocation = new File(reportFolder).getAbsolutePath();

        log.info("reports location: " + reportsLocation);

        try (FileInputStream fileInputStream = new FileInputStream(reportsLocation + separatorChar + reportStatus.getGeneratedName())) {
            return fileInputStream.readAllBytes();
        } catch (FileNotFoundException e) {
            log.error("reportsLocation not found: " + reportsLocation, e);
            throw new RuntimeException(e);
        } catch (IOException e) {
            log.error("IOException" + reportsLocation, e);
            throw new RuntimeException(e);
        }
    }
}