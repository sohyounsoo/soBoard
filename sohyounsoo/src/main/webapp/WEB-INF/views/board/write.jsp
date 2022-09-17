<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="/resources/js/jquery-3.6.0.min.js"/></script>
<script type="text/javascript">
var saveUrl = "<c:url value='/board/writeAjax.do'/>";

var writePage = {
	boardSave : function() {
    	var data = {
    		title: $('#title').val(),
    		content: $('#content').val(),
    		writer : $('#writer').val()
    	}
    	
    	 $.ajax({
                type: 'post',
                url: saveUrl,
                dataType: 'json',
                data: JSON.stringify(data),
                contentType:'application/json; charset=utf-8',
                success: function(data) {
                	alert(data.msg);
                	window.location.href = '/board/selectListPage.do';
                },
                error: function(e) {
                    alert("오류가 발생했습니다.");
                }
         });	
    }   
}

</script>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 조회</title>
</head>
<body>

<form id="writeForm" name="writeForm" method="post" onsubmit="return false;">

<label>제목</label>
<input type="text" id="title" name="title" /><br/>

<label>작성자</label>
<input type="text" id="writer" name="writer" /><br/>

<label>내용</label>
<textarea cols="50" rows="5" id="content" name="content"></textarea><br/>

<button title="조회" onclick="javascript:writePage.boardSave();">작성</button>
</form>

</body>
</html>