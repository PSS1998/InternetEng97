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
        // scriptlet
        int bidAmount = Integer.parseInt(request.getParameter("bidAmount"));
        Gson gson = new Gson();
        User deepCopyUser = gson.fromJson(gson.toJson(Data.user), User.class);
        String idd = request.getParameter("hid");
        int id = Integer.valueOf(idd);
//        System.out.println(origURL);
//        StringTokenizer tokenizer = new StringTokenizer(origURL, "/");
//        String page = tokenizer.nextToken();
//        String idd=null;
//        idd = tokenizer.nextToken();
//        int id = -1;
//        for (int i = 0; i < Data.projects.size(); i++) {
//            if(Data.projects.get(i).getId().equals(idd)){
//                id =i;
//                break;
//            }
//        }
        Project deepCopyProject = gson.fromJson(gson.toJson(Data.projects.get(id)), Project.class);
        Data.bids.add(new Bid(deepCopyUser, deepCopyProject, bidAmount));
    %>

    <h3>You Bidded!!!</h3>

</body>
</html>
