<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="/resources/js/jquery-3.6.0.min.js"/>"</script>
<script type="text/javascript">

var boardViewUrl = "<c:url value='/board/seletBoardDetail.do'/>";
var listPageUrl = "<c:url value='/board/listPage.do'/>";

var listPage = {
	selectList : function(num) {
        $.ajax({
            url: listPageUrl,
            dataType: 'html',
            type: 'get',
            data: {
            	nowPage : num,
            	searchType : $("#searchType").val(),
            	keyword : $("#keyword").val()
            }, 
            success: function(data) {
                $('#contentsList').html(data);
            },
            error: function(e) {
                alert('테이블을 가져오는데 실패하였습니다.');
            }
        });
    },
    
    boardView : function(bno) {
    	var f = document.listFrm;
    	
    	$("#bno").val(bno);
    	   
        f.target = "_self";
        f.action = boardViewUrl;
        f.submit();
    }
    
}

$(document).ready(function() {
	listPage.selectList('1');
	
	$(":input").keyup(function(e) {		//text입력란에서 키를 눌렀을시 실행되는 이벤트이다
		if(e.keyCode == 13) {			//enter를 쳤을떄 실행
			listPage.selectList('');	//검색을 한다
		}
	});
	
});

</script>
<form id="listFrm" name="listFrm" method="post" onsubmit="return false;">
	<input type='hidden' id="bno" name="bno" value="" />
</form>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 리스트</title>
</head>
<body>

<div id="nav">
	<%@ include file="../include/nav.jsp" %>
</div>

<div id="contentsList"></div>

<div>
  <select id="searchType" name="searchType">
      <option value="title">제목</option>
      <option value="content">내용</option>
      <option value="title_content">제목+내용</option>
      <option value="writer">작성자</option>
  </select>
  <input type="text" id="keyword" name="keyword" />
  <button type="button" onclick="listPage.selectList('')">검색</button>
 </div>
	
</body>
</html>