<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2023/3/19
  Time: 19:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>update</title>
</head>
<body>
<form action="updateByIdServlet" method="post">
    <input type="text" name="id" value="${product.id}"/>
    <input type="text" name="name" value="${product.name}"/>
    <input type="text" name="price" value="${product.price}"/>
    <input type="text" name="slogan" value="${product.slogan}"/>
    <input type="submit" value="修改"/>
</form>

</body>
</html>
