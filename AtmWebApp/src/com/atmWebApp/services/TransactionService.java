package com.atmWebApp.services;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Component;

import com.atmWebApp.daos.TransactionDao;
import com.atmWebApp.entities.Transaction;

@Component
public class TransactionService {

	@Autowired
	TransactionDao transactionDao;
	
	public Transaction completeTransaction(String creditAmount, String debitAmount, String accountId){
		Transaction completedTransaction = null;
		try{
			BigDecimal creditAmountBD = new BigDecimal(creditAmount);
			BigDecimal debitAmountBD = new BigDecimal(debitAmount);
			completedTransaction = transactionDao.completeTransaction(creditAmountBD, debitAmountBD, accountId);
		} catch (NumberFormatException e){
			
		}
		return completedTransaction;
	}

	public boolean isValidDollarAmount(String dollarAmount) {
		return dollarAmount.matches("/^\\$?[0-9]+(\\.[0-9][0-9])?$/");
	}

	public Transaction initiateTransaction(String transactionType, String dollarAmount, String accountId) {
		if (transactionType.equals("Deposit")){
			return completeTransaction(dollarAmount, "", accountId);
		}
			return completeTransaction("", dollarAmount, accountId);

	}
}
