<%--
  User: zarek
  Date: 2020/12/27
  Time: 1:15
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
%>
<html>
<head>
    <title>$Title$</title>
    <base href="<%=basePath%>">
    <script type="text/javascript" src="jquery/jquery-1.11.1-min.js"></script>
    <script type="text/javascript">
        $(function (){
            alert("hello world")
        })
    </script>
</head>
<body>
$END$
</body>
</html>