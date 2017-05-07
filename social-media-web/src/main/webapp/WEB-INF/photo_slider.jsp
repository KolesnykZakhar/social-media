<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 21.04.2017
  Time: 19:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title></title>
    <link rel='stylesheet prefetch' href='../static/css/bootstrap.css'>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body style="padding:0px; margin:0px; background-color:#fff;font-family:'Open Sans',sans-serif,arial,helvetica,verdana">

<!-- #region Jssor Slider Begin -->
<script src="../static/slider/js/jquery-1.11.3.min.js" type="text/javascript"></script>
<script src="../static/slider/js/jssor.slider-23.1.3.mini.js" type="text/javascript"></script>
<script type="text/javascript">
    jQuery(document).ready(function ($) {

        var jssor_1_SlideshowTransitions = [
            {$Duration: 1200, $Opacity: 2}
        ];

        var jssor_1_options = {
            $AutoPlay: 1,
            $SlideshowOptions: {
                $Class: $JssorSlideshowRunner$,
                $Transitions: jssor_1_SlideshowTransitions,
                $TransitionsOrder: 1
            },
            $ArrowNavigatorOptions: {
                $Class: $JssorArrowNavigator$
            },
            $BulletNavigatorOptions: {
                $Class: $JssorBulletNavigator$
            }
        };

        var jssor_1_slider = new $JssorSlider$("jssor_1", jssor_1_options);

        /*responsive code begin*/
        /*remove responsive code if you don't want the slider scales while window resizing*/
        function ScaleSlider() {
            var refSize = jssor_1_slider.$Elmt.parentNode.clientWidth;
            if (refSize) {
                refSize = Math.min(refSize, 600);
                jssor_1_slider.$ScaleWidth(refSize);
            }
            else {
                window.setTimeout(ScaleSlider, 30);
            }
        }

        ScaleSlider();
        $(window).bind("load", ScaleSlider);
        $(window).bind("resize", ScaleSlider);
        $(window).bind("orientationchange", ScaleSlider);
        /*responsive code end*/
    });
</script>
<style>
    /* jssor slider bullet navigator skin 05 css */
    /*
    .jssorb05 div           (normal)
    .jssorb05 div:hover     (normal mouseover)
    .jssorb05 .av           (active)
    .jssorb05 .av:hover     (active mouseover)
    .jssorb05 .dn           (mousedown)
    */
    .jssorb05 {
        position: absolute;
    }

    .jssorb05 div, .jssorb05 div:hover, .jssorb05 .av {
        position: absolute;
        /* size of bullet elment */
        width: 16px;
        height: 16px;
        background: url('../static/slider/img/b05.png') no-repeat;
        overflow: hidden;
        cursor: pointer;
    }

    .jssorb05 div {
        background-position: -7px -7px;
    }

    .jssorb05 div:hover, .jssorb05 .av:hover {
        background-position: -37px -7px;
    }

    .jssorb05 .av {
        background-position: -67px -7px;
    }

    .jssorb05 .dn, .jssorb05 .dn:hover {
        background-position: -97px -7px;
    }

    /* jssor slider arrow navigator skin 12 css */
    /*
    .jssora12l                  (normal)
    .jssora12r                  (normal)
    .jssora12l:hover            (normal mouseover)
    .jssora12r:hover            (normal mouseover)
    .jssora12l.jssora12ldn      (mousedown)
    .jssora12r.jssora12rdn      (mousedown)
    */
    .jssora12l, .jssora12r {
        display: block;
        position: absolute;
        /* size of arrow element */
        width: 30px;
        height: 46px;
        cursor: pointer;
        background: url('../static/slider/img/a12.png') no-repeat;
        overflow: hidden;
    }

    .jssora12l {
        background-position: -16px -37px;
    }

    .jssora12r {
        background-position: -75px -37px;
    }

    .jssora12l:hover {
        background-position: -136px -37px;
    }

    .jssora12r:hover {
        background-position: -195px -37px;
    }

    .jssora12l.jssora12ldn {
        background-position: -256px -37px;
    }

    .jssora12r.jssora12rdn {
        background-position: -315px -37px;
    }
</style>


<div class="menu_nav">
    <ul>
        <li><a role="button" data-toggle="modal" data-target="#addPhoto">Add Photo</a></li>
        <li><a href="#">Remove Photo</a></li>
    </ul>
    <div class="clr"></div>
</div>


<div class="modal fade" id="addPhoto" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Upload New Photo</h4>
            </div>
            <div class="modal-footer">
                <form action="/user/upload_photo_by_file" method="post"
                      enctype="multipart/form-data">
                    <label class="btn btn-primary">
                        <input name="uploadedPhoto" required type="file" hidden>
                    </label>
                    <button type="submit" class="btn btn-primary">Add
                    </button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close
                    </button>
                </form>
            </div>
        </div>

    </div>
</div>
<div id="jssor_1"
     style="position:relative;margin:0 auto;top:0px;left:0px;width:600px;height:300px;overflow:hidden;visibility:hidden;">
    <!-- Loading Screen -->
    <div data-u="loading" style="position:absolute;top:0px;left:0px;background-color:rgba(0,0,0,0.7);">
        <div style="filter: alpha(opacity=70); opacity: 0.7; position: absolute; display: block; top: 0px; left: 0px; width: 100%; height: 100%;"></div>
        <div style="position:absolute;display:block;background:url('../static/slider/img/loading.gif') no-repeat center center;top:0px;left:0px;width:100%;height:100%;"></div>
    </div>
    <div data-u="slides"
         style="cursor:default;position:relative;top:0px;left:0px;width:600px;height:300px;overflow:hidden;">
        <c:forEach items="${requestScope.photos}" var="urlPhoto">
            <div>
                <img data-u="image" src="${urlPhoto}"/>
            </div>
        </c:forEach>
    </div>
    <!-- Bullet Navigator -->
    <div data-u="navigator" class="jssorb05" style="bottom:16px;right:16px;" data-autocenter="1">
        <!-- bullet navigator item prototype -->
        <div data-u="prototype" style="width:16px;height:16px;"></div>
    </div>
    <!-- Arrow Navigator -->
    <span data-u="arrowleft" class="jssora12l" style="top:0px;left:0px;width:30px;height:46px;"
          data-autocenter="2"></span>
    <span data-u="arrowright" class="jssora12r" style="top:0px;right:0px;width:30px;height:46px;"
          data-autocenter="2"></span>
</div>
<!-- #endregion Jssor Slider End -->
</body>
<%--<script>--%>
    <%--$('#img').hover(function() {--%>
        <%--$(this).find('.delete').show();--%>
    <%--}, function() {--%>
        <%--$(this).find('.delete').hide();--%>
    <%--});--%>
<%--</script>--%>
</html>