package com.atmWebApp.daos;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.atmWebApp.entities.Account;

@Component
public class AccountDao {
	
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public Account getAccountByAccountIdAndPin(String accountId, String pin){
		String sql = "SELECT \"ACCOUNT\".\"PIN\", \"ACCOUNT\".\"ACCOUNT_ID\"" + 
				" FROM public.\"ACCOUNT\" WHERE \"ACCOUNT\".\"PIN\" = '" + pin + "' and " + 
				"\"ACCOUNT\".\"ACCOUNT_ID\" = " + accountId ;
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("accountId", accountId);
		namedParameters.addValue("pin", pin);
		Account currentAccount = new Account();
		try{
			SqlRowSet rs = namedParameterJdbcTemplate.queryForRowSet(sql, namedParameters);
			rs.next();
			currentAccount.setAccountId(rs.getString("ACCOUNT_ID"));
			currentAccount.setPin(rs.getString("PIN"));
		} catch(NumberFormatException nfe){
			
		} catch(Exception e){
			System.out.println(e);
		}
		return currentAccount;
	}

}
