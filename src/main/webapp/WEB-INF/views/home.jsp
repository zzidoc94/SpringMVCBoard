<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<script>
	windows.onload=function(){
		var chk=${check};
		if(chk==1){
			alert("회원가입 성공");
		}
		if(chk==2)
			alert("로그인 실패");
		
	}
</script>
<body>
<h1>
	Hello world!  
</h1>

<form action="access" name="LogFrm" method="post">
	<table border="1">
		<tr>
			<td colspan="3" align="center" bgcolor="skyblue">로그인</td>
		</tr>
		<tr>
			<td>아이디</td>
			<td><input type="text" name="m_id" tabindex="1"></td>
			<td rowspan="2" tabindex="3"><button>로그인</button>
		</tr>
		<tr>
			<td>패스워드</td>
			<td><input type="password" name="m_pwd" tabindex="2"></td>
		</tr>
		<tr>
			<td colspan="3" align="center" bgcolor="skyblue">com.board.icia</td>
		</tr>
		<tr>
			<td colspan="3" align="center"><a href="./joinfrm">회원가입</a>
		</tr>
	</table>
</form>
</body>
</html>
