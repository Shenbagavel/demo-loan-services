package com.shen.demo.services.loan.repository;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.shen.demo.services.loan.model.LoanAccModel;
import com.shen.demo.services.loan.process.LoanInterestProcessor;

@Repository
public class LoanAccRepositoryImpl implements LoanAccRepository {

	private static final Logger logger = LogManager.getLogger(LoanAccRepositoryImpl.class);
  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Override
  public int save(LoanAccModel loanAccModel) {
	  try {
		  logger.debug("inside save");
    return jdbcTemplate.update("INSERT INTO loan_Acc (acc_no, customer_no, loan_Amount, no_of_Tenor,interest_Rate,interest_amount,total_payable_Amount,id) VALUES(?,?,?,?,?,?,?)",
        new Object[] { loanAccModel.getAccNo(), loanAccModel.getCustomerNo(), loanAccModel.getLoanAmount(),loanAccModel.getNoOfTenor(),
		loanAccModel.getInterestRate(),loanAccModel.getInterestAmount(),loanAccModel.getTotalPayableAmount(),loanAccModel.getId()
		
		});
    //System.out.println("afterer insert in save");
  }catch(Exception ex) {
	  logger.debug("Failed during save to db:"+ex.getMessage());
	  ex.printStackTrace();
    return 1;
  }
	
  }

  

  @Override
  public LoanAccModel findById(Long id) {
    try {
      LoanAccModel loanAccModel = jdbcTemplate.queryForObject("SELECT * FROM loan_Acc WHERE id=?",
          BeanPropertyRowMapper.newInstance(LoanAccModel.class), id);

      return loanAccModel;
    } catch (IncorrectResultSizeDataAccessException e) {
      return null;
    }
  }

  
  @Override
  public List<LoanAccModel> findAll() {
    return jdbcTemplate.query("SELECT * from loan_Acc", BeanPropertyRowMapper.newInstance(LoanAccModel.class));
  }
 
}
