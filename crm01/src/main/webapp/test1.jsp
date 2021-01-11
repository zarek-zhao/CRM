<%--
  User: zarek
  Date: 2020/12/27
  Time: 13:36
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
%>
<html>
<head>
    <title>$Title$</title>
    <base href="<%=basePath%>">

    <script>
        $.ajax({
            url:"",
            data:{

            },
            type:"",
            dataType:"json",
            success:function (data) {

            }
        })



    </script>
</head>
<body>



//创建时间，当前系统时间
String createTime = DateTimeUtil.getSysTime();
//创建人：当前登录的用户
String createBy = ((User)request.getSession().getAttribute("user")).getName();



</body>
</html>