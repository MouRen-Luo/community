/**
 *提交回复
 */
function post(){
    var questionId = $("#questionId").val();
    var content = $("#comment_content").val();
    comment2target(questionId,1,content);
}
function comment2target(targetId,type,content) {
    if (!content){
        alert("不能回复空内容！！！");
        return;
    }
    $.ajax({
        type:"POST",
        url:"/comment",
        contentType:"application/json",
        data:JSON.stringify({"parentId":targetId,"content":content,"type":type}),
        success:function (data) {
            if (data.code == 200){
                window.location.reload();
            }else {
                if (data.code == 2003){
                    var isAccepted = confirm(data.message);
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

function comment(e) {
    var id = e.getAttribute("data-id");
    var dataId = $("#input-"+id).val();
    comment2target(id,2,dataId);
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
        var subCommentContainer = $("#comment-"+id);
        if (subCommentContainer.children().length!=1){
            //展开二级评论
            dataId.addClass("in");
            //标记二级评论展开状态
            e.setAttribute("data-collapse","in");
            e.classList.add("active");
        }else{
            $.getJSON("/comment/"+id,function (data) {
                $.each(data.data.reverse(),function (index,comment) {
                    var mediaLeft = $("<div/>",{
                        "class":"media-left"
                    }).append($("<img/>",{
                        "class":"media-object img-rounded",
                        "src":comment.user.imageUrl
                    }));

                    var mediaBody = $("<div/>",{
                        "class":"media-body"
                    }).append($("<h4/>",{
                        "class":"media-heading",
                        "html":comment.user.name
                    })).append($("<div/>",{
                        "html":comment.content
                    })).append($("<div/>",{
                        "class":"menu"
                    }).append($("<span/>",{
                        "class":"glyphicon glyphicon-thumbs-up icon"
                    })).append($("<span/>",{
                        "class":"pull-right",
                        "html": moment(comment.gmtCreate).format("YYYY-MM-DD")
                    })));

                    var hr = $("<hr/>",{

                    });

                    var media = $("<div/>",{
                        "class":"media"
                    }).append(mediaLeft).append(mediaBody).append(hr);

                    var commentElement = $("<div/>",{
                        "class":"col-lg-12 col-md-12 col-xs-12 col-sm-12"
                    }).append(media);
                    subCommentContainer.prepend(commentElement);
                });
                //展开二级评论
                dataId.addClass("in");
                //标记二级评论展开状态
                e.setAttribute("data-collapse","in");
                e.classList.add("active");
            });
        }

    }
}