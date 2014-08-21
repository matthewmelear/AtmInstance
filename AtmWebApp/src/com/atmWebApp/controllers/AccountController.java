package com.atmWebApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.atmWebApp.entities.Account;
import com.atmWebApp.entities.TransactionHelper;
import com.atmWebApp.services.AccountService;
import com.atmWebApp.services.TransactionService;

@Controller
public class AccountController {
	 
		@Autowired
		AccountService accountService;
		
		@ModelAttribute("account")
		public Account getAccountObject() {
			  return new Account();
			 }
		

		@ModelAttribute("transaction")
		public TransactionHelper getTransactionHelper(){
			return new TransactionHelper();
		}
	
	    @RequestMapping("/")
	    public ModelAndView helloWorld1() {
	        return new ModelAndView("index");
	    }
	    
	    @RequestMapping(value = "/login/", method = RequestMethod.POST)
	    public ModelAndView login(@ModelAttribute("account") Account account, BindingResult result) {
	    	String accountId = account.getAccountId();
	    	String pin = account.getPin();
	    	
	    	if (accountId != null && pin != null){
	    		
		    	Account currentAccount = accountService.login(accountId, pin);
		    	if (currentAccount.getAccountId() != null){
		    		return new ModelAndView("welcome", "balance", accountService.getAccountBalance(currentAccount.getAccountId()))
		    					.addObject("accountId", accountId);
		    	}
	    	}
	        return new ModelAndView("index", "message", "Wrong Account Number/PIN Combination");
	    }
	    

	    
}
