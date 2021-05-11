package com.leonardo.rocha.endpoint;

import com.leonardo.rocha.endpoint.date.DateFormatter;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultDatesService implements DatesService {

    @Value("#{datesFileName}")
    private Resource datesFileName;

    private final Logger logger = LoggerFactory.getLogger(DatesService.class);

    private List<String> dateList = new ArrayList<String>();

    @Override
    public List<String> getDates() {
        return dateList;
    }

    @PostConstruct
    public void initializeDates() {
        try{
        	logger.info("LEO LOOK:" + datesFileName);
            setDateList(datesFileName);
        } catch(IOException e) {
            logger.error("Error reading file {}", datesFileName, e.getStackTrace());
        }
    }

    private void setDateList(Resource resource) throws IOException {
        if(dateList.isEmpty()){
        	InputStream istream = resource.getInputStream();
            List<String> dates = IOUtils.readLines(istream, StandardCharsets.UTF_8);
        	formatDates(dates);
        }
    }

    private void formatDates(List<String> dates){
        for(String date : dates){
            formatAndAddDate(date);
        }
    }

    private void formatAndAddDate(String date){
        try {
            dateList.add(DateFormatter.formatDate(date));
        } catch (ParseException e){
            logger.error("{} is an invalid date.", date, e.getMessage());
        }
    }
}
