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
    <%
        String idString = request.getAttribute("ID").toString();
        int idd = -1;
        for (int i=0; i<Data.users.size(); i++){
            if(Data.users.get(i).getId().equals(idString)){
                idd = i;
            }
        }
    %>
    <li>id: <%=Data.users.get(idd).getId()%></li>
    <li>first name: <%=Data.users.get(idd).getFirstName()%></li>
    <li>last name: <%=Data.users.get(idd).getLastName()%></li>
    <li>jobTitle: <%=Data.users.get(idd).getJobTitle()%></li>
    <li>bio: <%=Data.users.get(idd).getBio()%></li>
    <li>
        skills:
        <ul>
            <%
                String id = request.getAttribute("ID").toString();
                for (Skill s : Data.users.get(idd).getSkills()) {
            %>
            <li>
                <%=s.getName()%>: <%=s.getPoint()%>
                <form action="/skillEndorse.jsp" method="POST">
                    <button>Endorse</button>
                    <input type="hidden" value="<%=s.getName()%>" name="skillName">
                    <input type="hidden" value="<%=id%>" name="userID">
                </form>
            </li>
            <%
                }
            %>
        </ul>
    </li>
</ul>
</body>
</html>
