<%@ page import="com.model.Data" %>
<%@ page import="com.model.User" %>
<%@ page import="com.google.gson.Gson" %>
<%@ page import="com.model.Project" %>
<%@ page import="com.model.Bid" %>
<%@ page import="java.util.StringTokenizer" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Bid</title>
</head>
<body>

    <%
        int bidAmount = Integer.parseInt(request.getParameter("bidAmount"));
        Gson gson = new Gson();
        User deepCopyUser = gson.fromJson(gson.toJson(Data.user), User.class);
        String idd = request.getParameter("hid");
        int id = Integer.valueOf(idd);
        Project deepCopyProject = gson.fromJson(gson.toJson(Data.projects.get(id)), Project.class);
        Data.bids.add(new Bid(deepCopyUser, deepCopyProject, bidAmount));
    %>

    <h3>You Bidded!!!</h3>

</body>
</html>
