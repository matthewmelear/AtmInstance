package com.atmWebApp.daos;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.atmWebApp.entities.Transaction;

@Component
public class TransactionDao {

	@Autowired
	DataSource dataSource;
	
	@Autowired
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	

	public Transaction completeTransaction(BigDecimal creditAmount, BigDecimal debitAmount, String accountId){
		Date recCreatDt = new Date();
		String insertSql = "INSERT INTO public.\"TRANSACTION\" COLUMNS (ACCOUNT_ID, CREDIT_AMOUNT, DEBIT_AMOUNT, REC_CREAT_DT) " + 
							"VALUES ("+ accountId + " ," + creditAmount + " ," +  debitAmount + " ,"  + recCreatDt+ ")";
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("creditAmount", creditAmount);
		paramSource.addValue("debitAmount", debitAmount);
		paramSource.addValue("REC_CREAT_DT", recCreatDt);
		String selectSql = "SELECT * FROM \"TRANSACTION\" WHERE \"TRANSACTION\".\"ACCOUNT_ID\" = " + accountId + " AND \"TRANSACTION\".\"REC_CREAT_DT\"  = " + recCreatDt;
		try{
			namedParameterJdbcTemplate.update(insertSql, paramSource);
			ResultSet rs = (ResultSet) namedParameterJdbcTemplate.queryForRowSet(selectSql, paramSource);
			Transaction completedTransaction = new Transaction (rs.getString("TRANSACTION_ID"), 
																rs.getString("ACCOUNT_ID"),
																rs.getBigDecimal("CREDIT_AMOUNT"),
																rs.getBigDecimal("DEBIT_AMOUNT"),
																rs.getDate("REC_CREAT_DT"));
			return completedTransaction;
		} catch (Exception e){
			
		}
		return null;
	}

	public List<BigDecimal> getAllCreditsByAccountId(String accountId) {
		List<BigDecimal> allCredits = new ArrayList<BigDecimal>();
		String sql = "SELECT \"TRANSACTION\".\"CREDIT_AMOUNT\" FROM public.\"TRANSACTION\" WHERE \"TRANSACTION\".\"ACCOUNT_ID\" = " + accountId;
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("accountId", accountId);
		
		try{
			SqlRowSet rs = namedParameterJdbcTemplate.queryForRowSet(sql, paramSource);
			while (rs.next()){
				BigDecimal currentCredit = rs.getBigDecimal("CREDIT_AMOUNT");
				allCredits.add(currentCredit);
			}
		} catch(NumberFormatException nfe){
			
		} catch(Exception e){
			
		}
		return allCredits;
	}

	public List<BigDecimal> getAllDebitsByAccountId(String accountId) {
		List<BigDecimal> allDebits = new ArrayList<BigDecimal>();
		String sql = "SELECT \"TRANSACTION\".\"DEBIT_AMOUNT\" FROM public.\"TRANSACTION\" WHERE \"TRANSACTION\".\"ACCOUNT_ID\" = " + accountId;
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("accountId", accountId);
		
		try{
			SqlRowSet rs = namedParameterJdbcTemplate.queryForRowSet(sql, paramSource);
			while (rs.next()) {
				BigDecimal currentCredit = rs.getBigDecimal("DEBIT_AMOUNT");
				allDebits.add(currentCredit);
			}
			
		} catch(NumberFormatException nfe){
			
		} catch(Exception e){
			
		}
		return allDebits;
	}

}
