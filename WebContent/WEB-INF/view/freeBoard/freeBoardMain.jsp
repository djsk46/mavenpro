<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script
  src="https://code.jquery.com/jquery-3.5.1.js"
  integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
  crossorigin="anonymous"></script>
  
  
<script type="text/javascript">
$(function () {
	$("#sub_btn").click(function(){
	var sel=$("#sel").val();
	var search=$("#search").val();
	var regexp = /^[0-9]*$/
	if(search.trim()==""){
		
		alert("검색어를 입력해주세요.");
		return false;
	}
		
		if(sel=="num"){
			if( !regexp.test(search) ) {
				alert("숫자만 입력하세요");
			return false;
			}
		}
		
	$.ajax({ 
		 type: "post", 
		 url : "./freeBoardSearch.ino", 
		 data: {"sel":sel,"search":search}, 
		 dataType:'json',
		 success : function(data) {
			 console.log(data);
			 console.log(data.length);
			 var str="";
			 for(var i=0;i<data.length;i++){ 
			   str+="<div style='width: 50px; float: left;'>"+data[i].NUM+"</div>";
			   str+="<div style='width: 300px; float: left;'><a href='./freeBoardDetail.ino?num="+data[i].NUM+"'>"+data[i].TITLE+"</a></div>";
			   str+="<div style='width: 150px; float: left;'>"+data[i].NAME+"</div>";
			   str+="<div style='width: 150px; float: left;'>"+data[i].REGDATE+"</div>";
			   str+="<hr style='width: 600px'>";
			 }
			 $("#res_cont").empty();
			 $("#res_cont").html(str);
			 }, error: function(error) {
				 console.log("error : ");
				 console.log(error);
				 }
			 });	//ajax End
	
	})
}); 




/* --------------------------------------  */
 

</script>

<script type="text/javascript">
$(function () {
var list=${freeBoardList };
var str="";
console.log(list);
console.log(list[0].NUM);
console.log(list.length);

for(var i=0;i<list.length;i++){
	 str+="<div style='width: 50px; float: left;'>"+list[i].NUM+"</div>";	
	 str+="<div style='width: 300px; float: left;'><a href='./freeBoardDetail.ino?num="+list[i].NUM+"'>"+list[i].TITLE+"</a></div>";
	 str+="<div style='width: 150px; float: left;'>"+list[i].NAME+"</div>";
	 str+=" <div style='width: 150px; float: left;'>"+list[i].REGDATE+"</div>";
	 str+="<hr style='width: 600px'>";
}
$("#res_cont").html(str);
});
</script>
  
  <!--공통 코드  -->
 <script type="text/javascript">
 /* $(function () {
	//location.href="./commonCode2.ino";
	$.ajax({ 
				 type: "post", 
				 url : "./commonCode2.ino",
				 dataType:'json',
				 success : function(data) {
					 console.log("data=");
					 console.log(data);
					 var str="";
					 for(var i=0;i<data.length;i++){
						 str+="<option value="+data[i].DCODE+">"+data[i].DCODE_NAME+"</option>";
					 }
					$("#sel").empty();	
					$("#sel").html(str);	
					 
					 }, error: function(error,data) {
					 alert("실패");
						 console.log(error);
						 console.log(data);
						 }
					 });	//ajax End
	
 }); */
 
 </script> 
  
</head>
<body>

	<div>
		<h1>자유게시판</h1>
	</div>
	<div style="width:650px;" align="right">
	
	<div style="display:inline-block; float:left; margin-left: 20px;" >
	<form>
	<select name="sel" id="sel" style="height:30px;">
		<%-- <option value="title" <c:if test="${search.sel eq 'title'}">  selected</c:if>>글제목</option>
		<option value="num" <c:if test="${search.sel eq 'num'}">  selected</c:if>>글번호</option> --%>
	   
	    <c:forEach items="${list2 }" var="list">
		<option value="${list2.DCODE }">${list2.DCODE_NAME }</option>
		</c:forEach>  
	</select>

	<input type="text" name="search" id="search" style="height:24px;" <c:if test="${not empty search}"> value="${search.search}" 
	</c:if>
	 >
	
	<input type="button" id="sub_btn" value="검색">
	</form>
	</div>
	
	<div style="display:inline-block;">
		<a href="./freeBoardInsert.ino">글쓰기</a>
	</div>
	</div>
	<hr style="width: 600px">
	<div id="res_cont">
<%-- 	<c:forEach items="${freeBoardList }" var="dto">
			<div style="width: 50px; float: left;">${dto.num }</div>	
			<div style="width: 300px; float: left;"><a href="./freeBoardDetail.ino?num=${dto.num }">${dto.title }</a></div>
			<div style="width: 150px; float: left;">${dto.name }</div>
			<div style="width: 150px; float: left;">${dto.regdate }</div> 
		<hr style="width: 600px">
	</c:forEach> --%>
	</div>
	
	
</body>
</html>