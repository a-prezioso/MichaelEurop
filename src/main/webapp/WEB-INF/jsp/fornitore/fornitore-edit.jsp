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
	<title>Fornitore Edit</title>
	<link href="/webjars/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" type="text/css">
	<script  type="text/javascript" src="/webjars/bootstrap/4.0.0/js/bootstrap.min.js"></script>
	<script  type="text/javascript" src="/webjars/jquery/3.0.0/jquery.min.js"></script>
</head>
<body>
<spring:url value="/fornitore/lista" var="backUrl"/>
&nbsp;<a class="btn btn-secondary" href="${backUrl}" role="button">Indietro</a>
<sec:authorize access="isAuthenticated()">
    <spring:url value="/logout" var="logoutUrl"/>
	<a class="btn btn-danger" href="${logoutUrl}" role="button" style="float: right;">Logout</a>&nbsp;&nbsp;
</sec:authorize>
	<div class="container">
		<spring:url value="/fornitore/save" var="saveURL" />
		
		<c:choose>
			<c:when test="${fornitoreForm.chiaveFornitore>0}">
				<h2>Modifica Fornitore</h2>
			</c:when>
			<c:otherwise>
				<h2>Nuovo Fornitore</h2>
			</c:otherwise>
		</c:choose>
		
		<form:form modelAttribute="fornitoreForm" method="post" action="${saveURL}" cssClass="form">
			<form:hidden path="chiaveFornitore" />
			
			<br>
			<div class="row">
				<div class="col">
					<label>Ragione Sociale</label>
					<form:input path="ragioneSociale" cssClass="form-control" />
				</div>
				<div class="col">
					<label>Indirizzo</label>
					<form:input path="indirizzo" cssClass="form-control" />
				</div>
			</div>
			<br>
			<div class="row">
				<div class="col">
					<label>Partita IVA</label>
					<form:input path="partitaIVA" cssClass="form-control" />
				</div>
				<div class="col">
					<label>Città</label>
					<form:input path="citta" cssClass="form-control" />
				</div>
			</div>
			<br>
			<div class="row">
				<div class="col">
					<label>Telefono</label>
					<form:input path="telefono" cssClass="form-control" />
				</div>
				<div class="col">
					<label>Provincia</label>
					<form:input path="provincia" cssClass="form-control" />
				</div>
			</div>
			<br>
			<div class="row">
				<div class="col">
					<label>Referente</label>
					<form:input path="referente" cssClass="form-control" />
				</div>
				<div class="col">
					<label>Cap</label>
					<form:input path="cap" cssClass="form-control" />
				</div>
			</div>
			
			<br>
			<br>
			
			<button type="submit" class="btn btn-success">Salva</button>
		</form:form>

	</div>

</body>
</html>