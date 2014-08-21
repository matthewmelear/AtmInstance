package com.atmWebApp.entities;
import java.math.BigDecimal;
import java.util.Date;

public class Transaction {

	private String transactionId; 
	private String accountId;
	private BigDecimal creditAmount;
	private BigDecimal debitAmount;
	private Date REC_CREAT_DT;
	
	public Transaction(){}
	
	public Transaction(String transactionId, String accountId,
			BigDecimal creditAmount, BigDecimal debitAmount, Date REC_CREAT_DT) {
		super();
		this.transactionId = transactionId;
		this.accountId = accountId;
		this.creditAmount = creditAmount;
		this.debitAmount = debitAmount;
		this.REC_CREAT_DT = REC_CREAT_DT;
	}
	
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public BigDecimal getCreditAmount() {
		return creditAmount;
	}
	public void setCreditAmount(BigDecimal creditAmount) {
		this.creditAmount = creditAmount;
	}
	public BigDecimal getDebitAmount() {
		return debitAmount;
	}
	public void setDebitAmount(BigDecimal debitAmount) {
		this.debitAmount = debitAmount;
	}
	public Date getREC_CREAT_DT() {
		return REC_CREAT_DT;
	}
	public void setREC_CREAT_DT(Date rEC_CREAT_DT) {
		REC_CREAT_DT = rEC_CREAT_DT;
	}
}
