<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="company_add_tag" uri="/WEB-INF/tlds/company_add_tag" %>
<!DOCTYPE html>
<html lang="zh-cn">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
        <meta name="renderer" content="webkit">
        <title>后台管理中心</title>
        <link rel="stylesheet" href="<%= request.getContextPath()%>/css/pintuer.css">
        <link rel="stylesheet" href="<%= request.getContextPath()%>/css/admin.css">
        <script src="<%= request.getContextPath()%>/js/jquery.js"></script>
        <script src="<%= request.getContextPath()%>/js/pintuer.js"></script>
        <script src="<%= request.getContextPath()%>/js/admin.js"></script>
        <script type="text/javascript">

                function test() {
                    var k = window.confirm("你确定要删除该记录");
                    if (k) {
                        event.returnValue = true;
                    } else {
                        event.returnValue = false;
                    }
                }

                window.onload = function () {
                    //1、获取表格
                    var tab = document.getElementById("tal");
                    //2、获取表格中tbody的行数
                    var len = tab.tBodies[0].rows.length
                    //                alert(len)
                    //3、开始循环，设置颜色
                    for (var i = 0; i < len; i++) {
                        if (i % 2 == 0) {
                            tab.tBodies[0].rows[i].style.backgroundColor = "write";
                        } else {
                            tab.tBodies[0].rows[i].style.backgroundColor = "#efefef";
                        }
                    }
                };
        </script>
        <style>
            .table td{vertical-align: middle;}
        </style>
    </head>
    <body>
        <div class="header bg-main">
            <%@include file="/WEB-INF/jspf/DPindex_top.jspf" %>
        </div>
        <div class="menu">
            <%@include file="/WEB-INF/jspf/DPindex_menu.jspf" %> 
        </div>
        <div class="admin">
            <div class="table-responsive">
                <div class="text-center text-blue margin-big-bottom"><h2>产品项目信息</h2></div>
                <form method="post" action="<%= request.getContextPath()%>/Admin/DPItmstrManage">
                    <div class="form-group">
                        <div class="field">
                            <div class="input-group">
                                <span class="addbtn">
                                    <button type="button" class="button icon-search"></button>
                                </span>
                                <input type="text" class="input" name="itmId" size="30" placeholder="按项目编号产查找" />

                                <span class="addbtn"><input type="submit" class="button" value="搜索"/></span>
                            </div>
                        </div>
                    </div>
                </form>
                <table class="table table-bordered"  id="tal">
                    <tr style="background-color: #ccc">
                        <td>项目编号</td><td>父项目编号</td><td>项目名称</td><td>顺序号</td><td>使用数量</td><td>供应商编号</td><td>子项目</td><td>更新</td><td>删除</td>
                    </tr>
                    <c:forEach var="itmstr" items="${itmstrs}">
                        <tr>
                            <td>${itmstr.getItmId()}</td>
                            <td>${itmstr.getItmPid()}</td>
                            <td>${itmstr.getItmName()}</td>
                            <td>${itmstr.getSeq()}</td>
                            <td>${itmstr.getItmCount()}</td>
                            <td>${itmstr.getSupId()}</td>
                            <c:if test="${itmstr.getItmstrIsParent() == 'true'}">
                                <td>
                                    <a class="button button-small border-blue" href="<%=request.getContextPath()%>/Admin/DPItmstrManage?itmPid=${itmstr.getItmId()}">查询子结点</a>
                                </td>
                            </c:if>
                            <c:if test="${itmstr.getItmstrIsParent() == 'false'}">
                                <td>
                                    <a class="button button-small border-green">当前为叶子</a>
                                </td>
                            </c:if>

                            <td>
                                <a class="button button-small border-yellow" href="<%=request.getContextPath()%>/Admin/DPItmstrUpdate?itmId=${itmstr.getItmId()}">更新项目</a>
                            </td>
                            <c:if test="${itmstr.getItmstrIsParent() == 'true'}">
                                <td>
                                    <a class="button button-small border-green">不可删除</a>
                                </td>
                            </c:if>
                            <c:if test="${itmstr.getItmstrIsParent() == 'false'}">
                                <td>
                                    <a class="button button-small border-red" onclick="test()" href="<%=request.getContextPath()%>/Admin/DPItmstrDelete?itmId=${itmstr.getItmId()}">删除项目</a>
                                </td>
                            </c:if>
                        </tr>
                    </c:forEach>
                </table>
            </div>
            <div class="text-center margin-big-top height-big">
                ${pagination.pageBar} &nbsp;&nbsp; ${pagination.numPageBar} &nbsp;&nbsp; 
            </div>
        </div>

    </body>
</html>
