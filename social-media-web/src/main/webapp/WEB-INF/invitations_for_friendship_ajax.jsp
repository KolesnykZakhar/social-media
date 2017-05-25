<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Invitations For Friendship</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel='stylesheet prefetch' href='../static/css/bootstrap.css'>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container">
    <h2><spring:message code="usersHeader"/></h2>
    <p></p>
    <table class="table" style="width: 623px">
        <tr>
            <th class="col-md-2 col-lg-2"><spring:message code="avatarColumn"/></th>
            <th><spring:message code="loginColumn"/></th>
            <th><spring:message code="nameColumn"/></th>
            <th><spring:message code="statusColumn"/></th>
        </tr>
        <c:forEach items="${requestScope.listInvitations}" var="user" varStatus="index">
            <tr>
                <td class="col-md-2 col-lg-2">
                    <a title="View" onclick="postMainDiv('/user/view_invitation/${user.idUser}', this)">
                        <img role="button" alt="User Pic" src='<c:out value="/user/avatar/${user.idUser}"/>'
                             class="img-circle img-responsive"></a>
                </td>
                <td><c:out value="${user.login}"/></td>
                <td>${user.firstName}<br>${user.lastName}</td>
                <td><c:choose>
                    <c:when test="${user.online}">
                        online
                    </c:when>
                    <c:otherwise>
                        offline
                    </c:otherwise>
                </c:choose>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
