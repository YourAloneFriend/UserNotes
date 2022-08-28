<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>UserNotes</title>

    <style>
        <%@include file="../styles/main.css"%>
    </style>
</head>
<body>
<div class="header-title color">
    <h1>Registration form</h1>
</div>


<center><div class="AlertMessage">
        <%
            if(session.getAttribute("alert-color") != null){
                if((Boolean)session.getAttribute("alert-color")){
        %>
            <p class="color-red font-alert">${alert}</p>
        <%
            }else{
        %>
            <p class="color-green font-alert">${alert}</p>
        <%
            }}else{
        %>
            </br>
        <%}%>
</div></center>

<form method="post">
    <center><div class="registration">
        <input class="input-sign-form color" type="text" id="username" name="username" placeholder="Username" >
        </br>
        <input class="input-sign-form color" type="text" id="email" name="email" placeholder="Email" >
        </br>
        <input class="input-sign-form color" type="password" id="password1" name="password1" placeholder="Password" >
        </br>
        <input class="input-sign-form color" type="password" id="password2" name="password2" placeholder="Password" >
        </br>

        <input class="medium-btn color margin-left" id="submit" name="button" value="SignUp" type="submit">
        <input class="medium-btn color margin-right" id="back" name="button" value="Back" type="submit">

    </div></center>
</form>

</body>
</html>