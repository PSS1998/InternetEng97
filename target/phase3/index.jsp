<%@ page import="org.codehaus.jackson.map.ObjectMapper" %>
<%@ page import="static com.control.project.getHTML" %>
<%@ page import="com.model.Data" %>
<%@ page import="com.model.Skill" %>
<%@ page import="java.util.List" %>
<%@ page import="org.codehaus.jackson.type.TypeReference" %>
<html>
<body>

<%
    ObjectMapper mapper = new ObjectMapper();

    String skillsString = getHTML("http://142.93.134.194:8000/joboonja/skill");

    Data.skills = mapper.readValue(skillsString, new TypeReference<List<Skill>>(){});
%>

<h2>Hello World!</h2>
</body>
</html>
