<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<spring:url var="css" value="/resources/css" />
<spring:url var="js" value="/resources/js" />
<spring:url var="images" value="/resources/images" />

<c:set var="contextRoot" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description"
	content="Ecom Website Using Spring MVC and Hibernate">
<meta name="author" content="Usama Ghori">

<title>ecom1 - Membership</title>

<script>

	window.contextRoot = '${contextRoot}'
	
</script>
<!-- Bootstrap Core CSS -->
<link href="${css}/bootstrap.min.css" rel="stylesheet">

<!-- Bootstrap Readable theme CSS -->
<link href="${css}/bootstrap-readable-theme.min.css" rel="stylesheet">

<!-- Bootstrap DataTable CSS -->
<link href="${css}/dataTables.bootstrap.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="${css}/myapp.css" rel="stylesheet">

</head>

<body>
<body>
	<div class="se-pre-con"></div>
	<div class="wrapper">

	    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	        <div class="container">
	            <!-- Brand and toggle get grouped for better mobile display -->
	            <div class="navbar-header">
	                <a class="navbar-brand" href="${flowExecutionUrl}&_eventId=home">ecom1</a>
	            </div>
			</div>
		</nav>
		
		
		<!-- Page Content -->

		<div class="content">