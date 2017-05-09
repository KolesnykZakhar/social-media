<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Chat Widget</title>
    <link rel="stylesheet" href="../static/chat/css/reset.css">
    <link rel='stylesheet prefetch' href='https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css'>
    <link rel="stylesheet" href="../static/chat/css/style.css">
    <link rel='stylesheet prefetch' href='../static/css/bootstrap.css'>
</head>
<body>
<div class="chat">
    <div class="chat-header clearfix row">
        <div class="col-md-2 col-lg-2 " align="center">
            <a title="View" onclick="postMainDiv('/user/user/${requestScope.chat.interlocutor.idUser}', this)">
                <img role="button" alt="User Pic"
                     src='<c:out value="/user/avatar/${requestScope.chat.interlocutor.idUser}"/>'
                     class="img-circle img-responsive"></a>
        </div>
        <div class="col-md-8 col-lg-8 " align="center">
            <div class="chat-about">
                <div class="chat-with">Chat with <c:out value="${requestScope.chat.interlocutor.firstName}"/> <c:out
                        value="${requestScope.chat.interlocutor.lastName}"/></div>
                <div class="chat-num-messages">already <c:out value="${requestScope.chat.amountMessages}"/> messages
                </div>
            </div>
        </div>
        <i role="button" title="Switch To Full Chat"
           onclick="postMainDiv('/user/full_chat/${requestScope.chat.interlocutor.idUser}')"
           class="glyphicon glyphicon-th"></i></div> <!-- end message-header -->
    <div class="chat-history">
        <ul>
            <c:forEach items="${requestScope.chat.messages}" var="message" varStatus="index">
                <c:choose>
                    <c:when test="${message.idUser eq requestScope.chat.idUser}">
                        <li class="clearfix">
                            <div class="message other-message float-right">
                                <c:out value="${message.textMessage}"/>
                            </div>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li>
                            <div class="message my-message">
                                <c:out value="${message.textMessage}"/>
                            </div>
                        </li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </ul>
    </div>
    <!-- end message-history -->
    <div class="chat-message clearfix">
        <textarea name="message-to-send" id="message-to-send" placeholder="Type your message" rows="3"></textarea>
        <input id="idInterlocutor" hidden value="${requestScope.chat.interlocutor.idUser}"/>
        <input id="idUser" hidden value="${requestScope.chat.idUser}"/>
        <button onclick="sendMessage('/user/short_chat/send_message')">Send</button>
    </div> <!-- end message-message -->
</div> <!-- end message -->
<!-- end container -->--%>
<script id="message-template" type="text/x-handlebars-template">
    <li class="clearfix">
        <div class="message-data align-right">
            <span class="message-data-time">{{time}}, Today</span> &nbsp; &nbsp;
            <span class="message-data-name">Olia</span> <i class="fa fa-circle me"></i>
        </div>
        <div class="message other-message float-right">
            {{messageOutput}}
        </div>
    </li>
</script>

<script id="message-response-template" type="text/x-handlebars-template">
    <li>
        <div class="message-data">
            <span class="message-data-name"><i class="fa fa-circle online"></i> Vincent</span>
            <span class="message-data-time">{{time}}, Today</span>
        </div>
        <div class="message my-message">
            {{response}}
        </div>
    </li>
</script>
<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
<script src='http://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.0/handlebars.min.js'></script>
<script src='http://cdnjs.cloudflare.com/ajax/libs/list.js/1.1.1/list.min.js'></script>

<script src="../static/chat/js/index.js"></script>


</body>
</html>
