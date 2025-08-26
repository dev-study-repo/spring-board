<%@ page session="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>게시글 작성</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
    <style>
        * { box-sizing: border-box; margin:0; padding:0; font-family:"Noto Sans KR", sans-serif; }
        body { padding-top: 100px; background-color:#f7f7f7; }
        .container {
            width: 60%; max-width: 900px; margin:0 auto;
            background:#fff; padding:40px 50px;
            border-radius:10px; box-shadow:0 3px 10px rgba(0,0,0,0.1);
            min-height: 600px;
        }
        h2 { font-size:28px; margin-bottom:25px; border-bottom:2px solid #333; padding-bottom:10px; }
        .frm { width:100%; }
        input[type="text"], textarea {
            width:100%; padding:12px; border-radius:5px;
            border:1px solid #ccc; font-size:16px; background:#f8f8f8;
            margin-bottom:15px;
        }
        textarea { min-height:300px; resize:vertical; }
        .btn-group { display:flex; gap:15px; margin-top:20px; }
        .btn { padding:10px 20px; font-size:16px; border-radius:5px; border:none; cursor:pointer; background:#e0e0e0; transition:0.2s; }
        .btn:hover { background:#c0c0c0; }
    </style>
</head>
<body>
<div class="container">
    <h2>게시글 작성</h2>
    <form id="form" class="frm" method="post" accept-charset="UTF-8">
        <input type="hidden" name="writer" id="writer" value="${sessionScope.id}">
        <input type="text" name="title" id="title" placeholder="제목을 입력해 주세요.">
        <textarea name="content" id="content" placeholder="내용을 입력해 주세요."></textarea>

        <div class="btn-group">
            <button type="button" id="writeBtn" class="btn"><i class="fa fa-pencil"></i> 등록</button>
            <button type="button" id="listBtn" class="btn"><i class="fa fa-bars"></i> 목록</button>
        </div>
    </form>
</div>

<script>
    function formCheck() {
        const form = document.getElementById("form");
        if(!form.title.value.trim()){ alert("제목을 입력해 주세요."); form.title.focus(); return false; }
        if(!form.content.value.trim()){ alert("내용을 입력해 주세요."); form.content.focus(); return false; }
        return true;
    }

    <%--$("#writeBtn").on("click", function(){--%>
    <%--    const form = $("#form");--%>
    <%--    form.attr("action", "${pageContext.request.contextPath}/board/write");--%>
    <%--    form.attr("method", "post");--%>
    <%--    if(formCheck()) form.submit();--%>
    <%--});--%>
    $("#writeBtn").on("click", function(){
        if (!formCheck()) return; // 유효성 검사
        const writer = $("#writer").val();
        if (writer === ""){
            alert("로그인이 필요합니다");
            location.href = "${pageContext.request.contextPath}/board/list";
            return;
        }
        const data = {
            title: $("#title").val(),
            content: $("#content").val(),
            writer: writer
        };
        if(formCheck()){
            $.ajax({
                type:'POST',
                url: 'write',
                data: JSON.stringify(data),
                contentType: 'application/json; charset=UTF-8',
                success: function (msg){
                    alert(msg);
                    location.href = "${pageContext.request.contextPath}/board/list";
                },
                error: function (err){ alert(err);}
            })
        }
    });

    $("#listBtn").on("click", function(){
        location.href = "${pageContext.request.contextPath}/board/list";
    });
</script>
</body>
</html>
