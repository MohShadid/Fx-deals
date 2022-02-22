package com.fxdeals.demo.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fxdeals.demo.Exception.ReqBodyException;
import com.fxdeals.demo.entity.FxDeal;
import com.fxdeals.demo.service.FxDealsService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/FxDeals")
public class FxDealsController {

	@Autowired
	FxDealsService fxDealsService;

	final static Logger log = LogManager.getLogger(FxDealsController.class);

	Map<String, String> body = new HashMap<>();

	@ApiOperation(value = "save the Fx deals to MongoDB", notes = "xxxx")
	@ApiResponses(value = { //
			@ApiResponse(code = 200, message = "Success saving the Fx deals"),
			@ApiResponse(code = 400, message = "Error occurred in saving the Fx deals"), })
	@PostMapping(path = "/saveFxDeal", //
			produces = { //
					MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<?> saveFxDeals(@Valid @RequestBody FxDeal entity) {

		try {
			fxDealsService.saveFxDeals(entity);
			body.put("Response", "operation successful");
			return new ResponseEntity<>(body, HttpStatus.OK);
		} catch (ReqBodyException e) {
			body.put("Response", e.getMessage());
			return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
		}

	}

}
