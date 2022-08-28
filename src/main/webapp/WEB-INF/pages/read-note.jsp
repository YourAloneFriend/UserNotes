<%@ page import="com.application.model.Note" %>
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
<center><div class="header-title color">
    <h1>Read note</h1>
</div></center>

<%
    Note note = (Note)session.getAttribute("note");
%>

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
    <center><div>
        <table>
            <tr>
                <td><input class="input-sign-form color" type="text" id="noteName" name="noteName" value="<%=note.getNoteName()%>" size="20" maxlength="64" disabled></td>

            </tr>
            <tr>
                <td><textarea class="input-note-form color" type="text" id="note" name="note" cols="80" rows="7" maxlength="2048" disabled><%=note.getNote()%></textarea></td>
            </tr>
        </table>

        <input class="medium-btn color" id="back" name="button" value="Back" type="submit">
    </div></center>
</form>
</body>
</html>

