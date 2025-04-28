package com.zxcjabka.taskservice.service.misc;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
@Component
public class LocalDateTimeParser {

    public LocalDateTime parse(String dateTimeStr) {
        if(dateTimeStr == null || dateTimeStr.isEmpty()) {
            return null;
        }
        String[] patterns = {
                "yyyy-MM-dd'T'HH:mm:ss",
                "yyyy-MM-dd HH:mm:ss",
                "yyyy-MM-dd",
                "dd.MM.yyyy HH:mm",
        };

        for (String pattern : patterns) {
            if(pattern.equals("yyyy-MM-dd")) {
                try{
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
                    LocalDate date = LocalDate.parse(dateTimeStr, formatter);
                    return date.atStartOfDay();
                } catch (Exception ignored) { }
            }
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
                return LocalDateTime.parse(dateTimeStr, formatter);
            } catch (Exception ignored) {
            }
        }
        throw new IllegalArgumentException("Не удалось распознать дату: " + dateTimeStr);
    }
}
