<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Bootstrap Example</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel='stylesheet prefetch' href='../static/css/bootstrap.css'>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container">
    <h2>Friends</h2>
    <p></p>
    <table class="table" style="width: 623px">
        <tr>
            <th class="col-md-2 col-lg-2">Avatar</th>
            <th>Login</th>
            <th>Name</th>
            <th>Email</th>
        </tr>
        <c:forEach items="${requestScope.friends}" var="friend" varStatus="index">
            <tr>
                <td class="col-md-2 col-lg-2">
                    <a title="View" onclick="postMainDiv('/user/friend/${friend.idUser}', this)">
                        <img role="button" alt="User Pic" src='<c:out value="/avatar/${friend.idUser}"/>'
                             class="img-circle img-responsive"></a>
                </td>
                <td><c:out value="${friend.login}"/></td>
                <td>${friend.firstName}<br>${friend.lastName}</td>
                <td><c:out value="${friend.email}"/></td>
            </tr>
        </c:forEach>
    </table>
    <ul style="list-style: none; display: inline;">
        <c:forEach var="i" begin="1" end="${requestScope.maxPage}">
            <li style="display: inline;"><a href="#" onclick="postMainDiv('/user/friends/${i}', this)">${i}&nbsp;</a>
            </li>
        </c:forEach>
    </ul>
</div>
