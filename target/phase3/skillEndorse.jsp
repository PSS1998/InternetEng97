<%@ page import="com.model.Data" %>
<%@ page import="com.model.Skill" %>
<%@ page import="com.model.Endorse" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Skill Endorse</title>
</head>
<body>
<%
    String id = request.getParameter("userID");
    request.setAttribute("ID", id);
    String SkillName = request.getParameter("skillName");
    int index = -1;
    for (int i=0; i<Data.users.size(); i++){
        if(Data.users.get(i).getId().equals(id)){
            index = i;
        }
    }
    boolean alreadyEndorsed = false;
    for (int i=0; i<Data.endorses.size(); i++){
        if(Data.endorses.get(i).getSkill().equals(SkillName) && Data.endorses.get(i).getUser1().equals(Data.user.getId()) && Data.endorses.get(i).getUser2().equals(Data.users.get(index).getId())){
            alreadyEndorsed = true;
        }
    }
    if(index != -1 && !alreadyEndorsed) {
        Data.users.get(index).endorseSkill(SkillName);
        Data.endorses.add(new Endorse(SkillName, Data.user.getId(), Data.users.get(index).getId()));
    }
%>
<jsp:forward page="userGuest.jsp"/>
</body>
</html>
