package com.fxdeals.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import lombok.Data;

@Component
@Data
public class MongoProperties {

	    @Value ("${mongo.uri}")
	    private String mongoUri;
	    @Value ("${mongo.port}")
	    private int mongoPort;
	    @Value ("${mongo.db}")
	    private String mongoDb;
	

}