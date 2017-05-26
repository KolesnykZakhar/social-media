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
    <script type="text/javascript" src="../static/js/ajax_post_get.js"></script>
    <script type="text/javascript" src="../static/js/jquery-1.3.2.min.js"></script>
    <title>Registration</title>
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
            <span style="float: right">
                <a href="?lang=ru">RU</a>
                <a href="?lang=en">EN</a>
            </span>
            <form class="form-horizontal" method="post" action="/registration">

                <div class="form-group">
                    <label for="firstName" class="cols-sm-2 control-label"><spring:message
                            code="yourFirstNameLabel"/></label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
                            <input required type="text" class="form-control" name="firstName" id="firstName"
                                   placeholder="<spring:message code="yourFirstNamePlaceholder"/>"/>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label for="lastName" class="cols-sm-2 control-label"><spring:message
                            code="yourLastNameLabel"/></label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
                            <input required type="text" class="form-control" name="lastName" id="lastName"
                                   placeholder="<spring:message code="yourLastNamePlaceholder"/>"/>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label for="email" class="cols-sm-2 control-label"><spring:message code="yourEmailLabel"/></label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-envelope fa" aria-hidden="true"></i></span>
                            <input required type="text" class="form-control" name="email" id="email"
                                   placeholder="<spring:message code="yourEmailPlaceholder"/>"/>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label for="phone" class="cols-sm-2 control-label"><spring:message code="yourPhoneLabel"/></label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-envelope fa" aria-hidden="true"></i></span>
                            <input required type="text" class="form-control" name="phone" id="phone"
                                   placeholder="<spring:message code="yourPhonePlaceholder"/>"/>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label for="birthDate" class="cols-sm-2 control-label"><spring:message
                            code="yourBirthDateLabel"/></label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-envelope fa" aria-hidden="true"></i></span>
                            <input required type="date" class="form-control" name="birthDate" id="birthDate"/>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label for="login" class="cols-sm-2 control-label"><spring:message code="yourLoginLabel"/></label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-users fa" aria-hidden="true"></i></span>
                            <input required type="text" class="form-control" name="login" id="login"
                                   placeholder="<spring:message code="yourLoginPlaceholder"/>"/>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label for="password" class="cols-sm-2 control-label"><spring:message
                            code="yourPasswordLabel"/></label><span class="pull-right" id="passChecker"></span>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span>
                            <input onkeyup="passwordChecker('/checkPass', this)" maxlength="16" minlength="6" required type="password" class="form-control"
                                   name="password" id="password"
                                   placeholder="<spring:message code="yourPasswordPlaceholder"/>"/>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label for="confirm" class="cols-sm-2 control-label"><spring:message
                            code="confirmPasswordLabel"/></label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span>
                            <input required type="password" class="form-control" name="confirm" id="confirm"
                                   placeholder="<spring:message code="confirmPasswordPlaceholder"/>"/>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <div class="cols-sm-10">
                        <div align="center">
                            <label class="radio-inline"><input required type="radio" name="gender"
                                                               value="0"><spring:message code="maleRadio"/></label>
                            <label class="radio-inline"><input required type="radio" name="gender"
                                                               value="1"><spring:message code="femaleRadio"/></label>
                        </div>
                    </div>
                </div>
                <div class="form-group ">
                    <button type="submit" class="btn btn-primary btn-lg btn-block login-button"><spring:message
                            code="registerButton"/></button>
                </div>
                <div class="login-register">
                    <a href="/login"><spring:message code="loginLink"/></a>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
