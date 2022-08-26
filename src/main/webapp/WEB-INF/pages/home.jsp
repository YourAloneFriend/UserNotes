<%@ page import="com.application.model.User" %>
<%@ page import="com.application.model.Note" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>UserNotes</title>

    <style>
        <%@include file="../styles/main.css"%>
    </style>
</head>
<body>

<%
    User user = (User)session.getAttribute("user");
    List<Note> notes = (List<Note>)session.getAttribute("notes");
%>

<center>
    <div class="header-title color">
        <h1>Home page</h1>
        <h2>Hello <%=user.getUserName()%></h2>
    </div>

    <%if(notes != null && notes.size() > 0){%>
    <div>
        <table class="table color">
            <thead>
                <tr>
                    <th>№</th>
                    <th>Note</th>
                    <th>Created at</th>
                    <th colspan="2">Operations</th>
                </tr>
            </thead>

            <%
                int i = 1;
                for(Note note : notes){
            %>
            <tbody>
                <tr>
                    <th><%=i%></th>
                    <th><a href="/read-note?id=<%=note.getId()%>"><%=note.getNoteName()%></a></th>
                    <th><%=note.getCreatedAt()%></th>
                    <th><a href="/update-note?id=<%=note.getId()%>">Update</a></th>
                    <th><a href="/delete-note?id=<%=note.getId()%>">Delete</a></th>
                </tr>
            </tbody>
            <%
                ++i;}
            %>
        </table>
    </div>
    <%}%>

    <form method="post">
        <input class="medium-btn color margin-left" id="submit" name="button" value="Create new note" type="submit">
        <input class="medium-btn color margin-right" id="logout" name="button" value="Log out" type="submit">
    </form>
</center>

</body>
</html>
