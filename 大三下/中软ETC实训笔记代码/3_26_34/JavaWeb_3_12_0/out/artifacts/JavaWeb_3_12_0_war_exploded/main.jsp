<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2023/3/12
  Time: 19:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>main</title>
    <link rel="stylesheet" href="bootstrap-3.4.1-dist/css/bootstrap.min.css"/>

    <style type="text/css" rel="stylesheet">
        h1{
            color:red;
            text-align: center;
        }
    </style>
</head>
<body>
<h1>${shop}</h1>

<table class="table hover">
    <tr>
        <td>编号</td>
        <td>食品名</td>
        <td>价格</td>
        <td>口号</td>
    </tr>
    <c:forEach var="product" items="${list}">
        <tr>
            <td>${product.id}</td>
            <td>${product.name}</td>
            <td>${product.price}</td>
            <td>${product.slogan}</td>
        </tr>
    </c:forEach>
</table>
<a href="mainServlet?changePageNow=0">首页</a>
<a href="mainServlet?changePageNow=${pageNow-pageSize}">上一页</a>

<c:forEach begin="1" end="${pageCount}" varStatus="status">
    <c:if test="${status.index==(pageNow/pageSize)+1}">
    <a style="color:red" href="mainServlet?changePageNow=${(status.index-1)*pageSize}">${status.index}</a>
    </c:if>
    <c:if test="${status.index!=(pageNow/pageSize)+1}">
        <a href="mainServlet?changePageNow=${(status.index-1)*pageSize}">${status.index}</a>
    </c:if>
</c:forEach>

<a href="mainServlet?changePageNow=${pageNow+pageSize}">下一页</a>
<a href="mainServlet?changePageNow=${(pageCount-1)*pageSize}">末页</a>
</body>
</html>
