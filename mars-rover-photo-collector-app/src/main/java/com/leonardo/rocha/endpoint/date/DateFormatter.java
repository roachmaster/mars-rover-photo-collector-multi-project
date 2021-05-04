package com.leonardo.rocha.endpoint.date;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter {
    private static final Logger logger = LoggerFactory.getLogger(DateFormatter.class);
    private static String[] possibleFormats = {
            "MM/dd/yy",
            "MMM d, yyyy",
            "MMM-d-yyyy"
    };

    public static String formatDate(String date) throws ParseException {
        logger.trace("Raw date from file: {}", date);
        Date parsedDate = DateUtils.parseDateStrictly(date, possibleFormats);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(parsedDate);
    }
}
