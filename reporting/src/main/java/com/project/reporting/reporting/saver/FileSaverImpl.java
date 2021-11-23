package com.project.reporting.reporting.saver;

import com.project.reporting.entity.ReportStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
@Slf4j
public class FileSaverImpl implements Saver {
    @Value("${web.upload.path}")
    private String reportFolder;

    public void save(ReportStatus reportStatus, byte[] result) {
        String reportName = reportStatus.getGeneratedName();
        char separatorChar = File.separatorChar;
        log.info("reports location: " + reportFolder);
        String reportsLocation = new File(reportFolder).getAbsolutePath();


        try (FileOutputStream fileOutputStream = new FileOutputStream(reportsLocation + separatorChar + reportName)) {
            fileOutputStream.write(result);
        } catch (FileNotFoundException e) {
            log.error("reportsLocation not found: " + reportsLocation, e);
            throw new RuntimeException(e);
        } catch (IOException e) {
            log.error("IOException" + reportsLocation, e);
            throw new RuntimeException(e);
        }
    }
}