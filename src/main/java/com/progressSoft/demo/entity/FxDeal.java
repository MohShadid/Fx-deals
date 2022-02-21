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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "FXDeals")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FxDeal {
	@org.springframework.data.annotation.Id
	@NotNull(message = "The Id is required.")
	private BigDecimal id;
	@NotBlank(message = "The from Currency Code is required.")
	private String fromCurrencyCode;
	@NotBlank(message = "The to Currency Code is required.")
	private String toCurrencyCode;
	@Transient
	@NotNull(message = "The deal Amount is required.")
	@Positive(message = "The deal Amount must be greater than 0")
	private BigDecimal dealAmount;
	private String Amount;
	@NotNull(message = "The deal Time stamp is required.")
	@FutureOrPresent(message = "The deal Time stamp must be today or in the future.")
	@JsonDeserialize(using = DateDeserializer.class)
	private Date dealTimestamp;

}


