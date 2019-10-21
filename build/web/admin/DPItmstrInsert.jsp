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
        <c:if test="${canInsert == 'false' }">
            <script type="text/javascript">
                alert("插入失败,当前插入节点构成环路!");
            </script>
        </c:if>
    </head>
    <body>
        <div class="header bg-main">
            <%@include file="/WEB-INF/jspf/DPindex_top.jspf" %>
        </div>
        <div class="menu">
            <%@include file="/WEB-INF/jspf/DPindex_menu.jspf" %> 
        </div>
        <div class="admin">
            <div class="text-center text-blue margin-big-bottom"><h2>添加产品项目信息</h2></div>
            <hr/>
            <form method="post" action="<%= request.getContextPath()%>/Admin/DPItmstrInsertDao">
                <div class="form-group">
                    <div class="label">
                        <label for="itmId">项目编号</label>
                    </div>
                    <div class="field">
                        <input type="text" class="input" id="itmId" name="itmId" size="32" value="${itmId}" data-validate="required:必填"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="label">
                        <label for="shipId">父项目编号</label>
                    </div>
                    <select class="input" name="itmPid" id="itmPid" data-validate="required:请选择" >
                        <c:forEach var="itmstr" items="${itmstrs}">
                            <option value="${itmstr.getItmId()}"> 产品编号: ${itmstr.getItmId()} 产品名称: ${itmstr.getItmName()}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <div class="label">
                        <label for="itmName">项目名称</label>
                    </div>
                    <div class="field">
                        <input type="text" class="input" id="itmName" name="itmName" size="128" data-validate="required:必填"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="label">
                        <label for="seq">顺序号</label>
                    </div>
                    <div class="field">
                        <input type="text" class="input" id="seq" name="seq" data-validate="required:必填,number:纯数字"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="label">
                        <label for="itmCount">使用数量</label>
                    </div>
                    <div class="field">
                        <input type="text" class="input" id="itmCount" name="itmCount" data-validate="required:必填,number:纯数字"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="label">
                        <label for="supId">供应商编号</label>
                    </div>
                    <select class="input" name="supId" id="supId" data-validate="required:请选择" >
                        <c:forEach var="sup" items="${sups}">
                            <option value="${sup.getSupId()}"> 供应商编号: ${sup.getSupId()} 供应商名称: ${sup.getSupName()}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-button">
                    <button class="button" type="submit">提交</button>
                </div>
            </form>
        </div>

    </body>
</html>
