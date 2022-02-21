package com.progressSoft.demo;


import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.progressSoft.demo.controller.FxDealsController;
import com.progressSoft.demo.entity.FxDeal;
import com.progressSoft.demo.model.mongo.FxDealRepository;


@WebMvcTest(FxDealsController.class)
class ProgressSoftDemoApplicationTests {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	FxDealRepository fxDealRepository;

	@Autowired
	ObjectMapper mapper;
	
	Map<String, Object> Body = new HashMap<>();

	@Test
	void saveRecord_success() throws Exception {

		Body.put("id", BigDecimal.ZERO);
		Body.put("fromCurrencyCode", "EUR");
		Body.put("toCurrencyCode", "USD");
		Body.put("dealAmount", BigDecimal.valueOf(100));
		Body.put("dealTime", "22-07-2022 22:54:00");

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/FxDeals/saveFxDeal")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(Body));

		mockMvc.perform(mockRequest).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$", CoreMatchers.notNullValue()))
				.andExpect(MockMvcResultMatchers.jsonPath("$.Response", CoreMatchers.is("operation successful")));
	}

	@Test
	void saveRecord_falidWrongArgs() throws Exception {

		Body.put("id", null);
		Body.put("fromCurrencyCode", "EUR");
		Body.put("toCurrencyCode", "USDA");
		Body.put("dealAmount", BigDecimal.valueOf(100));
		Body.put("dealTime", "22-07-2022 22:54:00");

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/FxDeals/saveFxDeal")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(Body));

		mockMvc.perform(mockRequest).andExpect(MockMvcResultMatchers.status().isBadRequest()).andExpect(
				result -> Assert.assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException));

	}

	@Test
	void saveRecord_falidWrongDateFormat() throws Exception {
		String date = "22-07-2022 22:10";
		Body.put("id", BigDecimal.ZERO);
		Body.put("fromCurrencyCode", "EUR");
		Body.put("toCurrencyCode", "USDA");
		Body.put("dealAmount", BigDecimal.valueOf(100));
		Body.put("dealTime", "22-07-2022 22:10");
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/FxDeals/saveFxDeal")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(Body));

		mockMvc.perform(mockRequest).andExpect(MockMvcResultMatchers.status().isBadRequest())
				.andExpect(
						result -> Assert.assertTrue(result.getResolvedException().getCause() instanceof java.io.IOException))
				.andExpect(result -> Assert.assertEquals(
						"Wrong date format dd-MM-yyyy hh:mm:ss --> Unparseable date: \"" + date + "\"",
						result.getResolvedException().getCause().getMessage()));
	}
	
	@Test
	void saveSameRecord() throws Exception {

		Body.put("id", BigDecimal.ZERO);
		Body.put("fromCurrencyCode", "EUR");
		Body.put("toCurrencyCode", "USD");
		Body.put("dealAmount", BigDecimal.valueOf(100));
		Body.put("dealTime", "22-07-2022 22:10:00");
		
		FxDeal fxDeal =FxDeal.builder()//
				.dealAmount(BigDecimal.valueOf(100))//
				.fromCurrencyCode("USD")//
				.toCurrencyCode("EUR")//
				.id(BigDecimal.ZERO).build();
				

		 Mockito.when(fxDealRepository.findById(fxDeal.getId())).thenReturn(Optional.of(fxDeal));

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/FxDeals/saveFxDeal")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(Body));

		mockMvc.perform(mockRequest).andExpect(MockMvcResultMatchers.status().isBadRequest())//
		.andExpect(MockMvcResultMatchers.jsonPath("$", CoreMatchers.notNullValue()))
		.andExpect(MockMvcResultMatchers.jsonPath("$.Response", CoreMatchers.is("The record already exist ")));
	}

}
