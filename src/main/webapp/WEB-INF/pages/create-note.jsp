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
    <h1>Create a note</h1>
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
            }}
        %>

    </br>
</div></center>

<form method="post">
    <center><div>
        <table>
            <tr>
                <td><input class="input-sign-form color" type="text" id="noteName" name="noteName" placeholder="Note name" size="20" maxlength="64"></td>

            </tr>
            <tr>
                <td><textarea class="input-note-form color" type="text" id="note" name="note" placeholder="Note" cols="80" rows="7" maxlength="2048"></textarea></td>
            </tr>
        </table>

        <input class="medium-btn color margin-right" id="submit" name="button" value="Create" type="submit">
        <input class="medium-btn color margin-right" id="back" name="button" value="Back" type="submit">
    </div></center>
</form>
</body>
</html>
