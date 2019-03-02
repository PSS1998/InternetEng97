<%@ page import="com.model.User" %>
<%@ page import="com.model.Data" %><%--
  Created by IntelliJ IDEA.
  User: PSS
  Date: 3/2/2019
  Time: 2:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Users</title>
    <style>
        table {
            text-align: center;
            margin: 0 auto;
        }
        td {
            min-width: 300px;
            margin: 5px 5px 5px 5px;
            text-align: center;
        }
    </style>
</head>
<body>
    <table>
        <tr>
            <th>id</th>
            <th>name</th>
            <th>jobTitle</th>
        </tr>
        <%
            for (User u : Data.users) {
                if(!u.getId().equals(Data.user.getId())) {
        %>
        <tr>
            <td><%= u.getId() %></td>
            <td><%= u.getFirstName()+" "+u.getLastName() %></td>
            <td><%= u.getJobTitle() %></td>
        </tr>
        <%
                }
            }
        %>
    </table>
</body>
</html>
