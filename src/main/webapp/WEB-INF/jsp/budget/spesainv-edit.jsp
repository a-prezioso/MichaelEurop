<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%> 
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Spesa Investimento Edit</title>
	<link href="/webjars/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" type="text/css">
	<script  type="text/javascript" src="/webjars/bootstrap/4.0.0/js/bootstrap.min.js"></script>
	<script  type="text/javascript" src="/webjars/jquery/3.0.0/jquery.min.js"></script>
</head>
<body>
<spring:url value="/budget/spesainv/list/${chiaveSottocategoria}" var="backUrl"/>
&nbsp;<a class="btn btn-secondary" href="${backUrl}" role="button">Indietro</a>
<sec:authorize access="isAuthenticated()">
    <spring:url value="/logout" var="logoutUrl"/>
	<a class="btn btn-danger" href="${logoutUrl}" role="button" style="float: right;">Logout</a>&nbsp;&nbsp;
</sec:authorize>
	<div class="container">
		<spring:url value="/budget/spesainv/save" var="saveURL" />
		
		<c:choose>
			<c:when test="${spesaInvestimentoForm.chiaveSpesaInvestimento>0}">
				<h2>Modifica Spesa Investimento</h2>
			</c:when>
			<c:otherwise>
				<h2>Nuova Spesa Investimento</h2>
			</c:otherwise>
		</c:choose>
		
		<div class="form-group">
				<label>Sottocategoria</label>
				<input value="${spesaInvestimentoForm.sottocategoria.sottocategoria}" readonly="readonly" class="form-control"/>
		</div>
		
		<form:form modelAttribute="spesaInvestimentoForm" method="post" action="${saveURL}" cssClass="form">
			<form:hidden path="chiaveSpesaInvestimento" />
			<form:hidden path="sottocategoria" />
			<div class="form-group">
				<label>Spesa Investimento</label>
				<form:input path="spesaInvestimento" cssClass="form-control" />
			</div>
			<br>
			<br>
			<button type="submit" class="btn btn-success">Salva</button>
		</form:form>

	</div>

</body>
</html>