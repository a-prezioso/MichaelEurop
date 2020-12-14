<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%> 
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %> 
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Area Edit</title>
	<link href="/webjars/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" type="text/css">
	<script  type="text/javascript" src="/webjars/bootstrap/4.0.0/js/bootstrap.min.js"></script>
	<script  type="text/javascript" src="/webjars/jquery/3.0.0/jquery.min.js"></script>
</head>
<body>
<spring:url value="/budget/sottocat-list/${sottocategoriaForm.area.chiaveArea}" var="backUrl"/>
&nbsp;<a class="btn btn-secondary" href="${backUrl}" role="button">Indietro</a>
<sec:authorize access="isAuthenticated()">
	<spring:url value="/logout" var="logoutUrl"/>
	<a class="btn btn-danger" href="${logoutUrl}" role="button" style="float: right;">Logout</a>&nbsp;&nbsp;
</sec:authorize>
	<div class="container">
		<spring:url value="/budget/save" var="saveURL" />
		
		
				<h2>Definizione Budget</h2>
			
		
		<form:form modelAttribute="sottocategoriaForm" method="post" action="${saveURL}" cssClass="form">
			<form:hidden path="chiaveSottocategoria" />
<%-- 			<form:hidden path="sottocateria" /> --%>
<%-- 			<form:hidden path="codice" /> --%>
<%-- 			<form:hidden path="area" /> --%>
<%-- 			<form:hidden path="budgetSpeso" /> --%>
			<div class="form-group col-xs-4">
				<label>Definisci il Budget per la Sottocategoria ${sottocategoriaForm.sottocategoria} dell'Area ${sottocategoriaForm.area.area} (&euro;)</label>
				<fmt:formatNumber value="${sottocategoriaForm.budget!=null?sottocategoriaForm.budget:0}" type="number" minFractionDigits="2" var="budgetFmt"/>
				<form:input path="budget" cssClass="form-control" id="budget" value="${budgetFmt}"/>
			</div>
			<br>
			<br>
			<button type="submit" class="btn btn-success" onclick="setCurrency();">Salva</button>
		</form:form>

	</div>

</body>

<script type="text/javascript">

function setCurrency(){
	var currency = document.getElementById("budget").value;
	currency = currency.replace(/[^0-9,-]+/g,"");
	currency = currency.replace(/,/g, '.');
	document.getElementById("budget").value = currency;
}

</script>

</html>