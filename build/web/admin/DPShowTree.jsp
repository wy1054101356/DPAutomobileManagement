
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
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
        <script src="js/jquery-1.11.1.min.js" type="text/javascript"></script>
        <script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
        <script src="../js/jquery.treeview.js" type="text/javascript"></script>
        <link href="../css/jquery.treeview.css" rel="stylesheet" type="text/css"/>
        <script type="text/javascript">
            var onclickCount = 1;
            var itmId = "";

            var data = ${itmstrs}
            $(document).ready(function () {
                function createTree(data) {
                    var str = "<ul id='browser' class='filetree treeview-famfamfam'>";
                    for (var i = 0; i < data.length; i++) {
                        if (data[i].children && data[i].children.length > 0) {
                            str += "<li><span class='folder'> <strong><a href='<%=request.getContextPath()%>/Admin/DPShowTree?itmId=" + data[i].itmId + "'>" + data[i].itmName + "</a></strong></span><ul>";
//                            str += "<li onclick='aa(\"" + data[i].itmId + "\")' value='" + data[i].itmId + "'><span class='folder'> <strong><a>" + data[i].itmName + "</a></strong></span><ul >";
                            str += createTree(data[i].children);
                            str += "</ul></li>";
                        } else {
                            str += "<li><span class='file'> <a href='<%=request.getContextPath()%>/Admin/DPShowTree?itmId=" + data[i].itmId + "'>" + data[i].itmName + "</a></span><li>";
                        }
                    }
                    str += "</ul>";
                    return str;
                }
                $("#browser").treeview({
                    persist: "location",
                    collapsed: true,
                    unique: true,
                    toggle: function () {
                        console.log("%s was toggled.", $(this).find(">span").text());
                    }

                });
                $(".add").html(function () {
                    var branches = $(createTree(data)).appendTo("#browser");
                    $("#browser").treeview({
                        collapsed: true,
                        add: branches
                    });
                });
            });

//            function aa(a) {
//
//                if (onclickCount === 1) {
//
////                  alert("当前值:" + a);//获取添加的li标签的元素值
//                    document.getElementById("itmId").value = a;
//                    itmId = a;
//                    ajaxFun();
//                    onclickCount = 2;
//                } else if (onclickCount === 2) {
//                    onclickCount = 1;
//                }
//            }

////            alert("当前值:" + itmId);
//            function ajaxFun() {
//                $.ajax({
//                    type: "POST",
//                    url: "<%=request.getContextPath()%>/Admin/GetChildTreeServer?itmId=" + itmId,
//                    dataType: "text",
//                    success: function (mes) {
//                        var branches1 = createTree(mes).appendTo("#browser");
//                        $("#browser1").treeview(
//                            collapsed: true,
//                            add1: branches1
//                        );
//                        function createTree(data) {
//                            var str = "<ul id='browser1' class='filetree treeview-famfamfam'>";
//                            for (var i = 0; i < data.length; i++) {
//                                if (data[i].children && data[i].children.length > 0) {
//                                    //data-validate="required:必填,mobile:手机号格式有误,ajax#CheckNumber?managerId=:账号已存在"
////                            str += "<li><span class='folder'> <strong><a href='<%=request.getContextPath()%>/Admin/DPShowTree?itmId=" + data[i].itmId + "'>" + data[i].itmName + "</a></strong></span><ul>";
//                                    str += "<li onclick='aa(\"" + data[i].itmId + "\")' value='" + data[i].itmId + "'><span class='folder'> <strong><a>" + data[i].itmName + "</a></strong></span><ul >";
//                                    str += createTree(data[i].children);
//                                    str += "</ul></li>";
//                                } else {
//                                    str += "<li><span class='file'> <a href='<%=request.getContextPath()%>/Admin/DPShowTree?itmId=" + data[i].itmId + "'>" + data[i].itmName + "</a></span><li>";
//                                }
//                            }
//                            str += "</ul>";
//                            return str;
//                        }
//                        $("#browser1").treeview({
//                            persist: "location",
//                            collapsed: true,
//                            unique: true,
//                            toggle: function () {
//                                console.log("%s was toggled.", $(this).find(">span").text());
//                            }
//                        });
//
//                    }
//                }
//                });
//            }
        </script>

        <script>
            var data1 = ${itmstrs1};
            var choiseItmId = "";
            var isFun = false;
            $(document).ready(function () {
                function createTree(data1) {
                    var str = "<ul id='browser1' class='filetree treeview-famfamfam'>";
                    for (var i = 0; i < data1.length; i++) {
                        if (data1[i].children && data1[i].children.length > 0) {
                            //str += "<li><span class='folder'><strong> <a href='<%=request.getContextPath()%>/Admin/DPShowTree?itmId=" + data1[i].itmId + "'>" + data1[i].itmName + "</a></strong> </span><ul>";
                            str += "<li onclick='aa(\"" + data1[i].itmId + "\")' value='" + data1[i].itmId + "'><span class='folder'> <strong><a>" + data1[i].itmName + " " + data1[i].supId + "</a></strong></span><ul >";
                            str += createTree(data1[i].children);
                            str += "</ul></li>";
                        } else {
                            //str += "<li><span class='file'> <a href='<%=request.getContextPath()%>/Admin/DPShowTree?itmId=" + data1[i].itmId + "'>" + data1[i].itmName + "</a></span><li>";
                            str += "<li onclick='aa(\"" + data1[i].itmId + "\")' value='" + data1[i].itmId + "'><span class='file'><a>" + data1[i].itmName + " " + data1[i].supId +"</a></span><li>";
                        }
                    }
                    str += "</ul>";
                    return str;
                }
                $("#browser1").treeview({
                    persist: "location",
                    collapsed: true,
                    unique: true,
                    toggle: function () {
                        console.log("%s was toggled.", $(this).find(">span").text());
                    }
                });
                $(".add1").html(function () {
                    var temp = ${itmstrs1};
                    if (temp === null)
                        return;
                    var branches = $(createTree(data1)).appendTo("#browser1");
                    $("#browser1").treeview({
                        add1: branches
                    });
                });
            });
            function aa(a) {
                if (choiseItmId === "") {
                    choiseItmId = a;
                    isFun = true;
                    ajaxFun();
                } else {
                    return;
                }
            }

            function ajaxFun() {
                $.ajax({
                    type: "POST",
                    url: "<%=request.getContextPath()%>/Admin/GetChildTreeServer?choiseItmId=" + choiseItmId,
                    dataType: "text",
                    success: function (mes) {
                        // alert(mes);
                        var arr = mes.split("@@@");

                        document.getElementById("czhs").innerHTML = "0.008秒";
                        document.getElementById("xmbh").innerHTML = arr[0];
                        document.getElementById("fxmbh").innerHTML = arr[1];
                        document.getElementById("xmmc").innerHTML = arr[2];
                        document.getElementById("xmmc1").innerHTML = arr[2];
                        document.getElementById("sxh").innerHTML = arr[3];
                        document.getElementById("sysl").innerHTML = arr[4];
                        document.getElementById("gysbh").innerHTML = arr[5];
                        choiseItmId = "";
                    }
                });
            }
        </script>
    </head>
    <body>
        <div class="header bg-main">
            <%@include file="/WEB-INF/jspf/DPindex_top.jspf" %>
        </div>
        <div class="menu">
            <%@include file="/WEB-INF/jspf/DPindex_menu.jspf" %> 
        </div>


        <div class="admin" style="font-size: 15px">
            <div class="text-center text-blue margin-big-bottom"><h2>产品树结构 丨 产品过滤</h2></div><hr/>
            <form method="post" action="<%= request.getContextPath()%>/Admin/DPShowTree">
                <div class="form-group">
                    <div class="field">
                        <div class="input-group">
                            <span class="addbtn"><input class="button" value="按产品项目搜索产品树:"/></span>
                            <input type="text" class="input" name="itmId" id="itmId" size="30" value="${itmId}" data-validate="required:必填"/>
                            <span class="addbtn"><input type="submit" class="button" value="搜索"/></span>
                        </div>
                        <div class="input-group">
                            <span class="addbtn"><input class="button" value="按供应商过滤产品结构"/></span>
                            <input type="text" class="input" name="supId" id="supId" size="30" value="${supId}"/>
                            <span class="addbtn"><input type="submit" class="button" value="过滤"/></span>
                        </div>
                    </div>
                </div>
            </form>
            <div class="line-big">
                <div class="xm4">
                    <div class="panel">
                        <div class="panel-head"><strong>所有产品结构</strong></div>
                        <div class="panel border-back">
                            <div id="main" class ="add"  >
                                <ul id="browser" class="filetree treeview-famfamfam"  >  </ul>
                            </div >
                        </div>
                    </div>
                </div>

                <div class="xm4">
                    <div class="panel">
                        <div class="panel-head">
                            <strong style=' color: #0ae'>${itmstr.getItmName()}</strong>
                            <strong>子产品结构</strong>
                        </div>
                        <div class="panel border-back">
                            <div id="main" class ="add1" >
                                <ul id="browser1" class="filetree treeview-famfamfam">  </ul>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="xm4">
                    <div class="panel">
                        <div class="panel-head"><strong><strong style=' color: #0ae' id="xmmc1">${itmstr.getItmName()}</strong> 产品信息</strong></div>
                        <table class="table">
                            <tr style="color: #0ae">
                                <td width="110" align="right"  >查找耗时:</td>
                                <td id="czhs">${findTreeTime}秒</td>
                            </tr>
                            <tr>
                                <td width="110" align="right" >项目编号:</td>
                                <td id="xmbh">${itmstr.getItmId()}</td>
                            </tr>
                            <tr>
                                <td align="right" >父项目编号:</td>
                                <td id="fxmbh">${itmstr.getItmPid()}</td>
                            </tr>
                            <tr>
                                <td align="right">项目名称:</td>
                                <td id="xmmc">${itmstr.getItmName()}</td>
                            </tr>
                            <tr>
                                <td align="right" >顺序号:</td>
                                <td id="sxh">${itmstr.getSeq()}</td>
                            </tr>     
                            <tr>
                                <td align="right" >使用数量:</td>
                                <td id="sysl">${itmstr.getItmCount()}</td>
                            </tr>   
                            <tr>
                                <td align="right">供应商编号:</td>
                                <td id="gysbh">${itmstr.getSupId()}</td>
                            </tr>   

                        </table>
                    </div>

                </div>
            </div>
        </div>
    </body>
</html>
