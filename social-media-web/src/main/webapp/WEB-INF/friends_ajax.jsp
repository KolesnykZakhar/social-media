<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Friends</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel='stylesheet prefetch' href='../static/css/bootstrap.css'>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container">
    <h2><spring:message code="friendsHeader"/></h2>
    <p></p>
    <table class="table" style="width: 623px">
        <tr>
            <th class="col-md-2 col-lg-2"><spring:message code="avatarColumn"/></th>
            <th><spring:message code="loginColumn"/></th>
            <th><spring:message code="nameColumn"/></th>
            <th><spring:message code="emailColumn"/></th>
            <th><spring:message code="statusColumn"/></th>
        </tr>
        <c:forEach items="${requestScope.usersPage.page}" var="user" varStatus="index">
            <tr>
                <td class="col-md-2 col-lg-2">
                    <a title="View" onclick="postMainDiv('/user/friend/${user.idUser}', this)">
                        <img role="button" alt="User Pic" src='<c:out value="/user/avatar/${user.idUser}"/>'
                             class="img-circle img-responsive"></a>
                </td>
                <td><c:out value="${user.login}"/></td>
                <td>${user.firstName}<br>${user.lastName}</td>
                <td><a href="mailto:<c:out value='${user.email}'/>"><c:out value="${user.email}"/></a></td>
                <td><c:choose>
                    <c:when test="${user.online}">
                        <spring:message code="onlineValue"/>
                    </c:when>
                    <c:otherwise>
                        <spring:message code="offlineValue"/>
                    </c:otherwise>
                </c:choose></td>
            </tr>
        </c:forEach>
    </table>
    <ul style="list-style: none; display: inline;">
        <c:forEach var="i" begin="1" end="${requestScope.usersPage.amountPages}">
            <li style="display: inline;"><a href="#" onclick="postMainDiv('/user/friends/${i}', this)">${i}&nbsp;</a>
            </li>
        </c:forEach>
    </ul>
</div>
</body>
</html>
