<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
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
    <h2><spring:message code="chatsMenuHeader"/></h2>
    <p></p>
    <table class="table" style="width: 623px">
        <tr>
            <th class="col-md-2 col-lg-2"><spring:message code="avatarColumn"/></th>
            <th><spring:message code="loginColumn"/></th>
            <th><spring:message code="lastMessageColumn"/></th>
            <th><spring:message code="statusColumn"/></th>
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
                <td role="button"><a title="View"
                                     onclick="postMainDiv('/user/short_chat/${chatHeader.interlocutor.idUser}/${chatHeader.hasUnread()}', this)">
                    <c:out value="${chatHeader.lastMessage.textMessage}"/>
                    <c:choose>
                        <c:when test="${chatHeader.hasUnread()}">
                            <br><span id="amountUnreadChatHeader" style="color: red"><c:out
                                value="${chatHeader.amountUnread}"/></span>
                        </c:when>
                    </c:choose>
                </a>
                </td>
                <td><c:choose>
                    <c:when test="${chatHeader.interlocutor.online}">
                        <spring:message code="onlineValue"/>
                    </c:when>
                    <c:otherwise>
                        <spring:message code="offlineValue"/>
                    </c:otherwise>
                </c:choose>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
