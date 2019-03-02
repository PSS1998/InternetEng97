<%@ page import="com.model.Data" %>
<%@ page import="com.model.Skill" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Skill Add</title>
</head>
<body>
<%
    String SkillName = request.getParameter("skillName").toString();
    Data.user.addSkill(SkillName);
%>
<jsp:forward page="userLoggedin.jsp"/>
</body>
</html>
