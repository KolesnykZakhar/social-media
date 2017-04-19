<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 24.03.2017
  Time: 14:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Simple Responsive Admin</title>
    <!-- BOOTSTRAP STYLES-->
    <link href="/static/css/bootstrap.css" rel="stylesheet" />
    <!-- FONTAWESOME STYLES-->
    <link href="/static/css/font-awesome.css" rel="stylesheet" />
    <!-- CUSTOM STYLES-->
    <link href="/static/css/custom.css" rel="stylesheet" />
    <!-- GOOGLE FONTS-->
    <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css' />
</head>
<body>



<div id="wrapper">
    <div class="navbar navbar-inverse navbar-fixed-top">
        <div class="adjust-nav">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".sidebar-collapse">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">
                    <img src="/static/images/logo.png" />

                </a>

            </div>

            <span class="logout-spn" >
                  <a href="/logout" style="color:#fff;">LOGOUT</a>
            </span>
        </div>
    </div>
    <!-- /. NAV TOP  -->
    <nav class="navbar-default navbar-side" role="navigation">
        <div class="sidebar-collapse">
            <ul class="nav" id="main-menu">



                <li class="active-link">
                    <a href="index.html" ><i class="fa fa-desktop "></i>Dashboard <span class="badge">Included</span></a>
                </li>


                <li>
                    <a href="ui.html"><i class="fa fa-table "></i>UI Elements  <span class="badge">Included</span></a>
                </li>
                <li>
                    <a href="blank.html"><i class="fa fa-edit "></i>Blank Page  <span class="badge">Included</span></a>
                </li>


                <li>
                    <a href="#"><i class="fa fa-qrcode "></i>My Link One</a>
                </li>
                <li>
                    <a href="#"><i class="fa fa-bar-chart-o"></i>My Link Two</a>
                </li>

                <li>
                    <a href="#"><i class="fa fa-edit "></i>My Link Three </a>
                </li>
                <li>
                    <a href="#"><i class="fa fa-table "></i>My Link Four</a>
                </li>
                <li>
                    <a href="/user/index"><i class="fa fa-edit "></i>To User Account</a>
                </li>

            </ul>
        </div>

    </nav>
    <!-- /. NAV SIDE  -->
    <div id="page-wrapper" >
        <div id="page-inner">
            <div class="row">
                <div class="col-lg-12">
                    <h2>ADMIN DASHBOARD</h2>
                </div>
            </div>
            <!-- /. ROW  -->
            <hr />
            <div class="row">
                <div class="col-lg-12 ">
                    <div class="alert alert-info">
                        <strong>Welcome Jhon Doe ! </strong> You Have No pending Task For Today.
                    </div>

                </div>
            </div>
            <!-- /. ROW  -->
            <div class="row text-center pad-top">
                <div class="col-lg-2 col-md-2 col-sm-2 col-xs-6">
                    <div class="div-square">
                        <a href="blank.html" >
                            <i class="fa fa-circle-o-notch fa-5x"></i>
                            <h4>Check Data</h4>
                        </a>
                    </div>


                </div>

                <div class="col-lg-2 col-md-2 col-sm-2 col-xs-6">
                    <div class="div-square">
                        <a href="blank.html" >
                            <i class="fa fa-envelope-o fa-5x"></i>
                            <h4>Mail Box</h4>
                        </a>
                    </div>


                </div>
                <div class="col-lg-2 col-md-2 col-sm-2 col-xs-6">
                    <div class="div-square">
                        <a href="blank.html" >
                            <i class="fa fa-lightbulb-o fa-5x"></i>
                            <h4>New Issues</h4>
                        </a>
                    </div>


                </div>
                <div class="col-lg-2 col-md-2 col-sm-2 col-xs-6">
                    <div class="div-square">
                        <a href="blank.html" >
                            <i class="fa fa-users fa-5x"></i>
                            <h4>See Users</h4>
                        </a>
                    </div>


                </div>
                <div class="col-lg-2 col-md-2 col-sm-2 col-xs-6">
                    <div class="div-square">
                        <a href="blank.html" >
                            <i class="fa fa-key fa-5x"></i>
                            <h4>Admin </h4>
                        </a>
                    </div>


                </div>
                <div class="col-lg-2 col-md-2 col-sm-2 col-xs-6">
                    <div class="div-square">
                        <a href="blank.html" >
                            <i class="fa fa-comments-o fa-5x"></i>
                            <h4>Support</h4>
                        </a>
                    </div>


                </div>
            </div>
            <!-- /. ROW  -->
            <div class="row text-center pad-top">

                <div class="col-lg-2 col-md-2 col-sm-2 col-xs-6">
                    <div class="div-square">
                        <a href="blank.html" >
                            <i class="fa fa-clipboard fa-5x"></i>
                            <h4>All Docs</h4>
                        </a>
                    </div>


                </div>
                <div class="col-lg-2 col-md-2 col-sm-2 col-xs-6">
                    <div class="div-square">
                        <a href="blank.html" >
                            <i class="fa fa-gear fa-5x"></i>
                            <h4>Settings</h4>
                        </a>
                    </div>


                </div>
                <div class="col-lg-2 col-md-2 col-sm-2 col-xs-6">
                    <div class="div-square">
                        <a href="blank.html" >
                            <i class="fa fa-wechat fa-5x"></i>
                            <h4>Live Talk</h4>
                        </a>
                    </div>


                </div>
                <div class="col-lg-2 col-md-2 col-sm-2 col-xs-6">
                    <div class="div-square">
                        <a href="blank.html" >
                            <i class="fa fa-bell-o fa-5x"></i>
                            <h4>Notifications </h4>
                        </a>
                    </div>


                </div>
                <div class="col-lg-2 col-md-2 col-sm-2 col-xs-6">
                    <div class="div-square">
                        <a href="blank.html" >
                            <i class="fa fa-rocket fa-5x"></i>
                            <h4>Launch</h4>
                        </a>
                    </div>


                </div>
                <div class="col-lg-2 col-md-2 col-sm-2 col-xs-6">
                    <div class="div-square">
                        <a href="blank.html" >
                            <i class="fa fa-user fa-5x"></i>
                            <h4>Register User</h4>
                        </a>
                    </div>


                </div>
            </div>
            <!-- /. ROW  -->
            <div class="row text-center pad-top">


                <div class="col-lg-2 col-md-2 col-sm-2 col-xs-6">
                    <div class="div-square">
                        <a href="blank.html" >
                            <i class="fa fa-envelope-o fa-5x"></i>
                            <h4>Mail Box</h4>
                        </a>
                    </div>


                </div>
                <div class="col-lg-2 col-md-2 col-sm-2 col-xs-6">
                    <div class="div-square">
                        <a href="blank.html" >
                            <i class="fa fa-lightbulb-o fa-5x"></i>
                            <h4>New Issues</h4>
                        </a>
                    </div>


                </div>
                <div class="col-lg-2 col-md-2 col-sm-2 col-xs-6">
                    <div class="div-square">
                        <a href="blank.html" >
                            <i class="fa fa-users fa-5x"></i>
                            <h4>See Users</h4>
                        </a>
                    </div>


                </div>
                <div class="col-lg-2 col-md-2 col-sm-2 col-xs-6">
                    <div class="div-square">
                        <a href="blank.html" >
                            <i class="fa fa-key fa-5x"></i>
                            <h4>Admin </h4>
                        </a>
                    </div>


                </div>
                <div class="col-lg-2 col-md-2 col-sm-2 col-xs-6">
                    <div class="div-square">
                        <a href="#" >
                            <i class="fa fa-comments-o fa-5x"></i>
                            <h4>Support</h4>
                        </a>
                    </div>


                </div>
                <div class="col-lg-2 col-md-2 col-sm-2 col-xs-6">
                    <div class="div-square">
                        <a href="blank.html" >
                            <i class="fa fa-circle-o-notch fa-5x"></i>
                            <h4>Check Data</h4>
                        </a>
                    </div>


                </div>
            </div>
            <!-- /. ROW  -->
            <div class="row text-center pad-top">
                <div class="col-lg-2 col-md-2 col-sm-2 col-xs-6">
                    <div class="div-square">
                        <a href="blank.html" >
                            <i class="fa fa-rocket fa-5x"></i>
                            <h4>Launch</h4>
                        </a>
                    </div>


                </div>
                <div class="col-lg-2 col-md-2 col-sm-2 col-xs-6">
                    <div class="div-square">
                        <a href="blank.html" >
                            <i class="fa fa-clipboard fa-5x"></i>
                            <h4>All Docs</h4>
                        </a>
                    </div>


                </div>
                <div class="col-lg-2 col-md-2 col-sm-2 col-xs-6">
                    <div class="div-square">
                        <a href="blank.html" >
                            <i class="fa fa-gear fa-5x"></i>
                            <h4>Settings</h4>
                        </a>
                    </div>


                </div>
                <div class="col-lg-2 col-md-2 col-sm-2 col-xs-6">
                    <div class="div-square">
                        <a href="blank.html" >
                            <i class="fa fa-wechat fa-5x"></i>
                            <h4>Live Talk</h4>
                        </a>
                    </div>


                </div>
                <div class="col-lg-2 col-md-2 col-sm-2 col-xs-6">
                    <div class="div-square">
                        <a href="blank.html" >
                            <i class="fa fa-bell-o fa-5x"></i>
                            <h4>Notifications </h4>
                        </a>
                    </div>


                </div>

                <div class="col-lg-2 col-md-2 col-sm-2 col-xs-6">
                    <div class="div-square">
                        <a href="blank.html" >
                            <i class="fa fa-user fa-5x"></i>
                            <h4>Register User</h4>
                        </a>
                    </div>


                </div>
            </div>
            <!-- /. ROW  -->
            <div class="row text-center pad-top">



                <div class="col-lg-2 col-md-2 col-sm-2 col-xs-6">
                    <div class="div-square">
                        <a href="blank.html" >
                            <i class="fa fa-lightbulb-o fa-5x"></i>
                            <h4>New Issues</h4>
                        </a>
                    </div>


                </div>
                <div class="col-lg-2 col-md-2 col-sm-2 col-xs-6">
                    <div class="div-square">
                        <a href="blank.html" >
                            <i class="fa fa-users fa-5x"></i>
                            <h4>See Users</h4>
                        </a>
                    </div>


                </div>
                <div class="col-lg-2 col-md-2 col-sm-2 col-xs-6">
                    <div class="div-square">
                        <a href="blank.html" >
                            <i class="fa fa-key fa-5x"></i>
                            <h4>Admin </h4>
                        </a>
                    </div>


                </div>
                <div class="col-lg-2 col-md-2 col-sm-2 col-xs-6">
                    <div class="div-square">
                        <a href="blank.html" >
                            <i class="fa fa-comments-o fa-5x"></i>
                            <h4>Support</h4>
                        </a>
                    </div>


                </div>
                <div class="col-lg-2 col-md-2 col-sm-2 col-xs-6">
                    <div class="div-square">
                        <a href="blank.html" >
                            <i class="fa fa-circle-o-notch fa-5x"></i>
                            <h4>Check Data</h4>
                        </a>
                    </div>


                </div>
                <div class="col-lg-2 col-md-2 col-sm-2 col-xs-6">
                    <div class="div-square">
                        <a href="blank.html" >
                            <i class="fa fa-envelope-o fa-5x"></i>
                            <h4>Mail Box</h4>
                        </a>
                    </div>


                </div>
            </div>
            <!-- /. ROW  -->
            <div class="row">
                <div class="col-lg-12 ">
                    <br/>
                    <div class="alert alert-danger">
                        <strong>Want More Icons Free ? </strong> Checkout fontawesome website and use any icon <a target="_blank" href="http://fortawesome.github.io/Font-Awesome/icons/">Click Here</a>.
                    </div>

                </div>
            </div>
            <!-- /. ROW  -->
        </div>
        <!-- /. PAGE INNER  -->
    </div>
    <!-- /. PAGE WRAPPER  -->
</div>
<div class="footer">


    <div class="row">
        <div class="col-lg-12" >
            &copy;  2014 yourdomain.com | Design by: <a href="http://binarytheme.com" style="color:#fff;" target="_blank">www.binarytheme.com</a>
        </div>
    </div>
</div>


<!-- /. WRAPPER  -->
<!-- SCRIPTS -AT THE BOTOM TO REDUCE THE LOAD TIME-->
<!-- JQUERY SCRIPTS -->
<script src="/static/js/jquery-1.10.2.js"></script>
<!-- BOOTSTRAP SCRIPTS -->
<script src="/static/js/bootstrap.min.js"></script>
<!-- CUSTOM SCRIPTS -->
<script src="/static/js/custom.js"></script>


</body>
</html>

