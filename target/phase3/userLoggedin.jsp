<%@ page import="com.model.Data" %>
<%@ page import="com.model.Skill" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>User</title>
</head>
<body>
    <ul>
        <li>id: <%=Data.user.getId()%></li>
        <li>first name: <%=Data.user.getFirstName()%></li>
        <li>last name: <%=Data.user.getLastName()%></li>
        <li>jobTitle: <%=Data.user.getJobTitle()%></li>
        <li>bio: <%=Data.user.getBio()%></li>
        <li>
            skills:
            <ul>
                <%
                    for (Skill s : Data.user.getSkills()) {
                %>
                <li>
                    <%=s.getName()%>: <%=s.getPoint()%>
                    <form action="/skillDel.jsp" method="POST">
                        <button>Delete</button>
                        <input type="hidden" value="<%=s.getName()%>" name="skillName">
                    </form>
                </li>
                <%
                    }
                %>
            </ul>
        </li>
    </ul>
    Add Skill:
    <form action="/skillAdd.jsp" method="POST">
        <select name="skillName">
            <%
                for (Skill s : Data.skills) {
            %>
            <option value="<%=s.getName()%>"><%=s.getName()%></option>
            <%
                }
            %>
        </select>
        <button>Add</button>
    </form>
</body>
</html>
