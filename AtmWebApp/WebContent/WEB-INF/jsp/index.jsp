<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
<head>

</head>
<body>

<p>Enter your Account Number and PIN</p>
${message}
<form:form commandName="account" action="/AtmWebApp/login/">
<table>
<tr>
<td>Account Number:</td>
<td><form:input path="accountId" /></td>
</tr>
<tr>
<td>PIN:</td>
<td><form:input path="pin" /></td>
</tr>
<tr>
<td colspan="2">
<input type="submit" value="Submit" />
</td>
</tr>
</table>
</form:form>
    <p>FYI, valid Account Number/PIN combinations are:<p>
    <ul><li>AcctNo: 1234,      PIN: 9999</li>
    <li>AcctNo: 2345,      PIN:8888</li></ul>

</body>
</html>