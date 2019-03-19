<%@ page import="org.codehaus.jackson.map.ObjectMapper" %>
<%@ page import="static com.control.project.getHTML" %>
<%@ page import="com.model.Data" %>
<%@ page import="com.model.Skill" %>
<%@ page import="java.util.List" %>
<%@ page import="org.codehaus.jackson.type.TypeReference" %>
<!DOCTYPE html>
<html>
<body>

<%
    ObjectMapper mapper = new ObjectMapper();

    String skillsString = getHTML("http://142.93.134.194:8000/joboonja/skill");

    Data.skills = mapper.readValue(skillsString, new TypeReference<List<Skill>>(){});
%>



<p id="test1">Hello World!</p>

<p id="test2">Hello World!</p>


<script>

    var xhttp1 = new XMLHttpRequest();
    xhttp1.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            // Typical action to be performed when the document is ready:
            document.getElementById("test1").innerHTML = xhttp1.responseText;
        }
    };
    xhttp1.open("GET", "http://localhost:8080/project", true);
    xhttp1.send();

</script>

<script>

    var xhttp2 = new XMLHttpRequest();
    xhttp2.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            // Typical action to be performed when the document is ready:
            document.getElementById("test2").innerHTML = xhttp2.responseText;
        }
    };
    xhttp2.open("PUT", "http://localhost:8080/skill?skill=CSS", true);
    xhttp2.send();

</script>


</body>
</html>
