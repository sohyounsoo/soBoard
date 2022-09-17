<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="/resources/js/jquery-3.6.0.min.js"/></script>
<script type="text/javascript">
var writeReplyUrl = "<c:url value='/reply/writeReply.do'/>";
var deleteReplyUrl = "<c:url value='/reply/deleteReply.do'/>";
var updateReplyUrl = "<c:url value='/reply/updateReply.do'/>";

var replyPage = {
	writeReply : function(index) {
		var param = null;
		if(index == undefined) param = $("#cmtFrm").serialize(); // 댓글
		else param = $("#nestedFrm_" + index).serialize(); // 답글
		$.ajax({
            type: 'POST',
            url: writeReplyUrl,
            dataType: 'json',
            data: param,
            success: function(data) {
            	alert(data.msg);
            	view.selectComent($('#nowPage').val());
            },
            error: function(e) {
                alert("오류가 발생했습니다.");
            }
        });
	},
	
	// 답글 폼
	nestedReply : function(rno, index) {
		$("[id^='nestedDiv']").hide();
		$("#nestedDiv"+ index).empty(); // 초기화
		var html = "";
		html += '<form id ="nestedFrm_'+index+'" method="post" onsubmit="return false">';
		html += '<input type="hidden" id="bno" name="bno" value="'+${bno}+'">';
		html += '<input type="hidden" id="parnt_rno" name="parnt_rno" value="'+rno+'">';
		html += '<div>';
		html += 	'<p>';
		html += 		'<label>답글 작성자</label> <input type="text" name="writer">';
		html += 	'</p>';
		html += 	'<p>';
		html += 		'<textarea rows="5" cols="50" name="content"></textarea>';
		html += 	'</p>';
		html += 	'<p>';
		html += 		'<button onclick="javascript:replyPage.writeReply('+index+');">답글 작성</button>';
		html += 	'</p>';
		html += '</div>';
		html += '</from>';
		
 		$("#nestedDiv"+ index).append(html); // 태그 추가
		$("#nestedDiv"+ index).show();
		
	},

	delete : function(rno) {
		$.ajax({
            type: 'POST',
            url: deleteReplyUrl,
            dataType: 'json',
            data: {
            	rno : rno,
            	bno : $("#bno").val()
            },
            success: function(data) {
            	alert(data.msg);
            	view.selectComent($('#nowPage').val());
            },
            error: function(e) {
                alert("오류가 발생했습니다.");
            }
        });
	},
	
	update : function(rno) {
		$.ajax({
            type: 'POST',
            url: updateReplyUrl,
            dataType: 'json',
            data: {
            	rno : rno,
            	bno : $("#bno").val(),
            	content : $("#replyContent").val(),
            	writer : $("#replyWriter").val()
            },
            success: function(data) {
            	alert(data.msg);
            	view.selectComent($('#nowPage').val());
            },
            error: function(e) {
                alert("오류가 발생했습니다.");
            }
        });
	}
}

</script>

	<c:forEach items="${replyList}" var="reply" varStatus="status" >
		<c:choose>
			<c:when test="${reply.depth > 0}">
				<div>
				<p>
					<c:forEach items="${replyList}" var="replyList" begin="1" end="${reply.depth}" step="1">
						ㄴ
					</c:forEach>
					답글 작성자 : ${reply.writer}, 작성시간 <fmt:formatDate value="${reply.regDate}" pattern="yyyy-MM-dd" />
				</p>
				<p>
					<textarea rows="5" cols="50" id="replyContent">${reply.content}</textarea>
				</p>
				<p>
					<button data-number="${status.index}" onclick="javascript:replyPage.nestedReply('${reply.rno}', '${status.index}')">답글 달기</button>
					<button onclick="javascript:replyPage.update('${reply.rno}')">댓글 수정</button>
					<button onclick="javascript:replyPage.delete('${reply.rno}')">댓글 삭제</button>
				</p>
			</div>
			</c:when>
		
			<c:otherwise>
		    <div>
				<p>
					댓글 작성자 : ${reply.writer}, 작성시간 <fmt:formatDate value="${reply.regDate}" pattern="yyyy-MM-dd" />
				</p>
				<p>
					<textarea rows="5" cols="50" id="replyContent">${reply.content}</textarea>
				</p>
				<p>
					<button id="button" data-number="${status.index}" onclick="javascript:replyPage.nestedReply('${reply.rno}', '${status.index}')">답글 달기</button>
					<button onclick="javascript:replyPage.update('${reply.rno}')">댓글 수정</button>
					<button onclick="javascript:replyPage.delete('${reply.rno}')">댓글 삭제</button>
				</p>
			</div>
			</c:otherwise>
		</c:choose>
		
		<!-- 답글 div -->
		<div id="nestedDiv${status.index}" style="display:none"></div>
	
	</c:forEach>

<!-- 댓글 작성 -->
<form id="cmtFrm" name="cmtFrm" method="post" onsubmit="return false">
<input type="hidden" id="bno" name="bno" value="${bno}">
<input type="hidden" id="nowPage" name="nowPage" value="">
<input type="hidden" id="parnt_rno" name="parnt_rno" value="0">
<input type="hidden" id="depth" name="depth" value="0">

	<div>
		<p>
			<label>댓글 작성자</label> <input type="text" name="writer">
		</p>
		<p>
			<textarea rows="5" cols="50" name="content"></textarea>
		</p>
		<p>
			<button onclick="javascript:replyPage.writeReply()">댓글 작성</button>
		</p>
	</div>
	
</form>

${Pagging}


