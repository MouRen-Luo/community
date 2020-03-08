/**
 *提交回复
 */
function post(){
    var questionId = $("#questionId").val();
    var content = $("#comment_content").val();
    if (!content){
        alert("不能回复空内容！！！");
        return;
    }
    $.ajax({
        type:"POST",
        url:"/comment",
        contentType:"application/json",
        data:JSON.stringify({"parentId":questionId,"content":content,"type":1}),
        success:function (data) {
            if (data.code == 200){
                window.location.reload();
            }else {
                if (data.code == 2003){
                    var isAccepted = confirm(data.message+"45678");
                    if (isAccepted){
                        window.open("https://github.com/login/oauth/authorize?client_id=9649e173e7bd06cf074b&redirect_uri=http://localhost:8080/callback&scope=user&state=1");
                        window.localStorage.setItem("closable",true);
                    }
                }else {
                    alert(data.message);
                }
            }
        },
        dataType:"json"
    });
}

/**
 * 展开二级评论
 */
function collapseComment(e){
    var id = e.getAttribute("data-id");
    var dataId = $("#comment-"+id);
    var collapse = e.getAttribute("data-collapse");
    if (collapse){
        //遮蔽二级评论
        dataId.removeClass("in");
        e.removeAttribute("data-collapse");
        e.classList.remove("active")
    }else {
        //展开二级评论
        dataId.addClass("in");
        //标记二级评论展开状态
        e.setAttribute("data-collapse","in");
        e.classList.add("active")
    }
}