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
	<title>Avanzamento Budget</title>
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
	
		<h2 align="center">Avanzamento Budget</h2>
		
		<br>
		<br>
		<c:choose>
			<c:when test="${areaList.size()>0}">
				<table align="center">
					<tr>
						<td>Area :</td>
						<td>
							<select class="form-control col-xs-5" id="chiaveArea" name="chiaveArea" onchange="setSottocategoriaList()" > 
								<option value="0">---Scegli un'Area---</option>
								<c:forEach items="${areaList}" var="currentArea">
									<c:choose>
										<c:when test="${currentArea.chiaveArea == chiaveArea}">
											<option value="${currentArea.chiaveArea}" selected="selected">${currentArea.area}</option>
										</c:when>
										<c:otherwise>
											<option value="${currentArea.chiaveArea}">${currentArea.area}</option>
										</c:otherwise>
									</c:choose>
									
								</c:forEach>
							</select>
						</td>
					</tr>
				</table>
				
				<c:choose>
					<c:when test="${sottocategoriaList.size()>0}">
						<table class="table table-striped table-bordered mydatatablereport" style="width:100%">
							<thead>
								<tr>
									<th>Codice</th>
									<th>Sottocategoria</th>
									<th>Budget</th>
									<th>Budget Speso</th>
									<th>Avanzamento</th>
								</tr>
							</thead>
							
							<tbody>
<%-- 								<c:set var="totBudget" value="${0}"/> --%>
<%-- 								<c:set var="totBudgetSpeso" value="${0}"/> --%>
								<c:forEach items="${sottocategoriaList}" var="currentSottocategoria">
								<tr>
									<td>${currentSottocategoria.codice}</td>
									<td>${currentSottocategoria.sottocategoria}</td>
									<td style="text-align: right;"><fmt:formatNumber value="${currentSottocategoria.budget!=null?currentSottocategoria.budget:0}" type="currency" currencySymbol="&euro;"/></td>
									<td style="text-align: right;"><fmt:formatNumber value="${currentSottocategoria.budgetSpeso!=null?currentSottocategoria.budgetSpeso:0}" type="currency" currencySymbol="&euro;"/></td>
									<td style="text-align: right;"><fmt:formatNumber value="${currentSottocategoria.budget - currentSottocategoria.budgetSpeso}" type="currency" currencySymbol="&euro;"/></td>
<%-- 									<c:set var="totBudget" value="${totBudget + currentSottocategoria.budget}"></c:set> --%>
<%-- 									<c:set var="totBudgetSpeso" value="${totBudgetSpeso + currentSottocategoria.budgetSpeso}"></c:set> --%>
								</tr>
								</c:forEach>
								
							</tbody>
							
							<tfoot>
								<tr>
									<th colspan="2" style="text-align: right;">Totale:</th>
<%-- 									<th style="text-align: right;"><fmt:formatNumber value="${totBudget}" type="currency" currencySymbol="&euro;"/></th> --%>
<%-- 									<th style="text-align: right;"><fmt:formatNumber value="${totBudgetSpeso}" type="currency" currencySymbol="&euro;"/></th> --%>
<%-- 									<th><fmt:formatNumber value="${totBudget - totBudgetSpeso}" type="currency" currencySymbol="&euro;"/></th> --%>
									<th style="text-align: right;"></th>
									<th style="text-align: right;"></th>
									<th style="text-align: right;"></th>
								</tr>
							</tfoot>
							
							
							
						</table>
					</c:when>
					<c:when test="${sottocategoriaList.size()==0 && chiaveArea!=0}">
						<br>
						<br>
						<div align="center">
							L'Area selezionata non ha Sottocategorie
						</div>
					</c:when>
				</c:choose>
				
				
				
				
				
				
				
				
			</c:when>
			<c:otherwise>
				Non ci sono Aree
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
function setSottocategoriaList(){
	var chiaveArea = document.getElementById("chiaveArea").value;
	window.location.href = "/budget/sottocat-list-av/"+chiaveArea;
}
</script>


</body>
</html>