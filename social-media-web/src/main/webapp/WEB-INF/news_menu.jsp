<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Blog</title>

    <!-- Bootstrap Core CSS -->
    <link href="../static/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="../static/css/blog-post.css" rel="stylesheet">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>
<!-- Page Content -->
<div class="container">

    <div class="row">
        <!-- Blog Post Content Column -->
        <div class="col-lg-6 col-md-6 col-sm-6">
            <%--<c:if test="${requestScope.canModify}">--%>
                <%--<div class="well">--%>
                    <%--<h4>New Post</h4>--%>
                    <%--<form id="postAddForm" method="post"--%>
                          <%--enctype="multipart/form-data">--%>
                        <%--<div class="form-group">--%>
                            <%--<textarea id="newPostText" name="newPostText" class="form-control" rows="3"></textarea>--%>
                        <%--</div>--%>
                        <%--<button id="add" type="submit" class="btn btn-primary">Add--%>
                        <%--</button>--%>
                        <%--<div class="modal-footer">--%>
                            <%--<span>supported formats: </span>--%>
                            <%--<c:forEach items="${requestScope.blogPage.supportedFormats}" var="format">--%>
                                <%--<span><c:out value="${format}"/> </span>--%>
                            <%--</c:forEach>--%>
                            <%--<label id="mediaInputs" class="btn btn-primary">--%>
                                <%--<input name="files" required type="file" hidden>--%>
                            <%--</label>--%>
                            <%--<button onclick="addFileUploader()" type="button" class="btn btn-default"--%>
                                    <%--data-dismiss="modal">--%>
                                <%--More--%>
                            <%--</button>--%>
                        <%--</div>--%>
                    <%--</form>--%>
                <%--</div>--%>
            <%--</c:if>--%>
            <ul style="list-style: none; display: inline;">
                <c:forEach var="i" begin="1" end="${requestScope.newsPage.amountPages}">
                    <li style="display: inline;"><a href="#"
                                                    onclick="postMainDiv('/user/news/${i}', this)">${i}&nbsp;</a>
                    </li>
                </c:forEach>
            </ul>
            <c:forEach items="${requestScope.newsPage.posts}" var="post">
                <hr style="width: 100%; color: black; height: 1px; background-color:black;"/>
                <!-- Author -->
                <p class="lead">
                    by <a onclick="postMainDiv('/user/user/${post.user.idUser}')" href="#"><c:out
                        value="${post.user.firstName}"/> <c:out
                        value="${post.user.lastName}"/></a>
                </p>

                <!-- Date/Time -->
                <p><span class="glyphicon glyphicon-time"></span> Posted
                    on ${post.datePost.toLocalDateTime().getMonth().toString()} ${post.datePost.toLocalDateTime().getDayOfMonth()}, ${post.datePost.toLocalDateTime().getYear()}
                    at ${post.datePost.toLocalDateTime().getHour()}:${post.datePost.toLocalDateTime().getMinute()}
                    <%--<c:if test="${requestScope.canModify}"><a--%>
                            <%--role="button" title="Delete" class="btn btn-danger pull-right"--%>
                            <%--onclick="postMainDiv('/user/delete_post/${post.idPost}/')">Delete Post</a></c:if>--%>
                </p>
                <br>
                <span class="lead"><c:out value="${post.textPost}"/></span>
                <c:forEach items="${post.mediaFiles}" var="mediaFiles">
                    <!-- Preview Image -->
                    <c:choose>
                        <c:when test="${mediaFiles.value eq 'IMAGE'}">
                            <img class="img-responsive" src="/user/media/${mediaFiles.key}/" alt="">
                        </c:when>
                        <c:when test="${mediaFiles.value eq 'VIDEO'}">
                            <video class="img-responsive" <%--width="320" height="240"--%> controls>
                                <source src="/user/media/${mediaFiles.key}/" type="video/mp4">
                                Your browser does not support the video tag.
                            </video>
                        </c:when>
                        <c:when test="${mediaFiles.value eq 'AUDIO'}">
                            <audio style="width: 100%" controls>
                                <source src="/user/media/${mediaFiles.key}/" type="audio/mpeg">
                                Your browser does not support the audio element.
                            </audio>
                        </c:when>
                    </c:choose>
                </c:forEach>
                <!-- Post Content -->
            </c:forEach>
        </div>
        <!-- /.container -->

        <!-- jQuery -->
        <script src="../static/js/jquery-1.10.2.js"></script>

        <!-- Bootstrap Core JavaScript -->
        <script src="../static/js/bootstrap.min.js"></script>
    </div>
</div>
</body>
<script>
    function addFileUploader() {
        var mediaInputs = $('#mediaInputs');
        if (mediaInputs.children().size() < 10) {
            mediaInputs.append("<input name='files' required type='file' hidden>");
        }
    }
    $("#add").click(function (event) {
        //stop submit the form, we will post it manually.
        event.preventDefault();
        // Get form
        var form = $('#postAddForm')[0];
        // Create an FormData object
        var data = new FormData(form);
        // If you want to add an extra field for the FormData
        data.append("CustomField", "This is some extra data, testing");
        // disabled the submit button
        $("#add").prop("disabled", true);
        $.ajax({
            type: "POST",
            enctype: 'multipart/form-data',
            url: "/user/add_new_post",
            data: data,
            processData: false,
            contentType: false,
            cache: false,
            timeout: 600000,
            success: function (data) {
                $("#mainDiv").html(data);
                console.log("SUCCESS : ", data);
                $("#add").prop("disabled", false);
            },
            error: function (e) {
                $("#mainDiv").html(e.responseText);
                console.log("ERROR : ", e);
                $("#add").prop("disabled", false);

            }
        });
    });
</script>
</html>
