<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="zh-cn">
    <head>
        <title></title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/pintuer.css">
        <script src="js/jquery.js"></script>
        <script src="js/pintuer.js"></script>
        <link href="css/bootstrap.css" rel='stylesheet' type='text/css' />
        <link href="css/style.css" rel='stylesheet' type='text/css' />
        <script type="text/javascript">
            window.onload = function () {
                //1、获取表格
                var tab = document.getElementById("tal");
                //2、获取表格中tbody的行数
                var len = tab.tBodies[0].rows.length;
//                alert(len)
                //3、开始循环，设置颜色
                for (var i = 0; i < len; i++) {
                    if (i % 2 === 0) {
                        tab.tBodies[0].rows[i].style.backgroundColor = "write";
                    } else {
                        tab.tBodies[0].rows[i].style.backgroundColor = "#efefef";
                    }
                }
            };
        </script>
    </head>
    <body>
        <!--首页面头部开始-->
        <%@include file="WEB-INF/jspf/homer.jspf" %>
        <!--首页面头部结束-->
        <!--首页面内容开始-->
        <div class="container padding-big-top padding-big-bottom">
            <div class="line-big">
                <div class="xl12 xs9 xm9 xb9">
                    <c:if test="${empty sups}"><
                        div class="text-large text-center margin-big-bottom">暂无任何信息！</div>
                    </c:if>
                    <c:if test="${!empty sups}">
                    <div class="text-large text-center margin-big-bottom">零部件供应商</div>
                    <table class="table table-hover table-bordered" id="tal">
                        <tr style="background-color: #cacaca ">
                            <td>供应商编号</td><td>供应商名称</td>
                        </tr>
                        <c:forEach var="sup" items="${sups}">
                            <tr>
                                <td>${sup.getSupId()}</td>
                                <td>${sup.getSupName()}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:if>
            </div>
        </div>

        <!--首页面内容结束-->
        <!--首页面尾部开始-->
        <%@include file="WEB-INF/jspf/footer.jspf" %>
        <!--首页面尾部结束-->
    </body>
</html>
