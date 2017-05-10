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
            <div class="well">
                <h4 >New Post</h4>
                <i role="button" title="Switch To Full Blog Menu" onclick="postMainDiv('/user/blog_menu_full', this)"
                   class="glyphicon glyphicon-th"></i>
                <form role="form">
                    <div class="form-group">
                        <textarea id="newPostText" class="form-control" rows="3"></textarea>
                    </div>
                    <button onclick="addNewPost('/user/add_new_post_short')" class="btn btn-primary">Add</button>
                </form>
            </div>
            <c:forEach items="${requestScope.posts}" var="post">
                <hr style="width: 100%; color: black; height: 1px; background-color:black;"/>
                <!-- Author -->
                <p class="lead">
                    by <a onclick="postMainDiv('/user/user/${requestScope.user.idUser}')" href="#"><c:out
                        value="${requestScope.user.firstName}"/> <c:out value="${requestScope.user.lastName}"/></a>
                </p>

                <!-- Date/Time -->
                <p><span class="glyphicon glyphicon-time"></span> Posted
                    on ${post.datePost.toLocalDateTime().getMonth().toString()} ${post.datePost.toLocalDateTime().getDayOfMonth()}, ${post.datePost.toLocalDateTime().getYear()}
                    at ${post.datePost.toLocalDateTime().getHour()}:${post.datePost.toLocalDateTime().getMinute()}
                </p>

                <!-- Preview Image -->
                <img class="img-responsive" src="http://placehold.it/900x300" alt="">

                <!-- Post Content -->
                <span class="lead"><c:out value="${post.textPost}"/></span>

            </c:forEach>
            <hr>
            <!-- /.row -->
            <hr>
            <!-- Footer -->
            <footer>
                <div class="row">
                    <div class="col-lg-6 col-md-6 col-sm-6">
                        <p>Copyright &copy; Your Website 2014</p>
                    </div>
                </div>
                <!-- /.row -->
            </footer>

        </div>
        <!-- /.container -->

        <!-- jQuery -->
        <script src="../static/js/jquery-1.10.2.js"></script>

        <!-- Bootstrap Core JavaScript -->
        <script src="../static/js/bootstrap.min.js"></script>
    </div>
</div>
</body>

</html>
