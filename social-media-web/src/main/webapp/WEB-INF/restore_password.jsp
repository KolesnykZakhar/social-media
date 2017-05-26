<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel='stylesheet prefetch' href='../static/css/bootstrap.css'>
    <link rel="stylesheet" type="text/css" href="../static/css/registration.css">
    <link href='https://fonts.googleapis.com/css?family=Passion+One' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Oxygen' rel='stylesheet' type='text/css'>
    <title>Login</title>
</head>
<body>
<div class="container">
    <div class="row main">
        <div class="panel-heading">
            <div class="panel-title text-center">
                <h1 class="title">Social Media Network</h1>
                <hr/>
            </div>
        </div>
        <div class="main-login main-center">
            <form class="form-horizontal" method="post" action="/restore_password">
                <div class="form-group">
                    <label for="email" class="cols-sm-2 control-label"><spring:message code="yourEmailLabel"/></label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-users fa" aria-hidden="true"></i></span>
                            <input required type="text" class="form-control" name="email" id="email"
                                   placeholder="<spring:message code="yourEmailPlaceholder"/>"/>
                        </div>
                    </div>
                </div>
                <div class="form-group ">
                    <button type="submit" class="btn btn-primary btn-lg btn-block login-button"><spring:message code="restorePasswordButton"/>
                    </button>
                </div>
                <div class="login-register">
                    <a href="/login"><spring:message code="loginLink"/></a>
                </div>
                <div class="login-register">
                    <a href="/registration"><spring:message code="registrationLink"/></a>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
