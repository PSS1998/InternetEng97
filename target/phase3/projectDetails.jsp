<%@ page import="com.model.Project" %>
<%@ page import="com.model.Data" %>
<%@ page import="java.util.StringTokenizer" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Project</title>
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
    <%
        int id = -1;
        try {
            String idd = request.getAttribute("ID").toString();
//            StringTokenizer tokenizer = new StringTokenizer(request.getRequestURI(), "/");
//            System.out.println(tokenizer.nextToken());
//            String pageAddress = tokenizer.nextToken();
//            String idd=null;
//            idd = tokenizer.nextToken();

            for (int i = 0; i < Data.projects.size(); i++) {
                if(Data.projects.get(i).getId().equals(idd)){
                    id =i;
                    break;
                }
            }
        }
        catch (Exception E) {
            System.out.println(E.toString());
        }
        if (id != -1){
            Boolean minReq = Data.user.hasMinReq(Data.projects.get(id).getId());
            if(minReq) {
    %>
    <ul>
        <li>id: <%=Data.projects.get(id).getId()%></li>
        <li>title: <%=Data.projects.get(id).getTitle()%></li>
        <li>description: <%=Data.projects.get(id).getDescription()%></li>
        <li>imageUrl: <img src="<%=Data.projects.get(id).getImageUrl()%>" style="width: 50px; height: 50px;"></li>
        <li>budget: <%=Data.projects.get(id).getBudget()%></li>
    </ul>
    <%
            }
        }
    %>

    <%
        Boolean hasBidBefore = false;
        if(!Data.bids.isEmpty()) {
            for (int i = 0; i < Data.bids.size(); i++) {
                if (Data.projects.get(id).getId().equals(Data.bids.get(i).getProject().getId())) {
                    if (Data.bids.get(i).getBiddingUser().getId().equals(Data.user.getId())) {
                        hasBidBefore = true;
                    }
                }
            }
        }
        if(!hasBidBefore){
    %>

    <form action="/bid.jsp" method="POST">
        <label>Bid Amount:</label>
        <input type="number" name="bidAmount">
        <input type="hidden" name="hid" value="<%=Integer.toString(id)%>">
        <button>Submit</button>
    </form>

    <%
        }
    %>

</body>
</html>
