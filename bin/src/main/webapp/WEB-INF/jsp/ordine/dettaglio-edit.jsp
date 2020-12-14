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
	<title>Dettaglio Ordine Acquisto Edit</title>
	<link href="/webjars/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" type="text/css">
	<script  type="text/javascript" src="/webjars/bootstrap/4.0.0/js/bootstrap.min.js"></script>
	<script  type="text/javascript" src="/webjars/jquery/3.0.0/jquery.min.js"></script>
</head>
<body>
<spring:url value="/ordine/load" var="backUrl"/>
&nbsp;<a class="btn btn-secondary" href="${backUrl}" role="button">Indietro</a>
<sec:authorize access="isAuthenticated()">
    <spring:url value="/logout" var="logoutUrl"/>
	<a class="btn btn-danger" href="${logoutUrl}" role="button" style="float: right;">Logout</a>&nbsp;&nbsp;
</sec:authorize>
	<div class="container">
		<spring:url value="/ordine/dettaglio/save" var="saveURL" />
		
		<c:choose>
			<c:when test="${index!=null}">
				<h2>Modifica Dettaglio Ordine d'Acquisto</h2>
			</c:when>
			<c:otherwise>
				<h2>Nuovo Dettaglio Ordine d'Acquisto</h2>
			</c:otherwise>
		</c:choose>
		
		<form:form modelAttribute="dettaglioForm" method="post" action="${saveURL}" cssClass="form">
			<form:hidden path="chiaveOrdineAcquistoDettaglio" />
			<div class="form-group">
				<label>Descrizione</label>
				<form:input path="descrizione" cssClass="form-control" />
			</div>
			<div class="form-group">
				<label>Spesa Investimento</label>
				<form:select path="spesaInvestimento" cssClass="form-control" onfocus='this.size=5;' onblur='this.size=1;' onchange='this.size=1; this.blur();'>
				<form:option value="">---Scegli una Spesa d'Investimento ---</form:option>
					<c:forEach items="${spesaInvestimentoList}" var="currentSpesa">
						<c:choose>
							<c:when test="${currentSpesa.chiaveSpesaInvestimento == chiaveSpesa}">
								<form:option value="${currentSpesa}" selected="selected">${currentSpesa.spesaInvestimento} - ${currentSpesa.sottocategoria.sottocategoria}</form:option>
							</c:when>
							<c:otherwise>
								<form:option value="${currentSpesa}" >${currentSpesa.spesaInvestimento} - ${currentSpesa.sottocategoria.sottocategoria}</form:option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</form:select>
			</div>
			<div class="form-group">
				<label>Progetto</label>
				<form:select path="progetto" cssClass="form-control" onfocus='this.size=5;' onblur='this.size=1;' onchange='this.size=1; this.blur();'>
				<form:option value="">---Scegli un Progetto---</form:option>
					<c:forEach items="${progettoList}" var="currentProgetto">
						<c:choose>
							<c:when test="${currentProgetto.chiaveProgetto == chiaveProgetto}">
								<form:option value="${currentProgetto}" selected="selected">${currentProgetto.progetto}</form:option>
							</c:when>
							<c:otherwise>
								<form:option value="${currentProgetto}">${currentProgetto.progetto}</form:option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</form:select>
			</div>
			<div class="form-group">
				<label>Quantità</label>
				<form:input path="quantita" cssClass="form-control" />
			</div>
			<div class="form-group">
				<label>Importo Unitario (&euro;)</label>
				<fmt:formatNumber value="${dettaglioForm.importoUnitario!=null?dettaglioForm.importoUnitario:0}" type="number" minFractionDigits="2" var="importoUnitarioFmt"/>
				<form:input path="importoUnitario" id="importoUnitario" cssClass="form-control" value="${importoUnitarioFmt}"/>
			</div>
			<br>
			<br>
			<form:hidden path="riconciliato"/>
			<button type="submit" class="btn btn-success" onclick="setCurrency();">Salva</button>
		</form:form>

	</div>

</body>

<script type="text/javascript">

function setCurrency(){
	var currency = document.getElementById("importoUnitario").value;
	currency = currency.replace(/[^0-9,-]+/g,"");
	currency = currency.replace(/,/g, '.');
	document.getElementById("importoUnitario").value = currency;
}

</script>
</html>