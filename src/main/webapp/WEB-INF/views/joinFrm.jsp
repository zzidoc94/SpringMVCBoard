<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
	.subject{
		text-align:center;
		height:50px;
	}
</style>
<body>
	<form name="joinFrm" action="memberjoin" method="post" onsubmit="return check()">
		<table border ="1">
			<tr>
				<td colspan="2" class="subject">회원가입</td>
			</tr>
			<tr>
				<td width="100">id</td>
				<td><input type="text" name="m_id"></td>
			</tr>
			<tr>
				<td width="100">pwd</td>
				<td><input type="password" name="m_pwd"></td>
			</tr>
			<tr>
				<td width="100">name</td>
				<td><input type="text" name="m_name"></td>
			</tr>
			<tr>
				<td width="100">birth</td>
				<td><input type="text" name="m_birth"></td>
			</tr>
			<tr>
				<td width="100">addr</td>
				<td><input type="text" name="m_addr"></td>
			</tr>
			<tr>
				<td width="100">phone</td>
				<td><input type="text" name="m_phone"></td>
			</tr>
			<tr>
				<td colspan="2" class="subject"><input type="submit" value="회원가입"></td>
			</tr>
		</table>
	</form>
	<script>
		function check(){
			var frm=document.joinFrm;
			var length=frm.length-1;
			for(var i=0;i<length;i++){
				if(frm[i].value==''){
					alert(frm[i].name+"이 입력되지 않음");
					frm[i].focus();
					return false;//실패
				}	
			}
			return true;//성공
		}
	</script>
</body>
</html>