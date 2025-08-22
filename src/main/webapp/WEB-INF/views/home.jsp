<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/header.jsp" %>
<html>
<head>
	<meta charset="utf-8">
	<title>Home</title>
	<link rel="stylesheet" href="<c:url value='/resources/css/style.css'/>">
</head>
<body>
	<div id="main-content">
	    <p id="server-time">The time on the server is ${serverTime}.</p>
	</div>
</body>
</html>
