<%@ page import="com.application.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>

    <style>
        <%@include file="../styles/main.css"%>
    </style>
</head>
<body>

<%
    User user = (User)session.getAttribute("user");
%>

<center><div class="header-title color">
    <h1>Home page</h1>
    <h2>Hello <%=user.getUserName()%></h2>
</div></center>

</body>
</html>
