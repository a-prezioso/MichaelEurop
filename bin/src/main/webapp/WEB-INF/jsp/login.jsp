<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%> 
<!DOCTYPE html>
<html>
<head>
    <title>Log in</title>
    <link href="/webjars/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" type="text/css">
	<script  type="text/javascript" src="/webjars/bootstrap/4.0.0/js/bootstrap.min.js"></script>
	<script  type="text/javascript" src="/webjars/jquery/3.0.0/jquery.min.js"></script>
</head>
<body>
<div class="container">
    <div style="width:600px;margin-left: auto;margin-right: auto;margin-top:24px;padding: 24px;">
        <div class="card">
            <div class="card-header">
                <i class="fa fa-user"></i> Esegui il Login
            </div>
            <div class="card-block" style="padding: 24px;">
            <spring:url value="/login" var="loginUrl"/>
                <form name="f" action="${loginUrl}" method="post">
                    <fieldset>
                    
                    	<c:if test="${param.error!=null}">
                    		<div class="alert alert-danger">
	                            Username o password invalida.
	                        </div>
                    	</c:if>
                       
                        <c:if test="${param.logout!=null}">
                    		<div class="alert alert-success">
	                            Sei stato disconnesso.
	                        </div>
                    	</c:if>
                    	
                    	<c:if test="${param.forbidden!=null}">
                    		<div class="alert alert-warning">
	                            Non si dispone delle autorizzazioni necessarie!
	                        </div>
                    	</c:if>


                        <!-- Login Controls -->
                        <div class="form-group">
                            <label for="username">Username</label>
                            <input type="text" class="form-control" id="username" name="username"
                                   placeholder="Username">
                        </div>

                        <div class="form-group">
                            <label for="password">Password</label>
                            <input type="password" class="form-control" id="password" name="password"
                                   placeholder="Password">
                        </div>

                        <div class="form-check">
                            <input type="checkbox" class="form-check-input" id="remember-me" name="remember-me">
                            <label class="form-check-label" for="remember-me">Ricorda password</label>
                        </div>
                        
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                        <!-- Login Button -->
                        <div class="form-actions" style="margin-top: 12px;">
                            <button type="submit" class="btn btn-success">Log in</button>
                        </div>
                    </fieldset>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>

