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
        <script>/*Fixing iframe window.innerHeight 0 issue in Safari*/document.body.clientHeight;</script>
        <script src="https://gw.alipayobjects.com/os/antv/pkg/_antv.g6-3.0.2/build/g6.js"></script>
        <script src="https://gw.alipayobjects.com/os/antv/assets/lib/jquery-3.2.1.min.js"></script>
        <script src="https://gw.alipayobjects.com/os/antv/pkg/_antv.hierarchy-0.4.0/build/hierarchy.js"></script>
        <script>
            var data =${itmstrs}
            $(document).ready(function () {
                function createTree(data) {
                    var graph = new G6.TreeGraph({
                        container: 'mountNode',
                        width: window.innerWidth,
                        height: window.innerHeight,
                        pixelRatio: 2,
                        modes: {
                            default: [{
                                    type: 'collapse-expand',
                                    onChange: function onChange(item, collapsed) {
                                        var data = item.get('model').data;
                                        data.collapsed = collapsed;
                                        return true;
                                    }
                                }, 'drag-canvas', 'zoom-canvas']
                        },
                        defaultNode: {
                            size: 16,
                            anchorPoints: [[0, 0.5], [1, 0.5]]
                        },
                        defaultEdge: {
                            shape: 'cubic-horizontal'
                        },
                        nodeStyle: {
                            default: {
                                fill: '#40a9ff',
                                stroke: '#096dd9'
                            }
                        },
                        edgeStyle: {
                            default: {
                                stroke: '#A3B1BF'
                            }
                        },
                        layout: function layout(data) {
                            return Hierarchy.compactBox(data, {
                                direction: 'LR',
                                getId: function getId(d) {
                                    return d.itmName;
                                },
                                getHeight: function getHeight() {
                                    return 16;
                                },
                                getWidth: function getWidth() {
                                    return 16;
                                },
                                getVGap: function getVGap() {
                                    return 10;
                                },
                                getHGap: function getHGap() {
                                    return 100;
                                }
                            });
                        }
                    });
                    graph.data(data);
                    graph.render();
                    graph.getNodes().forEach(function (node) {
                        var model = node.get('model');
                        model.label = model.data.itmName + model.data.itmId;
                        model.labelCfg = {
                            offset: 10,
                            style: {
                                fill: '#666'
                            }
                        };
                        if (model.children && model.children.length > 0) {
                            model.labelCfg.position = 'left';
                        } else {
                            model.labelCfg.position = 'right';
                        }
                    });
                    graph.refresh();
                    graph.fitView();
                }
                $(".add").html(function () {
                    $(createTree(data));
                });
            });

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
            <div class="text-center text-blue margin-big-bottom"><h2>项目树结构</h2></div><hr/>
            <div id="mountNode" class="add"></div>
        </div>

    </body>
</html>
