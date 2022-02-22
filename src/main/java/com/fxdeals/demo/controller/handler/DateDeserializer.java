package com.fxdeals.demo.controller.handler;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class DateDeserializer extends JsonDeserializer<Date> {

	final static Logger log= LogManager.getLogger(DateDeserializer.class);

    @Override
    public Date deserialize(JsonParser jsonParser, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
    	log.info("format the date");
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        String date = jsonParser.getText();
        try {

            return format.parse(date);
        } catch (ParseException e) {
        	log.error("Exceptions happen! -->",e);
            throw new IOException("Wrong date format dd-MM-yyyy hh:mm:ss --> " + e.getMessage());
        }
    }

}