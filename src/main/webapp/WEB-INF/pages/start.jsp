<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>UserNotes</title>

    <style>
        <%@include file="../styles/main.css"%>
    </style>
</head>
<body>
<div class="header-title color">
    <h1>Hello and welcome to UserNotes project!</h1>
    <h2>SignIn or SignUp, if you don't have an account, and let's go.</h2>
</div>

<form action="/registration">
    <center><button type="submit" class="big-btn color">SignUp</button></center>
</form>
<form action="/authentication">
    <center><button type="submit" class="big-btn color">SignIn</button></center>
</form>
</body>
</html>