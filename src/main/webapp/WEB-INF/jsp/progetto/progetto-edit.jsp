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
	<title>Progetto Edit</title>
	<link href="/webjars/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" type="text/css">
	<script  type="text/javascript" src="/webjars/bootstrap/4.0.0/js/bootstrap.min.js"></script>
	<script  type="text/javascript" src="/webjars/jquery/3.0.0/jquery.min.js"></script>
</head>
<body>
<spring:url value="/progetto/lista" var="backUrl"/>
&nbsp;<a class="btn btn-secondary" href="${backUrl}" role="button">Indietro</a>
<sec:authorize access="isAuthenticated()">
    <spring:url value="/logout" var="logoutUrl"/>
	<a class="btn btn-danger" href="${logoutUrl}" role="button" style="float: right;">Logout</a>&nbsp;&nbsp;
</sec:authorize>
	<div class="container">
		<spring:url value="/progetto/save" var="saveURL" />
		
		<c:choose>
			<c:when test="${progettoForm.chiaveProgetto>0}">
				<h2>Modifica Progetto</h2>
			</c:when>
			<c:otherwise>
				<h2>Nuovo Progetto</h2>
			</c:otherwise>
		</c:choose>
		
		<form:form modelAttribute="progettoForm" method="post" action="${saveURL}" cssClass="form">
			<form:hidden path="chiaveProgetto" />
			<div class="form-group">
				<label>Progetto</label>
				<form:input path="progetto" cssClass="form-control" />
			</div>
			<div class="form-group">
				<label>Codice</label>
				<form:input path="codice" cssClass="form-control" />
			</div>
			<br>
			<br>
			<button type="submit" class="btn btn-success">Salva</button>
		</form:form>

	</div>

</body>
</html>