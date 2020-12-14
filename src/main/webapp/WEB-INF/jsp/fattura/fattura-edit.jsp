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
	<title>FatturaPassiva Edit</title>
	
	<link href="/css/scrolltable.css" rel="stylesheet" type="text/css">
	<link href="/webjars/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" type="text/css">
	<link href="/webjars/datatables/1.10.19/css/dataTables.bootstrap4.min.css" rel="stylesheet" type="text/css">
	
	<script  type="text/javascript" src="/webjars/bootstrap/4.0.0/js/bootstrap.min.js"></script>
	<script  type="text/javascript" src="/webjars/jquery/3.0.0/jquery.min.js"></script>
	
	
	<script src="https://cdn.jsdelivr.net/bootstrap.datepicker-fork/1.3.0/js/bootstrap-datepicker.js"></script>
	<script src="https://cdn.jsdelivr.net/bootstrap.datepicker-fork/1.3.0/js/locales/bootstrap-datepicker.it.js"></script>
	<link href="https://cdn.jsdelivr.net/bootstrap.datepicker-fork/1.3.0/css/datepicker3.css" rel="stylesheet"/>
	
</head>
<body>
<spring:url value="${urlFattura}" var="backUrl"/>
&nbsp;<a class="btn btn-secondary" href="${backUrl}" role="button">Indietro</a>
<sec:authorize access="isAuthenticated()">
    <spring:url value="/logout" var="logoutUrl"/>
	<a class="btn btn-danger" href="${logoutUrl}" role="button" style="float: right;">Logout</a>&nbsp;&nbsp;
</sec:authorize>
	<div class="container">
		<spring:url value="/fattura/save" var="saveURL" />
		
		<c:choose>
			<c:when test="${fatturaPassivaForm.chiaveFatturaPassiva>0}">
				<h2>Modifica Fattura Passiva</h2>
			</c:when>
			<c:otherwise>
				<h2>Nuova Fattura Passiva</h2>
			</c:otherwise>
		</c:choose>
		
		<c:choose>
			<c:when test="${fornitoreList.size()>0}">
				<form:form modelAttribute="fatturaPassivaForm" method="post" action="${saveURL}" cssClass="form" id="formId">
					<form:hidden path="chiaveFatturaPassiva" />
					<div class="form-group">
						<label>Descrizione</label>
						<form:input path="descrizione" cssClass="form-control" />
					</div>
					<div class="form-group">
						<label>Numero</label>
						<form:input path="numero" cssClass="form-control" />
					</div>
					<div class="form-group" >
						<label>Data</label>
						<fmt:formatDate value="${fatturaPassivaForm.data}" pattern="dd/MM/yyyy" var="fmtData"/>
		        		<input class="form-control" name="data" placeholder="dd/MM/yyyy" type="text" value="${fmtData}" data-provide="datepicker"/>
					</div>
					<div class="form-group">
						<label>Fornitore</label>
						<form:select path="fornitore" cssClass="form-control">
							<form:option value="">---Scegli un Fornitore---</form:option>
								<c:forEach items="${fornitoreList}" var="currentFornitore">
									<c:choose>
										<c:when test="${currentFornitore.chiaveFornitore == chiaveFornitore}">
											<form:option value="${currentFornitore}" selected="selected">${currentFornitore.ragioneSociale}</form:option>
										</c:when>
										<c:otherwise>
											<form:option value="${currentFornitore}">${currentFornitore.ragioneSociale}</form:option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
						</form:select>
					</div>
					<br>
					<br>
					
					
					
					<h4 align="center">Dettagli</h4>
			
						<input type="submit" value="Nuovo" onclick="setAction('/fattura/dettaglio/add');" class="btn btn-primary"/>
								
						<br>
						<br>
						<div align="center">
						<c:choose>
						
							<c:when test="${dettagli.size()>0}">
							
				<!-- 			<div style="height:100px;overflow:auto;"> -->
								<table class="table table-striped table-bordered mydatatablenosearch" style="width:100%">
									<thead>
										<tr>
											<th></th>
											<th>Descrizione</th>
											<th>Spesa Investimento</th>
											<th>Sottocategoria</th>
											<th>Preventivo</th>
											<th>Imponibile</th>
											<th>Importo Iva</th>
											<th>Importo</th>
										</tr>
									</thead>
									
									<tbody>
										<c:set var="totImponibile" value="${0}"/>
										<c:set var="totIva" value="${0}"/>
										<c:set var="totImporto" value="${0}"/>
										<c:forEach items="${dettagli}" var="currentDettaglio" varStatus="loop">
											<tr>
												<td><input type="radio" name="id" value="${loop.index}"> </td>
												<td>${currentDettaglio.descrizione}</td>
												<td>${currentDettaglio.spesaInvestimento.spesaInvestimento}</td>
												<td>${currentDettaglio.spesaInvestimento.sottocategoria.sottocategoria}</td>
												<td>${currentDettaglio.preventivo.preventivo}</td>
												<td><fmt:formatNumber value="${currentDettaglio.importo - currentDettaglio.importo*currentDettaglio.aliquota.aliquota/100}" type="currency" currencySymbol="&euro;"/></td>
												<td><fmt:formatNumber value="${currentDettaglio.importo*currentDettaglio.aliquota.aliquota/100}" type="currency" currencySymbol="&euro;"/></td>
												<td><fmt:formatNumber value="${currentDettaglio.importo}" type="currency" currencySymbol="&euro;"/></td>
											</tr>
											<c:set var="totImponibile" value="${totImponibile + (currentDettaglio.importo - currentDettaglio.importo*currentDettaglio.aliquota.aliquota/100)}"/>
											<c:set var="totIva" value="${totIva + (currentDettaglio.importo*currentDettaglio.aliquota.aliquota/100)}"/>
											<c:set var="totImporto" value="${totImporto + currentDettaglio.importo}"/>
										</c:forEach>
									</tbody>
									
									<tfoot>
										<tr>
											<th colspan="5" style="text-align: right;">Totale:</th>
											<th><fmt:formatNumber value="${totImponibile}" type="currency" currencySymbol="&euro;"/></th>
											<th><fmt:formatNumber value="${totIva}" type="currency" currencySymbol="&euro;"/></th>
											<th><fmt:formatNumber value="${totImporto}" type="currency" currencySymbol="&euro;"/></th>
										</tr>
									</tfoot>
									
									
									
								</table>
				<!-- 			</div> -->
								
								<form:hidden path="importo" value="${totImporto}"/>
								
								<br>
								<input type="submit" value="Modifica" onclick="return setUrl('/fattura/dettaglio/update/');" class="btn btn-warning"/>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="submit" value="Elimina" onclick="return setUrl('/fattura/dettaglio/delete/');" class="btn btn-danger"/>
								
								<br>
								<br>
								<br>
								<button type="submit" class="btn btn-success">Salva</button>
								<br>
								<br>
								<br>
							</c:when>
							<c:otherwise>
							<br>
							<br>
							Non ci sono Dettagli
							<br>
							<br>
							</c:otherwise>
						</c:choose>
					 	
					 
				
					</div>
					
					
					
				</form:form>
			</c:when>
			<c:otherwise>
				<br>
				<br>
				Non ci sono Fornitori
			</c:otherwise>
		</c:choose>
		

	</div>

</body>

<script  type="text/javascript" src="/webjars/datatables/1.10.19/js/dataTables.bootstrap4.min.js"></script>
<script  type="text/javascript" src="/webjars/datatables/1.10.19/js/jquery.dataTables.min.js"></script>
<script  type="text/javascript" src="/webjars/popper.js/1.14.7/dist/umd/popper.min.js"></script>
<script src="/js/scrolltable.js"></script>

<script type="text/javascript">

function setUrl(url){
	var scelta = document.getElementsByName("id");
	var i = 0;
	while(i<scelta.length && !scelta[i].checked){
		i++;
	}
	if(i<scelta.length){
		setAction(url+scelta[i].value);
		return true;
	} else {
		return false;
	}
};



function setAction(url){
	document.getElementById("formId").action = url;
};



</script>

<script>
$(document).ready(function(){
    $.fn.datepicker.defaults.language = 'it';
});

$(document).ready(function(){
    $('.datepicker').datepicker({
    	
    });
});
</script>
</html>