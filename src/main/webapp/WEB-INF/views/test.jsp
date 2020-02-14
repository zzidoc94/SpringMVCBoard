<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>test.jsp</h1>
	<c:forEach var="mb" items="${list}">
		${mb.m_id},${mb.m_name},${mb.m_point}
	</c:forEach>
</body>
</html>