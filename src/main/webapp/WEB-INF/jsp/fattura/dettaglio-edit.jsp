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
	<title>Dettaglio Fattura Passiva Edit</title>
	<link href="/webjars/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" type="text/css">
	<script  type="text/javascript" src="/webjars/bootstrap/4.0.0/js/bootstrap.min.js"></script>
	<script  type="text/javascript" src="/webjars/jquery/3.0.0/jquery.min.js"></script>
</head>
<body>
<spring:url value="/fattura/load" var="backUrl"/>
&nbsp;<a class="btn btn-secondary" href="${backUrl}" role="button">Indietro</a>
<sec:authorize access="isAuthenticated()">
    <spring:url value="/logout" var="logoutUrl"/>
	<a class="btn btn-danger" href="${logoutUrl}" role="button" style="float: right;">Logout</a>&nbsp;&nbsp;
</sec:authorize>
	<div class="container">
		<spring:url value="/fattura/dettaglio/save" var="saveURL" />
		
		<c:choose>
			<c:when test="${index!=null}">
				<h2>Modifica Dettaglio Fattura Passiva</h2>
			</c:when>
			<c:otherwise>
				<h2>Nuovo Dettaglio Fattura Passiva</h2>
			</c:otherwise>
		</c:choose>
		
		<form:form modelAttribute="dettaglioForm" method="post" action="${saveURL}" cssClass="form">
			<form:hidden path="chiaveFatturaPassivaDettaglio" />
			<div class="form-group">
				<label>Descrizione</label>
				<form:input path="descrizione" cssClass="form-control" />
			</div>
			<div class="form-group">
				<label>Spesa Investimento</label>
				<form:select path="spesaInvestimento" cssClass="form-control">
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
				<label>Preventivo</label>
				<form:select path="preventivo" cssClass="form-control">
				<form:option value="">---Scegli un Preventivo---</form:option>
					<c:forEach items="${preventivoList}" var="currentPreventivo">
						<c:choose>
							<c:when test="${currentPreventivo.chiavePreventivo == chiavePreventivo}">
								<form:option value="${currentPreventivo}" selected="selected">${currentPreventivo.preventivo}</form:option>
							</c:when>
							<c:otherwise>
								<form:option value="${currentPreventivo}">${currentPreventivo.preventivo}</form:option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</form:select>
			</div>
			<div class="form-group">
				<label>Importo (&euro;)</label>
				<fmt:formatNumber value="${dettaglioForm.importo!=null?dettaglioForm.importo:0}" type="number" minFractionDigits="2" var="importoFmt"/>
				<form:input path="importo" id="importo" cssClass="form-control" value="${importoFmt}"/>
			</div>
			<div class="form-group">
				<label>Aliquota (%)</label>
				<form:select path="aliquota" cssClass="form-control" >
				<form:option value="">---Scegli un'Aliquota IVA---</form:option>
					<c:forEach items="${aliquotaList}" var="currentAliquota">
						<c:choose>
							<c:when test="${currentAliquota.chiaveAliquotaIVA == chiaveAliquotaIVA}">
								<form:option value="${currentAliquota}" selected="selected">${currentAliquota.aliquota}</form:option>
							</c:when>
							<c:otherwise>
								<form:option value="${currentAliquota}">${currentAliquota.aliquota}</form:option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</form:select>
			</div>
			<br>
			<br>
			<button type="submit" class="btn btn-success" onclick="setCurrency();">Salva</button>
		</form:form>

	</div>

</body>

<script type="text/javascript">

function setCurrency(){
	var currency = document.getElementById("importo").value;
	currency = currency.replace(/[^0-9,-]+/g,"");
	currency = currency.replace(/,/g, '.');
	document.getElementById("importo").value = currency;
}

</script>
</html>