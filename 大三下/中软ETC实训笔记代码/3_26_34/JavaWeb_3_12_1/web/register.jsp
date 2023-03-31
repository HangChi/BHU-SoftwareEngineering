<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2023/3/12
  Time: 18:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>register</title>
    <script type="text/javascript" src="jquery-1.7.2.js"></script>
    <script type="text/javascript">
        function check(){
            //js跟html交互代码
            var name = document.form1.name.value;
            //使用jquery ajax调用后台验证用户名是否已被占用接口
            $.ajax({
                url:"http://10.101.57.200:8080/checkNameServlet",
                data:"name="+name,
                type:"post",
                dataType:"jsonp", //指定返回格式为jsonp格式  callback(1) null(1)
                success:function(msg){
                    if(msg=="1"){
                        span_check.innerText="该用户名已被占用";
                        $("#span_check").css("color","red");
                        $("input:submit").attr("disabled",true);
                    }else if(msg=="0"){
                        span_check.innerText="该用户名可以使用";
                        $("#span_check").css("color","green");
                        $("input:submit").attr("disabled",false);
                    }
                },
                error:function(){
                }
            });
        }
    </script>
</head>
<body>
<form name="form1" action="registerServlet" method="post">
    <input type="text" name="name" onchange="check()" /><span id="span_check"></span>
    <br/>
    <input type="text" name="password"/>
    <input type="submit" value="注册"/>
</form>
</body>
</html>
