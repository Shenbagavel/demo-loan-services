package com.shen.demo.services.loan.model;

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

public class LoanAccModel {

	private  String Id;
	private  String AccNo;
	private  String CustomerNo;
	private  int LoanAmount;
	private  int NoOfTenor;
	private  String TenorUnit;
	private  BigDecimal InterestRate;
	private  BigDecimal InterestAmount;
	private  BigDecimal TotalPayableAmount;

  public LoanAccModel() {

  }
  
  public LoanAccModel(String Id, String AccNo, String customerNo, int LoanAmount,int NoOfTenor,String TenorUnit,BigDecimal InterestRate) {
    this.Id = Id;
    this.AccNo = AccNo;
    this.CustomerNo = CustomerNo;
    this.LoanAmount = LoanAmount;
	this.NoOfTenor = NoOfTenor;
	this.TenorUnit = TenorUnit;
	this.InterestRate = InterestRate;
  }

  public LoanAccModel(String Id, String AccNo, String CustomerNo) {
    this.Id = Id;
    this.AccNo = AccNo;
    this.CustomerNo = CustomerNo;
    
  }
  



	public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }
	
	public String getAccNo() {
        return AccNo;
    }

    public void setAccNo(String AccNo) {
        this.AccNo = AccNo;
    }
	
	public String getCustomerNo() {
        return CustomerNo;
    }

    public void setCustomerNo(String CustomerNo) {
        this.CustomerNo = CustomerNo;

	}
	public int getLoanAmount() {
        return LoanAmount;
    }

    public void setLoanAmount(int LoanAmount) {
        this.LoanAmount = LoanAmount;
    }
	
	public int getNoOfTenor() {
        return NoOfTenor;
    }

    public void setNoOfTenor(int NoOfTenor) {
        this.NoOfTenor = NoOfTenor;
	}
	
	public String getTenorUnit() {
        return TenorUnit;
    }

    public void setTenorUnit(String TenorUnit) {
        this.TenorUnit = TenorUnit;
    }
	
	public BigDecimal getInterestRate() {
        return InterestRate;
    }


	public void setInterestRate(BigDecimal InterestRate) {
        this.InterestRate = InterestRate;
    }
	
	public BigDecimal getInterestAmount() {
        return InterestAmount;
    }


	public void setInterestAmount(BigDecimal InterestAmount) {
		this.InterestAmount = InterestAmount;
    }
	
	public BigDecimal getTotalPayableAmount() {
        return TotalPayableAmount;
	}

    public void setTotalPayableAmount(BigDecimal TotalPayableAmount) {
		this.TotalPayableAmount = TotalPayableAmount;
    }
}




