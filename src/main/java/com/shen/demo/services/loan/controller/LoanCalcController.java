package com.shen.demo.services.loan.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.shen.demo.services.loan.model.LoanAccModel;
import com.shen.demo.services.loan.repository.LoanAccRepository;
import com.shen.demo.services.loan.process.LoanInterestProcessor;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class LoanCalcController {
	
	private static final Logger logger = LogManager.getLogger(LoanCalcController.class);

  @Autowired
  LoanAccRepository loanAccRepository;

  @Autowired
  LoanInterestProcessor loanInterestProcessor;
  
  @GetMapping("/getAllAcc")
  public ResponseEntity<List<LoanAccModel>> getAllRecords(@RequestParam(required = false) String title) {
    try {
      List<LoanAccModel> loanAccRec = new ArrayList<LoanAccModel>();

      
        loanAccRepository.findAll().forEach(loanAccRec::add);
      
      if (loanAccRec.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(loanAccRec, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/loancalc/{id}")
  public ResponseEntity<LoanAccModel> getRecordById(@PathVariable("id") long id) {
	  
    LoanAccModel loanAccModel = loanAccRepository.findById(id);

    if (loanAccModel != null) {
      return new ResponseEntity<>(loanAccModel, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/calculateInterest")
  public ResponseEntity<LoanAccModel> calculateInterest(@RequestBody LoanAccModel loanAccModel) {
    try {
    	logger.debug("In calculateinterest");
		loanAccModel = loanInterestProcessor.calculateAmount(loanAccModel);
		System.out.println("after calling calc amount");
      //loanAccRepository.save(new LoanAccModel(loanAccModel.getCustomerNo(), loanAccModel.getLoanAmount(),loanAccModel.getNoOfTenor(),loanAccModel().getTenorUnits,loanAccModel().getInterestRate() ));
		logger.debug("total payable calcualted :"+loanAccModel.getTotalPayableAmount().toString());
		loanAccRepository.save(loanAccModel);
		logger.debug("after successfully done save");
	  	// URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/calculateInterest").buildAndExpand(loanAccModel.getId())
	  	  //        .toUri();
     // return new ResponseEntity<>("Loan acc was created successfully.", HttpStatus.CREATED);
	  	return ResponseEntity.ok(loanAccModel);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }


}
