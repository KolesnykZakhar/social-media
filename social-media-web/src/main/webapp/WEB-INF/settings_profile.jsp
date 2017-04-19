<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Settings Profile</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel='stylesheet prefetch' href='../static/css/bootstrap.min.css'>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container">
    <h1>Settings Profile</h1>
    <div class="row">
        <div style="width: 623px"
             class="col-xs-12 col-sm-12 col-md-6 col-lg-6 toppad">


            <div class="panel panel-info">
                <div class="panel-heading">
                    <h3 class="panel-title"><c:out
                            value="${requestScope.user.firstName} ${requestScope.user.lastName}"/></h3>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-md-3 col-lg-3 " align="center"><img alt="User Pic"
                                                                            src="http://babyinfoforyou.com/wp-content/uploads/2014/10/avatar-300x300.png"
                                                                            class="img-circle img-responsive"></div>
                        <div class=" col-md-9 col-lg-9 ">
                            <form <%--action="/update_user_info" id="userInfoForm"--%>>
                                <table class="table table-user-information">

                                    <tbody>
                                    <tr>
                                        <td>Department:</td>
                                        <td>Programming</td>
                                    </tr>
                                    <tr>
                                        <td>Hire date:</td>
                                        <td>06/23/2013</td>
                                    </tr>
                                    <tr>
                                        <td><label for="birthDate">Date of Birth</label></td>
                                        <%--<td><c:out value="${requestScope.user.birthDate.toLocalDateTime().toLocalDate()}"/></td>--%>
                                        <td><input id="birthDate" required type="date" name="birthDate"
                                                   value="${requestScope.user.birthDate}"/>
                                        </td>
                                    </tr>

                                    <tr>
                                    <tr>
                                        <td>Gender</td>
                                        <td>Female</td>
                                    </tr>
                                    <tr>
                                        <td>Home Address</td>
                                        <td>Kathmandu,Nepal</td>
                                    </tr>
                                    <tr>
                                        <td>Email</td>
                                        <td><a href="mailto:<c:out value='${requestScope.user.email}'/>"><c:out
                                                value="${requestScope.user.email}"/></a></td>
                                    </tr>
                                    <td>Phone Number</td>
                                    <td><c:out value='${requestScope.user.phone}'/></td>

                                    </tr>

                                    </tbody>

                                </table>

                                <%--<a href="#" onclick="document.getElementById('userInfoForm').submit(); return false;" class="btn btn-primary">Save changes</a>--%>
                                <a href="#" onclick="updateUser('/user/update_user_info/', this)"
                                   class="btn btn-primary">Save changes</a>
                                <a href="/user/index" class="btn btn-primary">Cancel</a>
                            </form>
                        </div>
                    </div>
                </div>
                <div class="panel-footer">
                    <a data-original-title="Broadcast Message" data-toggle="tooltip" type="button"
                       class="btn btn-sm btn-primary"><i class="glyphicon glyphicon-envelope"></i></a>
                    <span class="pull-right">
                            <a href="edit.html" data-original-title="Edit this user" data-toggle="tooltip" type="button"
                               class="btn btn-sm btn-warning"><i class="glyphicon glyphicon-edit"></i></a>
                            <a data-original-title="Remove this user" data-toggle="tooltip" type="button"
                               class="btn btn-sm btn-danger"><i class="glyphicon glyphicon-remove"></i></a>
                        </span>
                </div>

            </div>
        </div>
    </div>
</div>
