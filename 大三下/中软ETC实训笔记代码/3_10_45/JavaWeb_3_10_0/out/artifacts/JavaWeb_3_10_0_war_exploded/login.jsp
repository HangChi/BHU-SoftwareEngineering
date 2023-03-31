<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2023/3/10
  Time: 19:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="bootstrap-3.4.1-dist/css/bootstrap.min.css"/>
</head>
<body>
<button class="btn btn-danger">(危险)Danger</button>
<button class="btn btn-primary btn-lg active">渤海大学 锦州烧烤</button>
<img class="img-thumbnail" src="sk.jpg"/>

<form>
    <div class="form-group">
        <label for="exampleInputEmail1">账号</label>
        <input type="email" class="form-control" id="exampleInputEmail1" placeholder="请输入账号">
    </div>
    <div class="form-group">
        <label for="exampleInputPassword1">密码</label>
        <input type="password" class="form-control" id="exampleInputPassword1" placeholder="请输入密码">
    </div>
    <div class="form-group">
        <label for="exampleInputFile">文件上传</label>
        <input type="file" id="exampleInputFile">
        <p class="help-block">Example block-level help text here.</p>
    </div>
    <div class="checkbox">
        <label>
            <input type="checkbox"> Check me out
        </label>
    </div>
    <button type="submit" class="btn btn-default">登录</button>
</form>

<table class="table table-hover">
    <thead>
    <tr>
        <td>编号</td>
        <td>书名</td>
        <td>主角</td>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td>1</td>
        <td>天龙八部</td>
        <td>乔峰</td>
    </tr>
    <tr>
        <td>2</td>
        <td>连城诀</td>
        <td>狄云</td>
    </tr>
    <tr>
        <td>3</td>
        <td>书剑恩仇录</td>
        <td>陈家洛</td>
    </tr>
    </tbody>
</table>
</body>
</html>
