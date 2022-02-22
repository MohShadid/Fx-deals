package com.fxdeals.demo.aspect;

import java.util.Currency;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.fxdeals.demo.Exception.ReqBodyException;
import com.fxdeals.demo.entity.FxDeal;

@Aspect
@Component
public class FxDealsAspect {

	final static Logger log = LogManager.getLogger(FxDealsAspect.class);

	@Before(value = "execution(* com.fxdeals.demo.service.FxDealsService.*(..)) and args(entity)")
	public void beforeAdvice(JoinPoint joinPoint, FxDeal entity) throws ReqBodyException {
		log.info("before saving the fx deal in the mongoDB ");
		checkCurCode(entity.getFromCurrencyCode());
		checkCurCode(entity.getToCurrencyCode());
		
	}

	public void checkCurCode(String Cur) throws ReqBodyException {
		try {
			log.info("check the ISO CurrencyCode is valid or not --> "+Cur);
			Currency.getInstance(Cur);
		} catch (IllegalArgumentException e) {
			log.error("Exceptions happen! --> ",e);
			throw new ReqBodyException("Wrong ISO Currency code -->" + Cur);
		}
	}

}
