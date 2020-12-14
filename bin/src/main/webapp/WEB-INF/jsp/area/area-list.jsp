<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Archivio Aree</title>
	
	<link href="/css/scrolltable.css" rel="stylesheet" type="text/css">
	<link href="/webjars/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" type="text/css">
	<link href="/webjars/datatables/1.10.19/css/dataTables.bootstrap4.min.css" rel="stylesheet" type="text/css">
	
</head>
<body>
<spring:url value="/menu/sottomenu-archivi" var="backUrl"/>
&nbsp;<a class="btn btn-secondary" href="${backUrl}" role="button">Indietro</a>
<sec:authorize access="isAuthenticated()">
    <spring:url value="/logout" var="logoutUrl"/>
	<a class="btn btn-danger" href="${logoutUrl}" role="button" style="float: right;">Logout</a>&nbsp;&nbsp;
</sec:authorize>
<div class="container">
	
	
		<h2 align="center">Archivio Aree</h2>
	
			
		<spring:url value="/area/add" var="addUrl"/>
		<a class="btn btn-primary" role="button" href="${addUrl}">Nuovo</a>
		<br>
		<br>
		<div align="center">
		<c:choose>
		
			<c:when test="${areaList.size()>0}">
			
<!-- 			<div style="height:100px;overflow:auto;"> -->
				<table class="table table-striped table-bordered mydatatable" style="width:100%">
					<thead>
						<tr>
							<th></th>
							<th>Codice</th>
							<th>Area</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${areaList}" var="currentArea">
							<tr>
								<td><input type="radio" name="id" value="${currentArea.chiaveArea}"> </td>
								<td>${currentArea.codice}</td>
								<td>${currentArea.area}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
<!-- 			</div> -->
				<br>
				<input type="button" value="Modifica" onclick="setLink('/area/update/');" class="btn btn-warning"/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button" value="Elimina" onclick="setLink('/area/delete/');" class="btn btn-danger"/>
				

			</c:when>
			<c:otherwise>
			<br>
			<br>
			Non ci sono Aree
			</c:otherwise>
		</c:choose>
	 	
	 

	</div>

</div>
<script  type="text/javascript" src="/webjars/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script  type="text/javascript" src="/webjars/jquery/3.0.0/jquery.min.js"></script>
<script  type="text/javascript" src="/webjars/datatables/1.10.19/js/dataTables.bootstrap4.min.js"></script>
<script  type="text/javascript" src="/webjars/datatables/1.10.19/js/jquery.dataTables.min.js"></script>
<script  type="text/javascript" src="/webjars/popper.js/1.14.7/dist/umd/popper.min.js"></script>
<script  type="text/javascript" src="/js/setLink.js"></script>
<script src="/js/scrolltable.js"></script>

</body>
</html>