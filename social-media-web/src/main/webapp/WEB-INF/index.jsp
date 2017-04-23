<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
                <h1><a href="#"><span>Social</span>Net</a></h1>
            </div>
            <div class="search">
                <form method="get" id="search" action="#">
          <span>
          <input type="text" value="Search..." name="s" id="s"/>
          <input name="searchsubmit" type="image" src="../static/images/search.gif" value="Go" id="searchsubmit"
                 class="btn"/>
          </span>
                </form>
                <div class="clr"></div>
            </div>
            <div class="clr"></div>
            <div class="menu_nav">
                <ul>
                    <c:if test="${requestScope.isAdmin}">
                        <li><a href="/admin/index" onclick="">Admin</a></li>
                    </c:if>
                    <li class="active"><a href="/user/index" onclick="">Home</a></li>
                    <li><a href="${pageContext.request.contextPath}/WEB-INF/support.html">Support</a></li>
                    <li><a href="${pageContext.request.contextPath}/WEB-INF/about.html">About Us</a></li>
                    <li><a href="${pageContext.request.contextPath}/WEB-INF/blog.html">Blog</a></li>
                    <li><a href="${pageContext.request.contextPath}/WEB-INF/contact.html">Contact Us</a></li>
                    <li><a href="/logout">Log Out</a></li>
                </ul>
                <div class="clr"></div>
            </div>
            <div class="hbg"><img src="../static/images/header_images.jpg" width="923" height="291" alt=""/></div>
        </div>
        <div class="content">
            <div class="content_bg">
                <div class="mainbar" id="mainDiv">
                    <jsp:include page="photo_slider.jsp"/>
                    <%--<div class="royalSlider rsDefault">--%>
                        <%--<!-- simple image slide -->--%>
                        <%--<img class="rsImg" src="/avatar/1" alt="image desc" />--%>

                        <%--<!-- lazy loaded image slide -->--%>
                        <%--<a class="rsImg" href="/avatar/2">image desc</a>--%>

                        <%--<!-- HTML content slide -->--%>
                        <%--<p>Content goes here</p>--%>

                        <%--<!-- image and content -->--%>
                        <%--<div>--%>
                            <%--<img class="rsImg" src="/avatar/24" data-rsVideo="https://vimeo.com/44878206" />--%>
                            <%--<p>Some content after...</p>--%>
                        <%--</div>--%>

                        <%--<!-- HTML content -->--%>
                        <%--<div>--%>
                            <%--Put any HTML content here--%>
                        <%--</div>--%>

                        <%--<!-- HTML content (100% with and height) -->--%>
                        <%--<div class="rsContent">--%>
                            <%--Put any HTML content here--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<ul class="bxslider" style="width: 300px; height: 300px;">--%>
                        <%--<li><img--%>
                                <%--src='<c:out value="/avatar/1"/>'--%>
                                <%--title="Funky roots"/></li>--%>
                        <%--<li><img--%>
                                <%--src='<c:out value="/avatar/2"/>'--%>
                                <%--title="The long and winding road"/></li>--%>
                        <%--<li><img--%>
                                <%--src='<c:out value="/avatar/24"/>'--%>
                                <%--title="Happy trees"/></li>--%>
                        <%--<li><img--%>
                                <%--src='<c:out value="/avatar/58"/>'--%>
                                <%--title="Happy trees"/></li>--%>
                    <%--</ul>--%>

                    <%--<div class="article">--%>
                    <%--<h2><span>Template License</span></h2>--%>
                    <%--<div class="clr"></div>--%>
                    <%--<p class="post-data"><span class="date">March 16, 2018</span> &nbsp;|&nbsp; Posted by <a--%>
                    <%--href="#">Owner</a> &nbsp;|&nbsp; Filed under <a href="#">templates</a>, <a href="#">internet</a>--%>
                    <%--</p>--%>
                    <%--<img src="../static/images/images_1.jpg" width="613" height="193" alt=""/>--%>
                    <%--<p>This is a free CSS website template by RocketWebsiteTemplates.com. This work is distributed--%>
                    <%--under the <a href="http://creativecommons.org/licenses/by/3.0/">Creative Commons Attribution--%>
                    <%--3.0 License</a>, which means that you are free to use it for any personal or commercial--%>
                    <%--purpose provided you credit me in the form of a link back to RocketWebsiteTemplates.com.</p>--%>
                    <%--<p class="spec"><a href="#" class="com fr">Comments (3)</a> <a href="#" class="rm fl">Read--%>
                    <%--more</a></p>--%>
                    <%--<div class="clr"></div>--%>
                    <%--</div>--%>
                    <%--<div class="article">--%>
                    <%--<h2><span>Lorem Ipsum</span> Dolor Sit</h2>--%>
                    <%--<div class="clr"></div>--%>
                    <%--<p class="post-data"><span class="date">March 15, 2010</span> &nbsp;|&nbsp; Posted by <a--%>
                    <%--href="#">Owner</a> &nbsp;|&nbsp; Filed under <a href="#">templates</a>, <a href="#">internet</a>--%>
                    <%--</p>--%>
                    <%--<img src="../static/images/images_2.jpg" width="613" height="193" alt=""/>--%>
                    <%--<p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Donec libero. Suspendisse bibendum.--%>
                    <%--Cras id urna. <a href="#">Morbi tincidunt, orci ac convallis aliquam, lectus turpis varius--%>
                    <%--lorem, eu posuere nunc justo tempus leo.</a> Donec mattis, purus nec placerat bibendum,--%>
                    <%--dui pede condimentum odio, ac blandit ante orci ut diam. Cras fringilla magna. Phasellus--%>
                    <%--suscipit, leo a pharetra condimentum, lorem tellus eleifend magna, eget fringilla velit--%>
                    <%--magna id neque. Curabitur vel urna. In tristique orci porttitor ipsum. Lorem ipsum dolor sit--%>
                    <%--amet, consectetuer adipiscing elit. Donec libero. Suspendisse bibendum. Cras id urna. Morbi--%>
                    <%--tincidunt, orci ac convallis aliquam.</p>--%>
                    <%--<p>Aenean commodo elit ac ante dignissim iaculis sit amet non velit. Donec magna sapien,--%>
                    <%--molestie sit amet faucibus sit amet, fringilla in urna. Aliquam erat volutpat. Fusce a dui--%>
                    <%--est. Sed in volutpat elit. Nam odio tortor, pulvinar non scelerisque in, eleifend nec nunc.--%>
                    <%--Sed pretium, massa sed dictum dapibus, nibh purus posuere magna, ac porta felis lectus ut--%>
                    <%--neque. Nullam sagittis ante vitae velit facilisis lacinia. Cras vehicula lacinia ornare.--%>
                    <%--Duis et cursus risus. Curabitur consectetur justo sit amet odio viverra vel iaculis odio--%>
                    <%--gravida. Ut imperdiet metus nec erat.</p>--%>
                    <%--<p class="spec"><a href="#" class="com fr">Comments (7)</a> <a href="#" class="rm fl">Read--%>
                    <%--more</a></p>--%>
                    <%--<div class="clr"></div>--%>
                    <%--</div>--%>
                    <%--<div class="pagenavi"><span class="pages">Page 1 of 2</span><span class="current">1</span><a--%>
                    <%--href="#">2</a><a href="#">&raquo;</a></div>--%>
                </div>
                <div class="sidebar">
                    <div class="gadget">
                        <h2 class="star"><span>Sidebar</span> Menu</h2>
                        <div class="clr"></div>
                        <ul id='sb_menu' class="sb_menu">
                            <li id='home' class="active"><a href="#" onclick="postMainDiv('/user/photo_slider', this)">Home</a></li>
                            <li><a id='news' href="#">News</a></li>
                            <li><a id='friendsSb' href="#" onclick="postMainDiv('/user/friends/1', this)">Friends</a>
                            </li>
                            <li><a id='blog' href="#">Blog</a></li>
                            <li><a id='bookmarks' href="#">Bookmarks</a></li>
                            <li><a id='settingsProfile' href="#" onclick="postMainDiv('/user/settings_profile', this)">Settings
                                Profile</a></li>
                        </ul>
                    </div>
                    <div class="gadget">
                        <h2 class="star"><span>Sponsors</span></h2>
                        <div class="clr"></div>
                        <ul class="ex_menu">
                            <li><a href="http://www.dreamtemplate.com">DreamTemplate</a><br/>
                                Over 6,000+ Premium Web Templates
                            </li>
                            <li><a href="http://www.templatesold.com/">TemplateSOLD</a><br/>
                                Premium WordPress &amp; Joomla Themes
                            </li>
                            <li><a href="http://www.imhosted.com">ImHosted.com</a><br/>
                                Affordable Web Hosting Provider
                            </li>
                            <li><a href="http://www.myvectorstore.com">MyVectorStore</a><br/>
                                Royalty Free Stock Icons
                            </li>
                            <li><a href="http://www.evrsoft.com">Evrsoft</a><br/>
                                Website Builder Software &amp; Tools
                            </li>
                            <li><a href="http://www.csshub.com/">CSS Hub</a><br/>
                                Premium CSS Templates
                            </li>
                        </ul>
                    </div>
                    <div class="gadget">
                        <h2 class="star"><span>Wise Words</span></h2>
                        <div class="clr"></div>
                        <div class="testi">
                            <p><span class="q"><img src="../static/images/qoute_1.gif" width="20" height="15"
                                                    alt=""/></span>
                                We can let circumstances rule us, or we can take charge and rule our lives from within.
                                <span class="q"><img src="../static/images/qoute_2.gif" width="20" height="15" alt=""/></span>
                            </p>
                            <p class="title"><strong>Earl Nightingale</strong></p>
                        </div>
                    </div>
                </div>
                <div class="clr"></div>
            </div>
        </div>
    </div>
    <div class="fbg">
        <div class="fbg_resize">
            <div class="col c1">
                <h2><span>Image Gallery</span></h2>
                <a href="#"><img src="../static/images/pic_1.jpg" width="58" height="58" alt=""/></a> <a href="#"><img
                    src="../static/images/pic_2.jpg" width="58" height="58" alt=""/></a> <a href="#"><img
                    src="../static/images/pic_3.jpg" width="58" height="58" alt=""/></a> <a href="#"><img
                    src="../static/images/pic_4.jpg" width="58" height="58" alt=""/></a> <a href="#"><img
                    src="../static/images/pic_5.jpg" width="58" height="58" alt=""/></a> <a href="#"><img
                    src="../static/images/pic_6.jpg" width="58" height="58" alt=""/></a></div>
            <div class="col c2">
                <h2><span>Lorem Ipsum</span></h2>
                <p>Lorem ipsum dolor<br/>
                    Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Donec libero. Suspendisse bibendum. Cras
                    id urna. <a href="#">Morbi tincidunt, orci ac convallis aliquam</a>, lectus turpis varius lorem, eu
                    posuere nunc justo tempus leo. Donec mattis, purus nec placerat bibendum, dui pede condimentum odio,
                    ac blandit ante orci ut diam.</p>
            </div>
            <div class="col c3">
                <h2><span>About</span></h2>
                <p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Donec libero. Suspendisse bibendum. Cras id
                    urna. Morbi tincidunt, orci ac convallis aliquam, lectus turpis varius lorem, eu posuere nunc justo
                    tempus leo. llorem, eu posuere nunc justo tempus leo. Donec mattis, purus nec placerat bibendum. <a
                            href="#">Learn more...</a></p>
            </div>
            <div class="clr"></div>
        </div>
    </div>
</div>
<div class="footer">
    <div class="footer_resize">
        <p class="lf">&copy; Copyright <a href="#">MyWebSite</a>.</p>
        <p class="rf">Layout by Rocket <a href="http://www.rocketwebsitetemplates.com/">Website Templates</a></p>
        <div class="clr"></div>
    </div>
</div>
<div align=center>This template downloaded form <a href='http://all-free-download.com/free-website-templates/'>free
    website templates</a></div>
</body>
<%--<script>--%>
    <%--jQuery(document).ready(function($) {--%>
        <%--$(".royalSlider").royalSlider({--%>
            <%--// options go here--%>
            <%--// as an example, enable keyboard arrows nav--%>
            <%--keyboardNavEnabled: true--%>
        <%--});--%>
    <%--});--%>
<%--</script>--%>
</html>