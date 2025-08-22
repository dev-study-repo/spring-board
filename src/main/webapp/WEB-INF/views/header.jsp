<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
    <meta charset="utf-8">
    <title>FastCampus</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<div id="menu">
    <ul>
        <li id="logo">fastcampus</li>
        <li><a href="<c:url value='/'/>">Home</a></li>
        <li><a href="<c:url value='/board/list'/>">Board</a></li>
        
        <c:choose>
            <c:when test="${not empty sessionScope.id}">
                <li><a href="<c:url value='/logout'/>">Logout</a></li>
            </c:when>
            <c:otherwise>
                <li><a href="<c:url value='/login-form'/>">Login</a></li>
            </c:otherwise>
        </c:choose>

        <li><a href="<c:url value=''/>">Sign in</a></li>
        <li><a href=""><i class="fa fa-search"></i></a></li>
    </ul>
</div>
