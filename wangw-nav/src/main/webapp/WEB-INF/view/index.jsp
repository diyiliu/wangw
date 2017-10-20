<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>首页</title>
    <link rel="stylesheet" href="./resources/css/nav.css">
</head>
<body>
<div class="search">
    <form action="http://www.baidu.com/baidu" target="_blank">
        <input type="text" name="word" maxlength="65"><input type="submit" value="百度一下">
    </form>
</div>
<div class="content">
    <div class="addPanel" id="addPanel">
        <div class="title">
            <h3>添加网址</h3>
            <div class="close">
                <a href="javascript:void(0);" id="close"><span></span><span></span></a>
            </div>
        </div>
        <form id="addForm">
            <input type="text" class="name" placeholder="名称" name="name">
            <input type="text" class="url" placeholder="网址" name="url">
            <select class="typeSelect" name="type" id="type">
                <option value="">未选择分类</option>
                <c:forEach items="${typeList}" var="item">
                    <option value="${item.name}">${item.name}</option>
                </c:forEach>
            </select>
            <input type="button" class="save" id="addSub" value="保存">
            <input type="reset" id="reset">
        </form>
    </div>
    <div class="tool">
        <a href="javascript:void(0);"><span class="edit">编辑</span></a>
        <a href="javascript:void(0);" id="add"><span class="add">添加</span></a>
    </div>
    <c:forEach items="${groupList}" var="item">
        <dl>
            <dt><h3>${item.type}</h3></dt>
            <dd>
                <ul>
                    <c:forEach items="${item.websiteList}" var="i">
                        <li><a href="http://${i.url}" target="_blank">${i.name}</a></li>
                    </c:forEach>
                </ul>
            </dd>
        </dl>
    </c:forEach>
</div>

<script type="text/javascript" src="./resources/js/jquery-3.2.1.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        $("#add").on("click", function () {
            $("#addPanel").show();
        });

        $("#close").on("click", function () {
            $("#addPanel").hide();
        });

        $("#addSub").on("click", function () {
            var params = $("#addForm").serialize();
            params[type] = $("#type ").val();

            $.ajax({
                type: "post",
                url: "add",
                data: params,
                dataType: "text",
                success: function (data) {
                    console.log(data);
                    $("#addPanel").hide();
                    $("#reset").click();
                    location.reload();
                }
            });
        });
    });
</script>
</body>
</html>