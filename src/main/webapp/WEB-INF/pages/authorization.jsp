<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Authorization</title>

    <style>
        <%@include file="../styles/main.css"%>
    </style>
</head>
<body>
<center><div class="header-title color">
    <h1>Authorization form</h1>
</div></center>

<center><div class="AlertMessage">
    <%
        String alert = (String)session.getAttribute("alert");
        if(alert != null){
    %>
        <p class="color-red font-alert"><%=alert%></p>
    <%}%>
</div></center>

<center><form method="post">
    <input class="input-sign-form color" type="text" id="email" name="email" placeholder="Email">
    </br>
    <input class="input-sign-form color" type="password" id="password" name="password" placeholder="Password">
    </br>

    <input class="medium-btn color margin-left" id="submit" name="button" value="SignIn" type="submit">
    <input class="medium-btn color margin-left" id="back" name="button" value="Back" type="submit">
</form></center>

</body>
</html>
