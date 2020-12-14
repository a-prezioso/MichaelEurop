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
	<title>Preventivo Edit</title>
	<link href="/webjars/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" type="text/css">
	<script  type="text/javascript" src="/webjars/bootstrap/4.0.0/js/bootstrap.min.js"></script>
	<script  type="text/javascript" src="/webjars/jquery/3.0.0/jquery.min.js"></script>
	
	<script src="https://cdn.jsdelivr.net/bootstrap.datepicker-fork/1.3.0/js/bootstrap-datepicker.js"></script>
	<script src="https://cdn.jsdelivr.net/bootstrap.datepicker-fork/1.3.0/js/locales/bootstrap-datepicker.it.js"></script>
	<link href="https://cdn.jsdelivr.net/bootstrap.datepicker-fork/1.3.0/css/datepicker3.css" rel="stylesheet"/>
</head>
<body>
<spring:url value="/preventivo/gestione/list/${chiaveFornitore}" var="backUrl"/>
&nbsp;<a class="btn btn-secondary" href="${backUrl}" role="button">Indietro</a>
<sec:authorize access="isAuthenticated()">
	    <spring:url value="/logout" var="logoutUrl"/>
		<a class="btn btn-danger" href="${logoutUrl}" role="button" style="float: right;">Logout</a>&nbsp;&nbsp;
	</sec:authorize>
	<div class="container">
		<spring:url value="/preventivo/save" var="saveURL" />
		
		<c:choose>
			<c:when test="${preventivoForm.chiavePreventivo>0}">
				<h2>Modifica Preventivo</h2>
			</c:when>
			<c:otherwise>
				<h2>Nuovo Preventivo</h2>
			</c:otherwise>
		</c:choose>
		
		<div class="form-group">
				<label>Fornitore</label>
				<input value="${preventivoForm.fornitore.ragioneSociale}" readonly="readonly" class="form-control"/>
		</div>
		
		<form:form modelAttribute="preventivoForm" method="post" action="${saveURL}" cssClass="form">
			<form:hidden path="chiavePreventivo" />
			<form:hidden path="fornitore" />
			<div class="form-group">
				<label>Codice</label>
				<form:input path="codice" cssClass="form-control" />
			</div>
			<div class="form-group" >
				<label>Data</label>
				<fmt:formatDate value="${preventivoForm.data}" pattern="dd/MM/yyyy" var="fmtData"/>
        		<input class="form-control" name="data" placeholder="dd/MM/yyyy" type="text" value="${fmtData}" data-provide="datepicker"/>
			</div>
			<div class="form-group">
				<label>Preventivo</label>
				<form:input path="preventivo" cssClass="form-control" />
			</div>
			<div class="form-group">
				<label>Importo (&euro;)</label>
				<fmt:formatNumber value="${preventivoForm.importo!=null?preventivoForm.importo:0}" type="number" minFractionDigits="2" var="importoFmt"/>
				<form:input path="importo" cssClass="form-control" id="importo" value="${importoFmt}"/>
			</div>
			<br>
			<br>
			<button type="submit" class="btn btn-success" onclick="setCurrency();">Salva</button>
		</form:form>

	</div>

</body>
<script>
$(document).ready(function(){
    $.fn.datepicker.defaults.language = 'it';
});

$(document).ready(function(){
    $('.datepicker').datepicker({
    	
    });
});
</script>

<script type="text/javascript">

function setCurrency(){
	var currency_tag = document.getElementById("importo");
	var currency_value = currency_tag.value;
	currency_value = currency_value.replace(/[^0-9,-]+/g,"");
	currency_value = currency_value.replace(/,/g, '.');
	currency_tag.value = currency_value;
}

</script>
</html>