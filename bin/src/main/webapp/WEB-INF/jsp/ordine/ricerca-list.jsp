<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Ricerca Ordini</title>
	<link href="/css/scrolltable.css" rel="stylesheet" type="text/css">
	<link href="/webjars/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" type="text/css">
	<link href="/webjars/datatables/1.10.19/css/dataTables.bootstrap4.min.css" rel="stylesheet" type="text/css">
</head>
<body>
	<spring:url value="/menu/sottomenu-ordini-acquisto" var="backUrl"/>
	&nbsp;<a class="btn btn-secondary" href="${backUrl}" role="button">Indietro</a>
	<sec:authorize access="isAuthenticated()">
	    <spring:url value="/logout" var="logoutUrl"/>
		<a class="btn btn-danger" href="${logoutUrl}" role="button" style="float: right;">Logout</a>&nbsp;&nbsp;
	</sec:authorize>
	
	<div class="container">
	
		<h2 align="center">Ricerca Ordini</h2>
		
		<br>
		<div align="center"><input type="button" class="btn btn-primary" onclick="setUrl();" value="Cerca"></div>
		
		<br>
		
				<table align="center">
					<tr>
						<td>Fornitore :</td>
						<td>
							<select class="form-control col-xs-5" id="chiaveFornitore" name="chiaveFornitore"> 
								<option value="0">---Scegli un Fornitore---</option>
								<c:forEach items="${fornitoreList}" var="currentFornitore">
									<c:choose>
										<c:when test="${currentFornitore.chiaveFornitore == chiaveFornitore}">
											<option value="${currentFornitore.chiaveFornitore}" selected="selected">${currentFornitore.ragioneSociale}</option>
										</c:when>
										<c:otherwise>
											<option value="${currentFornitore.chiaveFornitore}">${currentFornitore.ragioneSociale}</option>
										</c:otherwise>
									</c:choose>
									
								</c:forEach>
							</select>
						</td>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Progetto :</td>
						<td>
							<select class="form-control col-xs-5" id="chiaveProgetto" name="chiaveProgetto"> 
								<option value="0">---Scegli un Progetto---</option>
								<c:forEach items="${progettoList}" var="currentProgetto">
									<c:choose>
										<c:when test="${currentProgetto.chiaveProgetto == chiaveProgetto}">
											<option value="${currentProgetto.chiaveProgetto}" selected="selected">${currentProgetto.progetto}</option>
										</c:when>
										<c:otherwise>
											<option value="${currentProgetto.chiaveProgetto}">${currentProgetto.progetto}</option>
										</c:otherwise>
									</c:choose>
									
								</c:forEach>
							</select>
						</td>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Sottocategoria :</td>
						<td>
							<select class="form-control col-xs-5" id="chiaveSottocategoria" name="chiaveSottocategoria"> 
								<option value="0">---Scegli una Sottocategoria---</option>
								<c:forEach items="${sottocategoriaList}" var="currentSottocategoria">
									<c:choose>
										<c:when test="${currentSottocategoria.chiaveSottocategoria == chiaveSottocategoria}">
											<option value="${currentSottocategoria.chiaveSottocategoria}" selected="selected">${currentSottocategoria.sottocategoria}</option>
										</c:when>
										<c:otherwise>
											<option value="${currentSottocategoria.chiaveSottocategoria}">${currentSottocategoria.sottocategoria}</option>
										</c:otherwise>
									</c:choose>
									
								</c:forEach>
							</select>
						</td>
					</tr>
				</table>
				<br>
				<br>
				<c:choose>
					<c:when test="${ordineList.size()>0}">
						<table class="table table-striped table-bordered mydatatable" style="width:100%">
							<thead>
								<tr>
									<th></th>
									<th>Ordine d'Acquisto</th>
									<th>Fornitore</th>
									<th>Numero</th>
									<th>Data</th>
									<th>Importo</th>
								</tr>
							</thead>
							
							<tbody>
								<c:forEach items="${ordineList}" var="currentOrdine">
								<tr>
									<c:choose>
										<c:when test="${currentOrdine.chiaveOrdineAcquisto == chiaveOrdine}">
											<td><input type="radio" name="id" value="${currentOrdine.chiaveOrdineAcquisto}" checked="checked" onchange="setDettagliTable(this.value)"> </td>
										</c:when>
										<c:otherwise>
											<td><input type="radio" name="id" value="${currentOrdine.chiaveOrdineAcquisto}" onchange="setDettagliTable(this.value)"> </td>
										</c:otherwise>
									</c:choose>
									<td>${currentOrdine.ordineAcquisto}</td>
									<td>${currentOrdine.fornitore.ragioneSociale}</td>
									<td>${currentOrdine.numero}</td>
									<td><fmt:formatDate value="${currentOrdine.data}" pattern="dd/MM/yyyy"/></td>
									<td><fmt:formatNumber value="${currentOrdine.importo!=null?currentOrdine.importo:0}" type="currency" currencySymbol="&euro;"/></td>
								</tr>
								</c:forEach>
								
							</tbody>
							
							
						</table>
						
						<c:if test="${dettagli.size()>0}">
							<br>
							<br>
							<h3>Dettagli</h3>
							<br>
							<br>
							<table class="table table-striped table-bordered mydatatablenosearch" style="width:100%">
								<thead>
									<tr>
										<th>Dettaglio</th>
										<th>Spesa Investimento</th>
										<th>Sottocategoria</th>
										<th>Progetto</th>
										<th>Quantità</th>
										<th>Importo unitario</th>
										<th>Importo Totale</th>
									</tr>
								</thead>
								
								<tbody>
									<c:forEach items="${dettagli}" var="currentDettaglio">
										<c:if test="${
										(chiaveProgetto==0 && chiaveSottocategoria==0) || 
										(chiaveProgetto!=0 && chiaveSottocategoria!=0 && currentDettaglio.progetto.chiaveProgetto==chiaveProgetto && currentDettaglio.spesaInvestimento.sottocategoria.chiaveSottocategoria==chiaveSottocategoria) ||
										(chiaveProgetto!=0 && currentDettaglio.progetto.chiaveProgetto==chiaveProgetto) ||
										(chiaveSottocategoria!=0 && currentDettaglio.spesaInvestimento.sottocategoria.chiaveSottocategoria==chiaveSottocategoria)}">
										
											<tr>
												<td>${currentDettaglio.descrizione}</td>
												<td>${currentDettaglio.spesaInvestimento.spesaInvestimento}</td>
												<td>${currentDettaglio.spesaInvestimento.sottocategoria.sottocategoria}</td>
												<td>${currentDettaglio.progetto.progetto}</td>
												<td>${currentDettaglio.quantita}</td>
												<td><fmt:formatNumber value="${currentDettaglio.importoUnitario}" type="currency" currencySymbol="&euro;"/></td>
												<td><fmt:formatNumber value="${currentDettaglio.quantita * currentDettaglio.importoUnitario}" type="currency" currencySymbol="&euro;"/></td>
											</tr>
										
										</c:if>
									</c:forEach>
								</tbody>
								
								
								
							</table>
							<br>
							<br>
						</c:if>
						
					</c:when>
					<c:when test="${ordineList.size()==0 && chiaveFornitore!=0 && chiaveProgetto!=0 && chiaveSottocategoria!=0}">
						<br>
						<br>
						<div align="center">
							Non ci sono Ordini d'Acquisto
						</div>
						<br>
						<br>
					</c:when>
				</c:choose>
				
				
				
				
				
				
				
				
			
		
	
	</div>
<script  type="text/javascript" src="/webjars/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script  type="text/javascript" src="/webjars/jquery/3.0.0/jquery.min.js"></script>
<script  type="text/javascript" src="/webjars/datatables/1.10.19/js/dataTables.bootstrap4.min.js"></script>
<script  type="text/javascript" src="/webjars/datatables/1.10.19/js/jquery.dataTables.min.js"></script>
<script  type="text/javascript" src="/webjars/popper.js/1.14.7/dist/umd/popper.min.js"></script>
<script  type="text/javascript" src="/js/setLink.js"></script>
<script src="/js/scrolltable.js"></script>
<script type="text/javascript">
function setUrl(){
	var chiaveFornitore = document.getElementById("chiaveFornitore").value;
	var chiaveProgetto = document.getElementById("chiaveProgetto").value;
	var chiaveSottocategoria = document.getElementById("chiaveSottocategoria").value;
	window.location.href = "/ordine/ricerca/list?idF="+chiaveFornitore+"&idP="+chiaveProgetto+"&idS="+chiaveSottocategoria;
}

function setDettagliTable(chiaveOrdine){
	window.location.href = "/ordine/ricerca/list/"+chiaveOrdine;
}
</script>


</body>
</html>