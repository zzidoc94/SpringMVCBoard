<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	table{
		width:100%;
	}
	table,td,th{
		border:1px solid black;
		border-collapse:collapse; 
		padding:5px;
	}
	input[type='text']{
		width:100%;
	}
	textarea {
		width:98%;
		
	}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
</head>
<body>
	<form action="boardwrite" name= frm id="frm" method="post" enctype="multipart/form-data">
		<table border="1">
			<tr>
				<td>제목</td>
				<td><input type="text" name="b_title" id="b_title"></td>
			</tr>
			<tr>
				<td>내용</td>
				<td><textarea name="b_contents" id="b_contents" rows="20"></textarea>
			</tr>
			<tr>
				<td>파일첨부</td>
				<td>
					<input type="file" name="files" multiple id="files" />
					<input type="hidden" id="fileCheck" name="file_Check" value="0" />
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="글작성"/>
					<input type="button" onclick="formData()" value="FormData"/>
					<input type="reset" id="reset" value="취소" onclick='reset()'/>
					<input type="button" onclick="location.href='boardlist'" value="리스트보기"/>
				</td>
			</tr>
		</table>
	</form>
</body>
<script>
$("#files").on("change", function(){
    console.log(this.value); //첨부된 파일
    if(this.value===''){
       console.log("empty");
       $("#fileCheck").val(0); //첨부 안됨
    }else{
       console.log("not empty");
       $("#fileCheck").val(1); //첨부됨
    }
    console.log($("#fileCheck").val());
 });
	/* function fCheck(elem){
		console.dir(elem);
		if(elem.value===''){
			console.log("empty");
			$('#fileCheck').val(0);
		}else{
			console.log("not empty");
			$('#fileCheck').val(1);
		}
		console.log($('#fileCheck').val());
	} */
	function reset(){
		$('#fileCheck').val(0);
	}
	function formData(){
		var $obj=$('#files');
		console.log($obj);//jQuery 객체
		console.log($obj[0]);//javaScript 객체
		//$obj=document.getElementById('files');
		console.dir($obj[0]);//파일 엘리먼트
		console.dir($obj[0].files);//첨부된 파일리스트
		console.dir($obj[0].files.length);//javaScript 객체
		console.dir($obj[0].files[0]);//1번째 파일 정보
		console.dir($obj[0].files[1]);//2번째 파일 정보
		
		//FormData 사용 목적
		//1.multipart/form-data 를 전송시 무조건 사용(파일 업로드)
		//2.ajax를 이용한 RestFul에서 사용함
		//3.FormData객체는 form의 일부데이터만 서버에 전송할때도 좋습니다.
		
		
		/* var formData=new FormData(document.getElementById("frm"));	//폼데이터 객체
		console.log(formData.get("b_title"));
		console.log(formData.get("b_contents")); */
		
		var formData=new FormData();
		formData.append("b_title",$('#b_title').val());
		formData.append("b_contents",$('#b_contents').val());
		formData.append("fileCheck",$('#fileCheck').val());//0,1
		
		var files=$obj[0].files;//첨부된 파일 정보
		for(var i=0;i<files.length;i++){
			formData.append("files",files[i]);	//Map과 달리 속성(키)이 같아도 중복 저장
		}
		console.log(formData.get("b_title"));
		console.log(formData.getAll("files"));
		$.ajax({
	         url:'rest/boardwrite',
	         type:'post',
	         data:formData,
	         //까보지 말라는 뜻
	         processData:false,//application/x-www-form-urlencoded(쿼리스트링)
	         contentType:false,//multipart의 경우 contentType을 false로
	         //contentType:'application/json;charset=UTF-8'
	         dataType:'json',
	         success:function(data){
	            alert('성공');
	            console.log(data);
	         },
	         error:function(err){
	            console.log(err)
	         }
	      })//ajaxEnd 
	   	
	}
	
	
</script>

</html>