<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Chats Menu</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel='stylesheet prefetch' href='../static/css/bootstrap.css'>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <h2>Chats Menu</h2>
    <p></p>
    <table class="table" style="width: 623px">
        <tr>
            <th class="col-md-2 col-lg-2">Avatar</th>
            <th>Login</th>
            <th>Last Text</th>
            <th>Status</th>
        </tr>
        <c:forEach items="${requestScope.chatsMenu.chatHeaders}" var="chatHeader" varStatus="index">
            <tr>
                <td class="col-md-2 col-lg-2">
                    <a title="View" onclick="postMainDiv('/user/user/${chatHeader.interlocutor.idUser}', this)">
                        <img role="button" alt="User Pic"
                             src='<c:out value="/user/avatar/${chatHeader.interlocutor.idUser}"/>'
                             class="img-circle img-responsive">
                    </a>
                </td>
                <td><span style="color: red;"><c:out value="${chatHeader.interlocutor.login}"/></span><br>
                        ${chatHeader.interlocutor.firstName}<br>${chatHeader.interlocutor.lastName}</td>
                <td role="button"><a title="View" onclick="postMainDiv('/user/short_chat/${chatHeader.interlocutor.idUser}', this)">
                    <c:out value="${chatHeader.lastMessage.textMessage}"/>
                </a>
                </td>
                <td><c:choose>
                    <c:when test="${chatHeader.interlocutor.online}">
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
