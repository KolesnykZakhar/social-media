<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Bootstrap Example</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel='stylesheet prefetch' href='../static/css/bootstrap.min.css'>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container">
    <h1><c:out value="${requestScope.friend.login}"/></h1>
    <div class="row">
        <div style="width: 623px"
             class="col-xs-12 col-sm-12 col-md-6 col-lg-6 toppad">


            <div class="panel panel-info">
                <div class="panel-heading">
                    <h3 class="panel-title"><c:out
                            value="${requestScope.friend.firstName} ${requestScope.friend.lastName}"/></h3>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-md-3 col-lg-3 " align="center"><img alt="User Pic"
                                                                            src="http://babyinfoforyou.com/wp-content/uploads/2014/10/avatar-300x300.png"
                                                                            class="img-circle img-responsive"></div>
                        <div class=" col-md-9 col-lg-9 ">
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
                                    <td>Date of Birth</td>
                                    <td><c:out value="${requestScope.friend.birthDate}"/></td>
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
                                    <td><a href="mailto:<c:out value='${requestScope.friend.email}'/>"><c:out
                                            value="${requestScope.friend.email}"/></a></td>
                                </tr>
                                <td>Phone Number</td>
                                <td><c:out value='${requestScope.friend.phone}'/></td>

                                </tr>

                                </tbody>
                            </table>

                            <a href="#" class="btn btn-primary">My Sales Performance</a>
                            <a href="#" class="btn btn-primary">Team Sales Performance</a>
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
