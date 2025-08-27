<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/views/header.jsp" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>게시글 상세보기</title>
    <link rel="stylesheet" href="<c:url value='/resources/css/board.css'/>">
</head>
<body>
<div class="container">
    <h2>${board.title}</h2>

    <div class="meta-info">
        <div>작성자: ${board.writer}</div>
        <div>조회수: ${board.view_cnt}</div>
        <div>댓글 수: ${board.comment_cnt}</div>
        <div>등록일: <fmt:formatDate value="${board.reg_date}" pattern="yyyy-MM-dd"/></div>
        <div>수정일: <fmt:formatDate value="${board.up_date}" pattern="yyyy-MM-dd"/></div>
    </div>

    <div class="detail-row">
        <div class="detail-label">내용</div>
        <div class="detail-value">${board.content}</div>
    </div>

    <div class="btn-group">
        <button type="button" id="modifyBtn" class="btn">수정</button>
        <button type="button" id="removeBtn" class="btn">삭제</button>
        <button type="button" id="listBtn" class="btn">목록</button>
    </div>
    <div class="comment-section">
        <h3>댓글</h3>
        <div id="commentList"></div>
        <div class="comment-form">
            <textarea id="commentContent" placeholder="댓글을 입력하세요"></textarea>
            <button type="button" id="commentBtn">등록</button>
        </div>
        <c:if test="${empty sessionScope.id}">
            <p id="loginAlert" style="color:red; display:none;">로그인 후 댓글을 작성할 수 있습니다.</p>
        </c:if>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
<script>
    const contextPath = "${pageContext.request.contextPath}";
    const bno = "${board.bno}";
    const sessionId = "${sessionScope.id}";

    //게시글 삭제
    $("#removeBtn").on("click", function(){
        if(!confirm("정말 삭제하시겠습니까?")) return;
        <%--location.href = "${pageContext.request.contextPath}/board/remove/${board.bno}";--%>
        $.ajax({
            type:'POST',
            url: 'remove/${board.bno}',
            success: function(msg){
                alert(msg);
                location.href = contextPath+"/board/list";
            },
            error: function(err){ alert(err); }
        });
    });
    //게시글 수정
    $("#modifyBtn").on("click", function(){
        location.href = contextPath+"/board/modify/${board.bno}";
    });
    //목록으로 이동
    $("#listBtn").on("click", function(){
        location.href = contextPath+"/board/list";
    });

    //페이지 로딩 시 댓글 불러오기
    $(document).ready(function() {
        loadComments();
    });
    //댓글 조회
    function loadComments() {
        $.ajax({
            type: 'GET',
            url: contextPath+`/comments/${bno}`,
            dataType: 'json',
            success: function(comments) {
                const $list = $("#commentList");
                $list.empty();
                if (comments.length === 0) {
                    $list.append("<p>댓글이 없습니다.</p>");
                    return;
                }
                comments.forEach(function(c) {
                    var html = '<div class="comment" data-cno="' + c.cno + '">' +
                        '<strong>' + c.commenter + '</strong>' +
                        '<small>' + c.reg_date + '</small>' +
                        '<p>' + c.comment + '</p>';

                    if(sessionId === c.commenter){
                        html += '<button class="editBtn btn">수정</button>' +
                            '<button class="deleteBtn btn">삭제</button>';
                    }

                    html += '<button class="replyBtn btn">답글</button>';

                    html += '</div>';
                    if(!c.pcno) {
                        $list.append(html);
                    } else {
                        $list.find("[data-cno='"+c.pcno+"']").append('<div class="reply">'+html+'</div>');
                    }
                });
            },
            error: function() {
                alert("댓글을 불러오는 중 오류가 발생했습니다.");
            }
        });
    }
    //댓글 작성
    $("#commentBtn").on("click", function() {
        const sessionId = "${sessionScope.id}";
        const content = $("#commentContent").val().trim();

        if (!content) {
            alert("댓글 내용을 입력해주세요.");
            return;
        }

        if (!sessionId) {
            alert("로그인이 필요합니다.");
            return;
        }

        const data = {
            bno: "${board.bno}",
            pcno: null,
            comment: content,
            commenter: sessionId
        };

        $.ajax({
            type: 'POST',
            url: contextPath+`/comments/${bno}`,
            data: JSON.stringify(data),
            contentType: "application/json; charset=UTF-8",
            success: function (msg){
                alert(msg);
                location.reload();
            },
            error: function (err){ alert(err); }
        })
    });
    //댓글 수정 버튼
    $(document).on("click", ".editBtn", function(){
        const $commentDiv = $(this).closest(".comment");
        const currentContent = $commentDiv.find("p").text();

        $commentDiv.data("original", currentContent); //원래 내용

        // 댓글 내용을 textarea로 교체
        $commentDiv.find("p").replaceWith('<textarea class="editContent">' + currentContent + '</textarea>');

        // 버튼 텍스트 변경
        $(this).text("저장").removeClass("editBtn").addClass("saveBtn");
        $commentDiv.find(".deleteBtn").text("취소").removeClass("deleteBtn").addClass("cancelBtn");
    });
    //댓글 수정 내용 저장
    $(document).on("click",".saveBtn", function (){
        const $commentDiv = $(this).closest(".comment");
        const cno = $commentDiv.data("cno");
        const newContent = $commentDiv.find(".editContent").val().trim();

        if(!newContent){
            alert("댓글 내용을 입력해주세요.");
            return;
        }

        const data = {
            cno: cno,
            comment: newContent,
        }

        $.ajax({
            type: 'POST',
            url: contextPath+"/comments/modify",
            data: JSON.stringify(data),
            contentType: "application/json; charset=UTF-8",
            success: function (msg){
                alert(msg);
                loadComments();
            },
            error: function (err){alert(err);}
        });
    });
    //댓글 취소 버튼
    $(document).on("click", ".cancelBtn", function(){
        const $commentDiv = $(this).closest(".comment");
        const originalContent = $commentDiv.data("original");

        // textarea를 원래 p로 복원
        $commentDiv.find(".editContent").replaceWith('<p>' + originalContent + '</p>');

        $commentDiv.find(".saveBtn").text("수정").removeClass("saveBtn").addClass("editBtn");
        $(this).text("삭제").removeClass("cancelBtn").addClass("deleteBtn");
    });

    //대댓글 버튼
    $(document).on("click", ".replyBtn", function(){
        const $commentDiv = $(this).closest(".comment");

        if($commentDiv.find(".reply-form").length > 0) return;

        const replyForm = `
        <div class="reply-form">
            <textarea class="replyContent" placeholder="답글을 입력하세요"></textarea><br>
            <button class="replySubmit btn">등록</button>
            <button class="replyCancel btn">취소</button>
        </div>
    `;
        $commentDiv.append(replyForm);
    });

</script>
</body>
</html>
