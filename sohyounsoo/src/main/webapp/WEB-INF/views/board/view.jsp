<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="/resources/js/jquery-3.6.0.min.js"/>"</script>
<script type="text/javascript">
var updateUrl      = "<c:url value='/reply/updateReply.do'/>";
var deleteUrl      = "<c:url value='/reply/deleteReply.do'/>";
var selectReplyUrl = "<c:url value='/reply/selectReply.do'/>";

var view = {
    update : function(bno) {
//     	var data = {
//     		bno : bno,
//     		title : $('#title').val(),
//     		content : $('#content').val()
//     	}
//     	 $.ajax({
//              type: 'POST',
//              url: updateUrl,
//              dataType: 'json',
//              // data: JSON.stringify(data),
//              data: data,
//              contentType:'application/json; charset=utf-8',
//              success: function(data) {
//              	alert(data.msg);
//              	window.location.href = '/board/selectListPage';
//              },
//              error: function(e) {
//                  alert("오류가 발생했습니다.");
//              }
//          });
    	$.ajax({
            type: 'POST',
            url: updateUrl,
            dataType: 'json',
            data: $("#viewFrm").serialize(),
            success: function(data) {
            	alert(data.msg);
            	//window.location.href = '/board/selectListPage.do';
            },
            error: function(e) {
                alert("오류가 발생했습니다.");
            }
        });	
    },
    
    delete : function(bno) {
    	$.ajax({
            type: 'POST',
            url: deleteUrl,
            dataType: 'json',
            data: {
            	bno : $("#bno").val(),
            	
            },
            success: function(data) {
            	alert(data.msg);
            	window.location.href = '/board/selectListPage.do';
            },
            error: function(e) {
                alert("오류가 발생했습니다.");
            }
        });	
    },

    selectComent : function(nowPage) {
    	if(nowPage == '') nowPage = 1;
    	$.ajax({
            type: 'POST',
            url: selectReplyUrl,
            dataType: 'html',
            data: {
            	bno : $("#bno").val(),
            	nowPage : nowPage
            },
            success: function(data) {
				$('#replyList').html(data);
				$('#nowPage').val(nowPage);
				
            },
            error: function(e) {
                alert("오류가 발생했습니다.");
            }
        });	
    }
}

$(document).ready(function() {
	view.selectComent('');
});

</script>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 작성</title>
</head>
<body>
<div id="nav">
	<%@ include file="../include/nav.jsp" %>
</div>
	
<form id="viewFrm" name="viewFrm" method="post" onsubmit="return false;">
<input type='text' id="bno" name="bno" value="${view.bno}" />

	<label>제목</label>
	<input type="text" id="title" name="title" value="${view.title}" /><br/>
	
	<label>작성자</label>
	<input type="text" id="writer" name="writer" value="${view.writer}" /><br/>
	
	<label>내용</label>
	<textarea cols="50" rows="5" id="content" name="content">${view.content}</textarea><br/>
	
	<button title="수정" onclick="view.update('${view.bno}');">수정</button>
	<button title="삭제" onclick="view.delete('${view.bno}');">삭제</button>
</form>

<!-- 댓글 리스트 -->
<div id="replyList"></div>

</body>
</html>