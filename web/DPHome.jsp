<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>

        <title>汽车产业信息化系统</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/pintuer.css">
        <script src="js/jquery.js"></script>
        <script src="js/pintuer.js"></script>

        <link href="css/bootstrap.css" rel='stylesheet' type='text/css' />
        <link href="css/style.css" rel='stylesheet' type='text/css' />
    </head>
    <body>
        <!--Header-->
        <div class="header" id="home">
            <!--top-bar-w3-agile-->
            <%@include file="WEB-INF/jspf/homer.jspf" %>
        </div>
        <!--//top-bar-w3-agile-->
        <!-- banner-text -->
        <div class="slider" id="top">
            <div class="callbacks_container">
                <ul class="rslides callbacks callbacks1" id="slider4">
                    <li>
                        <div class="banner-top">
                            <div class="banner-info_agileits_w3ls">
                                <h3>东浦实训系统</h3>
                                <p>- 完备详细的汽车和产品信息</p>
                                <a href="DPLogin.jsp">立即登录<i class="fa fa-caret-right" aria-hidden="true"></i></a>
                                <a href="#">合作联系<i class="fa fa-caret-right" aria-hidden="true"></i></a>
                                <br>
                                <a>企业负责人 注册入住系统<i class="fa fa-caret-right" aria-hidden="true"></i></a>
                            </div>
                        </div>
                    </li>
                </ul>
            </div>
            <div class="clearfix"> </div>
        </div>
        <!--//Slider-->
</div>


<!-- //projects -->

<!--首页面尾部开始-->
<%@include file="WEB-INF/jspf/footer.jspf" %>
<!--首页面尾部结束-->

</body>

</html>