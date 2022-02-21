package com.progressSoft.demo.service;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.progressSoft.demo.Exception.ReqBodyException;
import com.progressSoft.demo.entity.FxDeal;
import com.progressSoft.demo.model.mongo.FxDealRepository;

@Service
public class FxDealsService {

	@Autowired
	FxDealRepository fxDealRepository;

	final static Logger log = LogManager.getLogger(FxDealsService.class);
	
	public void saveFxDeals(FxDeal entity) throws ReqBodyException {
		log.info("Check the record if it exists or not");
		checkFxDeals(entity.getId());
		log.info("format the amount in "+entity.getFromCurrencyCode()+"currency code");
		String formatAmount = amountDealsFormt(entity.getDealAmount(),entity.getFromCurrencyCode());
		entity.setAmount(formatAmount);
		fxDealRepository.save(entity);
		log.info("Save the record to the database");
	}

	public void checkFxDeals(BigDecimal id) throws ReqBodyException {
		Optional<FxDeal> fxDeal = fxDealRepository.findById(id);
		if (fxDeal.isPresent())
			log.info("The record already exist ");
			throw new ReqBodyException("The record already exist ");
	}
	
	public String amountDealsFormt(BigDecimal amount,String curCode) throws ReqBodyException {
		log.info("getting the format for "+curCode+"currency code");
     	NumberFormat defaultFormat = NumberFormat.getCurrencyInstance(getLocale(curCode));
		return defaultFormat.format(amount);

	}
	
	private static Locale getLocale(String strCode) {
	     
	    for (Locale locale : NumberFormat.getAvailableLocales()) {
	        String code = NumberFormat.getCurrencyInstance(locale).getCurrency().getCurrencyCode();
	        if (strCode.equals(code)) {
	            return locale;
	        }
	    }  
	    return null;
	}
}
