package com.progressSoft.demo.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.progressSoft.demo.controller.handler.DateDeserializer;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "FXDeals")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ApiModel(description = "Details about the contact")
public class FxDeal {
	@org.springframework.data.annotation.Id
	@NotNull(message = "The Id is required.")
	@ApiModelProperty(notes = "the uniqe id of the contact",required = true)
	private BigDecimal id;
	@NotBlank(message = "The from Currency Code is required.")
	@ApiModelProperty(notes = "From Currency ISO Code \"Ordering Currency\"",required = true)
	private String fromCurrencyCode;
	@NotBlank(message = "The to Currency Code is required.")
	@ApiModelProperty(notes = "To Currency ISO Code",required = true)
	private String toCurrencyCode;
	@Transient
	@NotNull(message = "The deal Amount is required.")
	@Positive(message = "The deal Amount must be greater than 0")
	@ApiModelProperty(notes = "Deal Amount in ordering currency",required = true)
	private BigDecimal dealAmount;
	private String Amount;
	@NotNull(message = "The deal Time stamp is required.")
	@FutureOrPresent(message = "The deal Time must be today or in the future.")
	@JsonDeserialize(using = DateDeserializer.class)
	@ApiModelProperty(notes = "Deal timestamp (dd-MM-yyyy hh:mm:ss) ",required = true)
	private Date dealTime;
	
	@ApiModelProperty(hidden = true)
	public String getAmount() {
		return Amount;
	}
	public void setAmount(String amount) {
		Amount = amount;
	}
	
	
}


