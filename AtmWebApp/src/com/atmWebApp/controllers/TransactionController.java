package com.atmWebApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.atmWebApp.entities.TransactionHelper;
import com.atmWebApp.services.TransactionService;

@Controller
public class TransactionController {

	
	@Autowired
	TransactionService transactionService;
	
	
	@RequestMapping(value = "/transact/")
    public ModelAndView login(@ModelAttribute("transaction") TransactionHelper transactionHelper, BindingResult result) {
		String accountId = transactionHelper.getAccountId();
		String dollarAmount = transactionHelper.getDollarAmount();
		String transactionType = transactionHelper.getTransactionType();
    	if (!transactionService.isValidDollarAmount(dollarAmount)){
    		return new ModelAndView("welcome", "message", "Invalid Dollar Amount");
    	}
    	transactionService.initiateTransaction(transactionType, dollarAmount, accountId);
    	return new ModelAndView(new RedirectView("index"), "message", "Transaction Successful");
    }
	
}
