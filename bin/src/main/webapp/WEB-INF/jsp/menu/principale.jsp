<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Menu Principale</title>
	<link href="/webjars/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" type="text/css">
	<script  type="text/javascript" src="/webjars/bootstrap/4.0.0/js/bootstrap.min.js"></script>
	<script  type="text/javascript" src="/webjars/jquery/3.0.0/jquery.min.js"></script>
</head>
<body>
<sec:authorize access="isAnonymous()">
    <spring:url value="/login" var="loginUrl"/>
	<a class="btn btn-primary" href="${loginUrl}" role="button" style="float: right;">Login</a>&nbsp;&nbsp;
</sec:authorize>
<sec:authorize access="isAuthenticated()">
    <spring:url value="/logout" var="logoutUrl"/>
	<a class="btn btn-danger" href="${logoutUrl}" role="button" style="float: right;">Logout</a>&nbsp;&nbsp;
</sec:authorize>
<div align="center">
	<h1>EuropeCar</h1>
	<br>
	<br>
	<table>
		<tr>
			<td><input type="radio" name="scelta"  value="sottomenu-archivi" checked="checked"> Archivio</td>
		</tr>
		<sec:authorize access="hasRole('ROLE_ADMIN')">
		    <tr>
				<td><input type="radio" name="scelta"  value="sottomenu-budget"> Budget</td>
			</tr>
		</sec:authorize>
		
		<tr>
			<td><input type="radio" name="scelta"  value="sottomenu-preventivi"> Preventivo</td>
		</tr>
		<tr>
			<td><input type="radio" name="scelta"  value="sottomenu-ordini-acquisto"> Ordine d'Acquisto</td>
		</tr>
		<tr>
			<td><input type="radio" name="scelta"  value="sottomenu-fatture-passive"> Fatture passive</td>
		</tr>
	</table>
	<br>
	<br>
	<input type="button" value="Avanti" onclick="sceltaMenu()" class="btn btn-primary">
	
<script type="text/javascript">

function sceltaMenu(){
	var scelta = document.getElementsByName("scelta");
	var i = 0;
	while(i<scelta.length && !scelta[i].checked){
		i++;
	}
	if(i<scelta.length){
		window.location.href = scelta[i].value;
		
	}
	
}
</script> 
</div>
</body>
</html>