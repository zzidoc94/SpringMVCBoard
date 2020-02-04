<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 리스트</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script>
	function logout(){
		$('#logoutFrm').submit();
	}
	
</script>
<style>
html, body {
	height: 100%;
	margin: 0
}

#articleView_layer {
	display: none;
	position: fixed;
	position: absolute;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%
}

#articleView_layer.open {
	display: block;
	color: red
}

#articleView_layer #bg_layer {
	position: absolute;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	background: #000;
	opacity: .5;
	filter: alpha(opacity = 50);
	z-index: 100
}

#contents_layer {
	position: absolute;
	top: 40%;
	left: 40%;
	width: 400px;
	height: 400px;
	margin: -150px 0 0 -194px;
	padding: 28px 28px 0 28px;
	border: 2px solid #555;
	background: #fff;
	font-size: 12px;
	z-index: 200;
	color: #767676;
	line-height: normal;
	white-space: normal;
	overflow: scroll
}
</style>
</head>
<body>
<h1>boardList.jsp</h1>


<c:if test="${!empty id }">
	<div align="right">
		<form id="logoutFrm" action='logout' method="post">
			<a href="javascript:logout()">로그아웃</a>
		</form>
	</div>
</c:if>


<h3>회원정보</h3>
<table id="one_table">
	<tr height="30">
		<td width="80" bgcolor="pink" align="center">id</td>
		<td>${mb.m_id }</td>
	</tr>
	<tr height="30">
		<td width="80" bgcolor="pink" align="center">이름</td>
		<td>${mb.m_name }</td>
	</tr>
	<tr height="30">
		<td width="80" bgcolor="pink" align="center">등급</td>
		<td>${mb.g_name }</td>
	</tr>
	<tr height="30">
		<td width="80" bgcolor="pink" align="center">포인트</td>
		<td>${mb.m_point }</td>
	</tr>
</table><br>
<table>
	<tr bgcolor="skyblue" height="30">
		<th width="100">번호</th>
		<th width="100">제목</th>
		<th width="100">작성자</th>
		<th width="100">작성일</th>
		<th width="100">조회수</th>
	</tr>
	<c:forEach var="board" items="${bList}">
		<tr height="25">
			<td>${board.b_num}</td>
			<td><a href="#" onclick="articleView(${board.b_num})">${board.b_title}</a></td>
			<td>${board.b_id}</td>
			<td>${board.b_date}</td>
			<td>${board.b_views}</td>
		</tr>
	</c:forEach>
</table>

<!-- 페이징 출력 -->
<div align="center" id="page">
	${paging}
</div>


<!-- 모달 박스 -->
<div id="articleView_layer">
	<div id="bg_layer"></div>
	<div id="contents_layer"></div>
</div>



<script>
	function articleView(b_num){
		$("#articleView_layer").addClass('open');	//모달박스  나타내기
		$.ajax({
			type:'get',
			url:'contents',
			data:{bNum:b_num},
			dataType:'html',
			success:function(data){
				
				$("#contents_layer").html(data);
			},
			error:function(error){
				console.log(error);
			}
			
		})//ajax End
	}//function End
	
	//모달박스 해제
	var $layerWindow=$("#articleView_layer");
	$layerWindow.find('#bg_layer').on('mousedown',function(event){
		console.log(event);
		$layerWindow.removeClass('open');
	});
	//esc 입력시 모달 해제
	$(document).keydown(function(event){
		console.log(event);
		if(event.keyCode!=27)
			return;
		else if($layerWindow.hasClass('open'))
			$layerWindow.removeClass('open');
	});
</script>
</body>
</html>