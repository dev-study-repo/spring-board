<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/header.jsp" %>
<html>
<head>
	<meta charset="utf-8">
	<title>UserInfo</title>
</head>
<body>
	<div id="main-content">
		<p>  id ${id} </p>
		<p>  pwd ${pwd} </p>
		<p> session - id <%= session.getAttribute("id") %> </p>
		<p> session - pwd <%= session.getAttribute("pwd") %> </p>
	</div>
</body>
</html>