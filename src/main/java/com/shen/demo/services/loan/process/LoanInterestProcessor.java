package com.shen.demo.services.loan.process;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.math.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.http.HttpStatus;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.shen.demo.services.loan.controller.LoanCalcController;
import com.shen.demo.services.loan.model.LoanAccModel;

@Component
public class LoanInterestProcessor{

	private static final Logger logger = LogManager.getLogger(LoanInterestProcessor.class);
	
	private static final String TOPIC = "demo-loan-topic";
										 

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
	
  public LoanAccModel calculateAmount(LoanAccModel loanAccDto){
	  LoanAccModel loanAccModelResp = new LoanAccModel();
		
		loanAccModelResp = loanAccDto;
		
		
	try{
		logger.debug("inside calc amount for customer:"+loanAccDto.getCustomerNo()+"@AccNo:"+loanAccDto.getAccNo());
		BigDecimal intAmount = BigDecimal.ONE;
		BigDecimal intPercentage = BigDecimal.ONE;
//intPercentage = loanAccDto.getInterestRate() / 100;
		BigDecimal divDays = new BigDecimal("100");
		logger.debug("Divdays:"+divDays +"interest Rate:"+loanAccDto.getInterestRate());
		intPercentage = loanAccDto.getInterestRate().divide(divDays, 3, RoundingMode.CEILING);
		
		BigDecimal dayPercentage = BigDecimal.ONE;
		//dayPercentage = loanAccDto.getTenor()/365;
		BigDecimal daysYear = new BigDecimal("365");
		
		dayPercentage = BigDecimal.valueOf(loanAccDto.getNoOfTenor()).divide(daysYear, 3, RoundingMode.CEILING);
		
		//intAmount = loanAccDto.getLoanAmount() * intPercentage * dayPercentage;
		
		//intAmount = new BigDecimal(loanAccDto.getLoanAmount()).multiply(intPercentage);
		intAmount = BigDecimal.valueOf(loanAccDto.getLoanAmount()).multiply(intPercentage);
		
		intAmount = intAmount.multiply(dayPercentage);
		
		BigDecimal totalpayable = BigDecimal.ONE;
		//totalpayable = loanAccDto.getLoanAmount() + intAmount;
		
		totalpayable = new BigDecimal(loanAccDto.getLoanAmount()).add(intAmount);
		
		loanAccModelResp.setInterestAmount(intAmount);
		loanAccModelResp.setTotalPayableAmount(totalpayable);
		loanAccModelResp.setId(UUID.randomUUID().toString());
		
		logger.debug("after calc amount");
	}catch(Exception ex){
		logger.debug("failed in calc amount "+ex.getMessage());
		ex.printStackTrace();

	}
	return loanAccModelResp;
}

	public void publishMessageToTopic(String message,String Key) {
        logger.info(String.format("#### -> Producing message -> %s", message));
		try{
        //this.kafkaTemplate.send(TOPIC,Key, message);
		kafkaTemplate.send(TOPIC, message);
		}catch(Exception ex){
			logger.debug("failed in publish message to Topic "+ex.getMessage());
			ex.printStackTrace();
			StringWriter errors = new StringWriter();
			ex.printStackTrace(new PrintWriter(errors));
			logger.info("Stack Trace is:"+ errors.toString());
		}
    }

}