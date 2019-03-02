<%@ page import="com.model.Project" %>
<%@ page import="com.model.Data" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Projects</title>
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
            <th>title</th>
            <th>budget</th>
        </tr>
        <%
            for (Project p : Data.projects) {
                Boolean minReq = Data.user.hasMinReq(p.getId());
                if(minReq) {
        %>
        <tr>
            <td><%= p.getId() %></td>
            <td><%= p.getTitle() %></td>
            <td><%= p.getBudget() %></td>
        </tr>
        <%
                }
            }
        %>
    </table>
</body>
</html>
