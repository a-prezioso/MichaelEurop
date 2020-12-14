<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Sottomenu Budget</title>
	<link href="/webjars/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" type="text/css">
	<script  type="text/javascript" src="/webjars/bootstrap/4.0.0/js/bootstrap.min.js"></script>
	<script  type="text/javascript" src="/webjars/jquery/3.0.0/jquery.min.js"></script>
</head>
<body>
<spring:url value="/menu/principale" var="backUrl"/>
&nbsp;<a class="btn btn-secondary" href="${backUrl}" role="button">Indietro</a>
<sec:authorize access="isAuthenticated()">
    <spring:url value="/logout" var="logoutUrl"/>
	<a class="btn btn-danger" href="${logoutUrl}" role="button" style="float: right;">Logout</a>&nbsp;&nbsp;
</sec:authorize>
<div align="center">
	<h2>Budget</h2>
	<br>
	<br>
	<table>
		<tr>
			<td><input type="radio" name="scelta"  value="sottomenu-budget/budget/definizione" checked="checked"> Definizione</td>
		</tr>
		<tr>
			<td><input type="radio" name="scelta"  value="sottomenu-budget/budget/riconciliazione"> Riconciliazione</td>
		</tr>
		<tr>
			<td><input type="radio" name="scelta"  value="sottomenu-budget/budget/avanzamento"> Avanzamento</td>
		</tr>
		<tr>
			<td><input type="radio" name="scelta"  value="sottomenu-budget/budget/spesa-investimento"> Spesa d'Investimento</td>
		</tr>
	</table>
	<br>
	<br>
	<input type="button" value="Avanti" onclick="sceltaVoce()" class="btn btn-primary">
	
<script type="text/javascript">

function sceltaVoce(){
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