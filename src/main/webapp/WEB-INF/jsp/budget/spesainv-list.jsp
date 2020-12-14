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
	<title>Spesa Investimento</title>
	<link href="/css/scrolltable.css" rel="stylesheet" type="text/css">
	<link href="/webjars/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" type="text/css">
	<link href="/webjars/datatables/1.10.19/css/dataTables.bootstrap4.min.css" rel="stylesheet" type="text/css">
</head>
<body>
	<spring:url value="/menu/sottomenu-budget" var="backUrl"/>
	&nbsp;<a class="btn btn-secondary" href="${backUrl}" role="button">Indietro</a>
	<sec:authorize access="isAuthenticated()">
	    <spring:url value="/logout" var="logoutUrl"/>
		<a class="btn btn-danger" href="${logoutUrl}" role="button" style="float: right;">Logout</a>&nbsp;&nbsp;
	</sec:authorize>
	
	<div class="container">
	
		<h2 align="center">Spesa Investimento</h2>
		
		<br>
		<br>
		<c:choose>
			<c:when test="${sottocategoriaList.size()>0}">
				<table align="center">
					<tr>
						<td>Sottocategoria :</td>
						<td>
							<select class="form-control col-xs-5" id="chiaveSottocategoria" name="chiaveSottocategoria" onchange="setSpesaInvestimentoList()"> 
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
				
				<c:choose>
					<c:when test="${spesaInvestimentoList.size()>0}">
						<table class="table table-striped table-bordered mydatatable" style="width:100%">
							<thead>
								<tr>
									<th></th>
									<th>Spesa Investimento</th>
								</tr>
							</thead>
							
							<tbody>
								<c:forEach items="${spesaInvestimentoList}" var="currentSpesaInvestimento">
								<tr>
									<td><input type="radio" name="id" value="${currentSpesaInvestimento.chiaveSpesaInvestimento}"> </td>
									<td>${currentSpesaInvestimento.spesaInvestimento}</td>
								</tr>
								</c:forEach>
								
							</tbody>
							
							
							
							
							
						</table>
						<br>
						<br>
						<spring:url value="/budget/spesainv/add" var="addUrl"/>
						<a class="btn btn-primary" role="button" href="${addUrl}">Nuovo</a>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="button" value="Modifica" onclick="setLink('/budget/spesainv/update/');" class="btn btn-warning"/>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="button" value="Elimina" onclick="setLink('/budget/spesainv/delete/');" class="btn btn-danger"/>
					</c:when>
					<c:when test="${spesaInvestimentoList.size()==0 && chiaveSottocategoria!=0}">
						<br>
						<br>
						<div align="center">
							La Sottocategoria selezionata non ha Spese Investimento
							<br>
							<br>
							<spring:url value="/budget/spesainv/add" var="addUrl"/>
							<a class="btn btn-primary" role="button" href="${addUrl}">Nuovo</a>
						</div>
					</c:when>
				</c:choose>
				
				
				
				
				
				
				
				
			</c:when>
			<c:otherwise>
				Non ci sono Sottocategorie
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
function setSpesaInvestimentoList(){
	var chiaveSottocategoria = document.getElementById("chiaveSottocategoria").value;
	window.location.href = "/budget/spesainv/list/"+chiaveSottocategoria;
}
</script>


</body>
</html>