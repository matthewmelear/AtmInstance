<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
Your account balance is   ${balance}.

<form:form commandName="transaction" action="/AtmWebApp/transact/">
<table>
<tr>
<td>Your Account Number:</td>
<td>
<form:select path="accountId">
   <form:option value= "${accountId}" />
</form:select></td>
<td>Deposit or Withdrawal?</td>
<td><form:select path="transactionType">
   <form:option value="deposit" />
   <form:option value="withdrawal" />
</form:select></td>
</tr>
<tr>
<td>How Much?</td>
<td><form:input path="dollarAmount" /></td>
</tr>
<tr>
<td colspan="2">
<input type="submit" value="Submit" />
</td>
</tr>
</table>
</form:form>
</body>
</html>