<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/header.jsp" %>
<html>
<head>
	<meta charset="utf-8">
	<title>UserInfo</title>
	<link rel="stylesheet" href="<c:url value='/resources/css/style.css'/>">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"/>
</head>
<body>

	<form action="/ch2/user-info" method="post" onsubmit="return formCheck(this);">
		<h3 id="title">Login</h3>
		<div id="msg">
			<c:if test="${not empty param.msg}">
				<i class="fa fa-exclamation-circle"> ${URLDecoder.decode(param.msg)}</i>
			</c:if>
		</div>
		<input type="text" name="id" value="${rem eq 'y' ?sessionScope.id : ''}" placeholder="아이디" autofocus>
		<input type="password" name="pwd" placeholder="비밀번호">
		<input type="hidden" name="toURL" value="${param.toURL}">
		<button>로그인</button>
		<div>
			<label><input type="checkbox" name="remember" value="y"> 아이디 기억</label> |
			<a href="">비밀번호 찾기</a> |
			<a href="">회원가입</a>
		</div>
		<script>
			function formCheck(frm) {
				let msg ='';
				if(frm.id.value.length==0) {
					setMessage('id를 입력해주세요.', frm.id);
					return false;
				}
				if(frm.pwd.value.length==0) {
					setMessage('password를 입력해주세요.', frm.pwd);
					return false;
				}
				return true;
			}
			function setMessage(msg, element){
				document.getElementById("msg").innerHTML = ` ${'${msg}'}`;
				if(element) {
					element.select();
				}
			}
		</script>
	</form>
</body>
</html>