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
	<title>Sottocategoria Edit</title>
	<link href="/webjars/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" type="text/css">
	<script  type="text/javascript" src="/webjars/bootstrap/4.0.0/js/bootstrap.min.js"></script>
	<script  type="text/javascript" src="/webjars/jquery/3.0.0/jquery.min.js"></script>
</head>
<body>
<spring:url value="/sottocategoria/lista" var="backUrl"/>
&nbsp;<a class="btn btn-secondary" href="${backUrl}" role="button">Indietro</a>
<sec:authorize access="isAuthenticated()">
    <spring:url value="/logout" var="logoutUrl"/>
	<a class="btn btn-danger" href="${logoutUrl}" role="button" style="float: right;">Logout</a>&nbsp;&nbsp;
</sec:authorize>
	<div class="container">
		<spring:url value="/sottocategoria/save" var="saveURL" />
		
		<c:choose>
			<c:when test="${sottocategoriaForm.chiaveSottocategoria>0}">
				<h2>Modifica Sottocategoria</h2>
			</c:when>
			<c:otherwise>
				<h2>Nuova Sottocategoria</h2>
			</c:otherwise>
		</c:choose>
		
		<form:form modelAttribute="sottocategoriaForm" method="post" action="${saveURL}" cssClass="form">
			<form:hidden path="chiaveSottocategoria" />
			<div class="form-group">
				<label>Sottocategoria</label>
				<form:input path="sottocategoria" cssClass="form-control" />
			</div>
			<div class="form-group">
				<label>Codice</label>
				<form:input path="codice" cssClass="form-control" />
			</div>
<!-- 			<div class="form-group"> -->
<!-- 				<label>Budget (&euro;)</label> -->
<%-- 				<fmt:formatNumber value="${sottocategoriaForm.budget!=null?sottocategoriaForm.budget:0}" type="number" minFractionDigits="2" var="budgetFmt"/> --%>
<%-- 				<form:input path="budget" cssClass="form-control" value="${budgetFmt}"/> --%>
<!-- 			</div> -->
<!-- 			<div class="form-group"> -->
<!-- 				<label>Budget Speso (&euro;)</label> -->
<%-- 				<fmt:formatNumber value="${sottocategoriaForm.budgetSpeso!=null?sottocategoriaForm.budgetSpeso:0}" type="number" minFractionDigits="2" var="budgetSpesoFmt"/> --%>
<%-- 				<form:input path="budgetSpeso" cssClass="form-control" value="${budgetSpesoFmt}" />  --%>
<!-- 			</div> -->
			<div class="form-group">
				<label>Area</label>
				<form:select path="area" cssClass="form-control" onfocus='this.size=5;' onblur='this.size=1;' onchange='this.size=1; this.blur();'>
				<form:option value="">---Scegli un'Area---</form:option>
					<c:forEach items="${areaList}" var="currentArea">
						<form:option value="${currentArea}" >${currentArea.area}</form:option>
					</c:forEach>
				</form:select>
			</div>
			<br>
			<br>
			<button type="submit" class="btn btn-success">Salva</button>
		</form:form>

	</div>

</body>
</html>