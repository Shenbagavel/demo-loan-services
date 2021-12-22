package com.shen.demo.services.loan.repository;

import java.util.List;

import com.shen.demo.services.loan.model.LoanAccModel;

public interface LoanAccRepository {
  int save(LoanAccModel loanAccModel);

  LoanAccModel findById(Long id);

  List<LoanAccModel> findAll();

  
}
