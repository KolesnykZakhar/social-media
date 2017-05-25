<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>SocialNet</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link href="../static/css/style.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="../static/js/jquery-1.3.2.min.js"></script>
    <script type="text/javascript" src="../static/js/script.js"></script>
    <script type="text/javascript" src="../static/js/cufon-yui.js"></script>
    <script type="text/javascript" src="../static/js/arial.js"></script>
    <script type="text/javascript" src="../static/js/cuf_run.js"></script>
    <script type="text/javascript" src="../static/js/ajax_post_get.js"></script>
</head>
<body>
<div class="main">
    <div class="main_resize">
        <div class="header">
            <div class="logo">
                <h1><span>Social</span>Media</h1>
            </div>
            <span class="pull-right">
                <a href="?lang=ru">RU</a>
                <a href="?lang=en">EN</a>
            </span>
            <div class="search">
                <span>
                        <input type="text" value="" name="s" id="s"/>
                        <input type="image" src="../static/images/search.gif" class="btn"
                               onclick="searchUser('/user/search_user/'+$('#s').val()+'/1')"/>
                    </span>
                <div class="clr"></div>
            </div>
            <div class="clr"></div>
            <div class="menu_nav">
                <ul>
                    <c:if test="${requestScope.isAdmin}">
                        <li><a href="/admin/index" onclick=""><spring:message code="adminButton"/></a></li>
                    </c:if>
                    <li><a href="/logout"><spring:message code="logOutButton"/></a></li>
                </ul>
                <div class="clr"></div>
            </div>
            <div class="hbg"><img src="../static/images/header_images.jpg" width="923" height="291" alt=""/></div>
        </div>
        <div class="content">
            <div class="content_bg">
                <div class="mainbar" id="mainDiv">
                    <jsp:include page="image_slider.jsp"/>
                </div>
                <div class="sidebar">
                    <div class="gadget">
                        <h2 class="star"><spring:message code="menuHeader"/></h2>
                        <div class="clr"></div>
                        <ul id='sb_menu' class="sb_menu">
                            <li id='home' class="active"><a href="#"
                                                            onclick="postMainDiv('/user/image_slider', this)"><spring:message
                                    code="homeTab"/></a>
                            </li>
                            <li><a id='news' href="#" onclick="postMainDiv('/user/news/1', this)"><spring:message
                                    code="newsTab"/></a></li>
                            <li><a id='friendsSb' href="#"
                                   onclick="postMainDiv('/user/friends/1', this)"><spring:message
                                    code="friendsTab"/></a>
                            </li>
                            <li><a id='messages' href="#"
                                   onclick="postMainDiv('/user/chats_menu', this)"><spring:message code="messagesTab"/>
                                <span id="amountUnreadMessages" style="color: red"><c:out
                                        value="${requestScope.amountUnreadMessages}"/></span></a></li>
                            <li><a id='blog' href="#"
                                   onclick="postMainDiv('/user/blog_menu/${requestScope.idUser}/1', this)"><spring:message
                                    code="blogTab"/></a></li>
                            <li><a id='bookmarks' href="#"
                                   onclick="postMainDiv('/user/bookmarks/1', this)"><spring:message
                                    code="bookmarksTab"/></a></li>
                            <li><a id='settingsProfile' href="#"
                                   onclick="postMainDiv('/user/settings_profile', this)"><spring:message
                                    code="settingsProfileTab"/></a>
                            </li>
                            <li><a id='invitationsForFriendship' href="#"
                                   onclick="postMainDiv('/user/invitations_for_friendship', this)">
                                <spring:message code="invitationsForFriendshipTab"/><span id="amountOfInvitations"
                                                                                          style="color: red"><c:out
                                    value="${requestScope.amountOfInvitations}"/></span></a></li>
                        </ul>
                    </div>
                </div>
                <div class="clr"></div>
            </div>
        </div>
    </div>
</div>
<div class="footer">
    <div class="footer_resize">
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <div class="clr"></div>
    </div>
</div>
</body>
</html>