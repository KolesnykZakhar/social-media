<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Bootstrap Example</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel='stylesheet prefetch' href='css/bootstrap.min.css'>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container">
    <h2>Friends</h2>
    <p></p>
    <table class="table" style="width: 623px">
        <thead>
        <tr>
            <th>Login</th>
            <th>Name</th>
            <th>Email</th>
            <th>Phone</th>
            <th></th>
        </tr>
        </thead>
        <c:forEach items="${requestScope.friends}" var="friend" varStatus="index">
            <tbody>
            <tr>
                <td><c:out value="${friend.login}"/></td>
                <td>${friend.firstName}<br>${friend.lastName}</td>
                <td><c:out value="${friend.email}"/></td>
                <td><c:out value="${friend.phone}"/></td>
                <td><a href="#" class="btn btn-primary" onclick="getUser('/index/friend', '${friend.idUser}')">View</a>
                </td>
            </tr>
            </tbody>
        </c:forEach>
    </table>
    <ul style="list-style: none; display: inline;">
        <c:forEach var="i" begin="1" end="${requestScope.maxPage}">
            <li style="display: inline;"><a href="#"
                                            onclick="postMainDiv('/index/friends', this, ${i})">${i}&nbsp;</a>
            </li>
        </c:forEach>
    </ul>
</div>
