package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public  class TextSalaryParser implements SalaryRecordParser {

    private static final Logger log = LoggerFactory.getLogger(TextSalaryParser.class);

    @Override
    public List<SalaryRecord> parse(File file) throws IOException {
        Path filePath = Path.of(file.getAbsolutePath());
        List<String> lines = Files.readAllLines(filePath);
        List<SalaryRecord> results = new ArrayList<>();
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            Pattern recordPattern = Pattern.compile("^(\\w+),(\\d+),(\\d{2}-\\d{2}-\\d{4})$");
            Matcher matcher = recordPattern.matcher(line);
            try {
                if (matcher.matches()) {
                    String name = matcher.group(1);
                    int salary = Integer.parseInt(matcher.group(2));

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                    LocalDate date = LocalDate.parse(matcher.group(3), formatter);
                    results.add(new SalaryRecord(name, salary, date));
                } else {
                    log.error("Invalid record format!{}", line);
                }
            } catch (Exception e) {
                log.error("Invalid record format!{} {}", line, e.getMessage());
            }
        }
        return results;
    }
}