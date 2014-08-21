package com.atmWebApp.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.atmWebApp.daos.AccountDao;
import com.atmWebApp.daos.TransactionDao;
import com.atmWebApp.entities.Account;

@Component
public class AccountService {

	@Autowired
	AccountDao accountDao;
	
	@Autowired
	TransactionDao transactionDao;
	
	public Account login(String accountId, String pin){
		return accountDao.getAccountByAccountIdAndPin(accountId, pin);
	}
	
	public BigDecimal getAccountBalance(String accountId) {
		List<BigDecimal> allCredits = transactionDao.getAllCreditsByAccountId(accountId);
		List<BigDecimal> allDebits = transactionDao.getAllDebitsByAccountId(accountId);
		BigDecimal accountTotal = BigDecimal.valueOf(0);
		
		for (int i = 0; i < allCredits.size(); i++){
			accountTotal.add(allCredits.get(i));
		}
		for (int i = 0; i < allDebits.size(); i++){
			accountTotal.subtract(allDebits.get(i));
		}
		
		return accountTotal;
	}
}
