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
	<title>Riconciliazione</title>
	
	<link href="/webjars/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" type="text/css">
	<script  type="text/javascript" src="/webjars/bootstrap/4.0.0/js/bootstrap.min.js"></script>
	<script  type="text/javascript" src="/webjars/jquery/3.0.0/jquery.min.js"></script>
	
	<script src="https://cdn.jsdelivr.net/bootstrap.datepicker-fork/1.3.0/js/bootstrap-datepicker.js"></script>
	<script src="https://cdn.jsdelivr.net/bootstrap.datepicker-fork/1.3.0/js/locales/bootstrap-datepicker.it.js"></script>
	<link href="https://cdn.jsdelivr.net/bootstrap.datepicker-fork/1.3.0/css/datepicker3.css" rel="stylesheet"/>
</head>
<body>
	<spring:url value="/menu/sottomenu-budget" var="backUrl"/>
	&nbsp;<a class="btn btn-secondary" href="${backUrl}" role="button">Indietro</a>
	<sec:authorize access="isAuthenticated()">
	    <spring:url value="/logout" var="logoutUrl"/>
		<a class="btn btn-danger" href="${logoutUrl}" role="button" style="float: right;">Logout</a>&nbsp;&nbsp;
	</sec:authorize>
	<div class="container">
	
		<h2 align="center">Riconciliazione</h2>
		
		<br>
		<br>
		<br>
		

		<label>Data Inizio</label>
    	<input class="form-control" name="dataInizio" id="dataInizio" placeholder="dd/MM/yyyy" type="text" value="" data-provide="datepicker"/>
    	<br>
    	<label>Data Fine</label>
    	<input class="form-control" name="dataFine" id="dataFine" placeholder="dd/MM/yyyy" type="text" value="" data-provide="datepicker"/>
		
		
		<br>
		<br>
		<br>
		
		<input type="button" value="Riconcilia" onclick="setUrl('/budget/riconciliazione/');" class="btn btn-primary"/>
		
		<br>
		<br>
		<c:choose>
			<c:when test="${ok!=null && ok==true}">
				<div class="text-success">La riconciliazione è stata effettuata con successo</div>
			</c:when>
			<c:when test="${ok!=null && ok==false}">
				<div class="text-danger">C'è stato un errore durante la fase di riconciliazione. Riprovare più tardi</div>
			</c:when>
		
		</c:choose>
	</div>

</body>
<script type="text/javascript">

function setUrl(url){
	
	var dataInizio = document.getElementById("dataInizio").value;
	var dataFine = document.getElementById("dataFine").value;
	
	var d1 = dataInizio.split('/');
	var d2 = dataFine.split('/');
	
	var dataInizioTemp = new Date(d1[2],d1[1]-1,d1[0]);
	dataInizioTemp.setHours(0,0,0,0);
	
	var dataFineTemp = new Date(d2[2],d2[1]-1,d2[0]);
	dataFineTemp.setHours(0,0,0,0);
	
	if(dataFineTemp>dataInizioTemp){
		window.location.href = "/budget/riconciliazione/riconcilia?dataInizio="+dataInizio+"&dataFine="+dataFine;
	} else {
		alert("Data Fine non può essere minore di Data Inizio");
	}
	
}

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