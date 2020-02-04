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
   <h3>게시글 상세보기</h3>
   <h3>Board * Reply Contents</h3>
   <a href="boardDelete?bNum=${board.b_num}">삭제</a>
   <table>
      <tr height="30">
         <td width="100" bgcolor="pink" align="center">NUM</td>
         <td colspan="5">${board.b_num}</td>
      </tr>

      <tr height="30">
         <td bgcolor="pink" align="center">WRITER</td>
         <td width="150">${board.b_id}</td>

         <td bgcolor="pink" align="center">DATE</td>
         <td width="150">${board.b_date}</td>

         <td bgcolor="pink" align="center">VIEWS</td>
         <td width="150">${board.b_views}</td>
      </tr>
      
      <tr height="30">
         <td bgcolor="pink" align="center">TITLE</td>
         <td colspan="5">${board.b_title}</td>
      </tr>
      
      <tr height="30">
         <td bgcolor="pink" align="center">CONTENTS</td>
         <td colspan="5">${board.b_contents}</td>
      </tr>
   </table>
   
    <form name="rFrm" id="rFrm">
   		<table>
   		<tr>
   			<td><textarea rows="3" cols="50" name="r_contents" style="width:300px"></textarea>
   			<td><input type="button" value="댓글 전송" style="width:70px;height:50px;"></td>
   		</tr>
   		</table>
    </form>
   
    <table>
   		<c:forEach var="r" items="${rList}">
   			<tr height="25" align="center">
	   			<td width="100">${r.r_id }</td>
	   			<td width="200">${r.r_contents }</td>
	   			<td width="200">${r.r_date }</td>
   			</tr>
   		</c:forEach>
    </table>
</body>
</html>