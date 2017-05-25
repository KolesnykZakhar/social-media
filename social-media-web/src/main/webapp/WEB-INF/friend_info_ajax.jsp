<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Friend Info</title>
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
                    <c:choose>
                        <c:when test="${requestScope.friend.online}">
                            <spring:message code="onlineValue"/>
                        </c:when>
                        <c:otherwise>
                            <spring:message code="offlineValue"/>
                        </c:otherwise>
                    </c:choose>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-md-3 col-lg-3 " align="center">
                            <img alt="User Pic" src='<c:out value="/user/avatar/${requestScope.friend.idUser}"/>'
                                 class="img-circle img-responsive">
                        </div>
                        <div class=" col-md-9 col-lg-9 ">
                            <table class="table table-user-information">
                                <tbody>
                                <tr>
                                    <td><label><spring:message code="firstNameColumn"/></label></td>
                                    <td><c:out value="${requestScope.friend.firstName}"/></td>
                                </tr>
                                <tr>
                                    <td><label><spring:message code="lastNameColumn"/></label></td>
                                    <td><c:out value="${requestScope.friend.lastName}"/></td>
                                </tr>
                                <tr>
                                    <td><label><spring:message code="phoneNumberColumn"/></label></td>
                                    <td><c:out value="${requestScope.friend.phone}"/></td>
                                </tr>
                                <tr>
                                    <td><label><spring:message code="birthDateColumn"/></label></td>
                                    <td><c:out value="${requestScope.friend.birthDate}"/></td>
                                </tr>
                                <tr>
                                    <td><label><spring:message code="genderColumn"/></label></td>
                                    <td>
                                        <div class="form-group">
                                            <div class="cols-sm-10">
                                                <spring:message code="${requestScope.friend.gender=='MALE'?'maleLabel':'femaleLabel'}"/>
                                            </div>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td><label><spring:message code="emailColumn"/></label></td>
                                    <td><a href="mailto:<c:out value='${requestScope.friend.email}'/>">
                                        <c:out value="${requestScope.friend.email}"/></a></td>
                                </tr>
                                <tr>
                                    <td><label><spring:message code="loginColumn"/></label></td>
                                    <td><c:out value='${requestScope.friend.login}'/></td>
                                </tr>
                                </tbody>
                            </table>
                            <a href="#"
                               onclick="addRemoveAcceptFriendship('/user/remove_from_friends/${requestScope.friend.idUser}')"
                               class="btn btn-primary"><spring:message code="removeFromFriendsButton"/></a>
                        </div>
                    </div>
                </div>
                <div class="panel-footer">
                    <a onclick="postMainDiv('/user/short_chat/${requestScope.friend.idUser}/${true}')" data-original-title="Broadcast Message" data-toggle="tooltip" type="button"
                       class="btn btn-sm btn-primary"><i class="glyphicon glyphicon-envelope"></i></a>
                    <span class="pull-right">
                            <a href="#" type="button" class="btn btn-sm btn-default" onclick="postMainDiv('/user/blog_menu/${requestScope.friend.idUser}/1/')"><spring:message code="viewBlogButton"/></a>
                            <a onclick="postMainDiv('/user/image_slider')" data-original-title="Remove this user"
                               data-toggle="tooltip" type="button"
                               class="btn btn-sm btn-danger"><i class="glyphicon glyphicon-remove"></i></a>
                        </span>
                </div>

            </div>
        </div>
    </div>
</div>
</body>
</html>
