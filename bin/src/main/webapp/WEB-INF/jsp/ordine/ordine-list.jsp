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
	<title>Gestione Ordini</title>
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
	
		<h2 align="center">Gestione Ordini</h2>
		
		<br>
		<br>
		<c:choose>
			<c:when test="${fornitoreList.size()>0}">
				<table align="center">
					<tr>
						<td>Fornitore :</td>
						<td>
							<select class="form-control col-xs-5" id="chiaveFornitore" name="chiaveFornitore" onchange="setOrdiniList()"> 
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
					</tr>
				</table>
				
				<c:choose>
					<c:when test="${ordineList.size()>0}">
						<table class="table table-striped table-bordered mydatatable" style="width:100%">
							<thead>
								<tr>
									<th></th>
									<th>Ordine d'Acquisto</th>
									<th>Numero</th>
									<th>Data</th>
									<th>Importo</th>
								</tr>
							</thead>
							
							<tbody>
								<c:forEach items="${ordineList}" var="currentOrdine">
								<tr>
									<td><input type="radio" name="id" value="${currentOrdine.chiaveOrdineAcquisto}"> </td>
									<td>${currentOrdine.ordineAcquisto}</td>
									<td>${currentOrdine.numero}</td>
									<td><fmt:formatDate value="${currentOrdine.data}" pattern="dd/MM/yyyy"/></td>
									<td><fmt:formatNumber value="${currentOrdine.importo!=null?currentOrdine.importo:0}" type="currency" currencySymbol="&euro;"/></td>
								</tr>
								</c:forEach>
								
							</tbody>
							
							
							
							
							
						</table>
						<br>
						<br>
						<spring:url value="/ordine/add" var="addUrl"/>
						<a class="btn btn-primary" role="button" href="${addUrl}">Nuovo</a>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="button" value="Modifica" onclick="setLink('/ordine/update/');" class="btn btn-warning"/>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="button" value="Elimina" onclick="setLink('/ordine/delete/');" class="btn btn-danger"/>
					</c:when>
					<c:when test="${ordineList.size()==0 && chiaveFornitore!=0}">
						<br>
						<br>
						<div align="center">
							Il Fornitore selezionato non ha Ordini d'Acquisto
							<br>
							<br>
							<spring:url value="/ordine/add" var="addUrl"/>
							<a class="btn btn-primary" role="button" href="${addUrl}">Nuovo</a>
						</div>
					</c:when>
				</c:choose>
				
				
				
				
				
				
				
				
			</c:when>
			<c:otherwise>
				Non ci sono Fornitori
			</c:otherwise>
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
function setOrdiniList(){
	var chiaveFornitore = document.getElementById("chiaveFornitore").value;
	window.location.href = "/ordine/gestione/list/"+chiaveFornitore;
}
</script>


</body>
</html>