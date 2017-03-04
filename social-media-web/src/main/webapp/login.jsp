<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 04.03.2017
  Time: 17:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <%--<meta name="viewport" content="width=device-width, initial-scale=1">--%>
    <link rel='stylesheet prefetch' href='http://aleksite.6te.net/css/bootstrap.css'>
    <%--<link rel="stylesheet" type="text/css" href="assets/css/bootstrap.css">--%>

    <!-- Website CSS style -->
    <%--<link rel="stylesheet" type="text/css" href="assets/css/main.css">--%>
    <link rel="stylesheet" type="text/css" href="css/login.css">

    <!-- Website Font style -->
    <%--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css">--%>

    <!-- Google Fonts -->
    <link href='https://fonts.googleapis.com/css?family=Passion+One' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Oxygen' rel='stylesheet' type='text/css'>

    <title>Login Page</title>
</head>
<body>
<div class="container" style="margin-top:40px">
    <div class="row">
        <div class="col-sm-6 col-md-4 col-md-offset-4">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <strong> Sign in to continue</strong>
                </div>
                <div class="panel-body">
                    <form role="form" <%--action="login"--%> method="POST">
                        <%--<fieldset>--%>
                            <div class="row">
                                <div class="center-block">
                                    <img class="profile-img"
                                         src="https://lh5.googleusercontent.com/-b0-k99FZlyE/AAAAAAAAAAI/AAAAAAAAAAA/eu7opA4byxI/photo.jpg?sz=120"
                                         alt="">
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-12 col-md-10  col-md-offset-1 ">
                                    <div class="form-group">
                                        <div class="input-group">
												<span class="input-group-addon">
                                                    <%--<i class="glyphicon glyphicon-user"></i>--%>
                                                </span>
                                            <input class="form-control" placeholder="Login or Email" id="loginname"
                                                   name="loginname" type="text">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="input-group">
												<span class="input-group-addon">
                                                    <%--<i class="glyphicon glyphicon-lock"></i>--%>
                                                </span>
                                            <input class="form-control" placeholder="Password" id="password" name="password"
                                                   type="password">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <input onclick="postAuthorization('/login')" type="button" class="btn btn-lg btn-primary btn-block" value="Sign in">
                                    </div>
                                </div>
                            </div>
                        <%--</fieldset>--%>
                    </form>
                </div>
                <div class="panel-footer ">
                    Don't have an account! <a href="registration.jsp" onClick=""> Sign Up Here </a>
                </div>
            </div>
        </div>
    </div>
</div>
<div id="result"></div>
<%--<script type="text/javascript" src="assets/js/bootstrap.js"></script>--%>
<script src="webjars/jquery/3.1.1/jquery.js"></script>
<script>
    function postAuthorization(url) {
        window.location.href = '#result';
        $.post(url, {
                    loginOrEmail: $('#loginname').val(),
                    password: $('#password').val()
                },
                function (responseText) {
                    $('#result').html(responseText);
                }
        );
    }
</script>
</body>
</html>
