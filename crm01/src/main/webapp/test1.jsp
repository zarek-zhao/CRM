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
$END$
</body>
</html>