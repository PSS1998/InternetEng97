package com.control;

import com.google.gson.Gson;
import com.model.Bid;
import com.model.Data;
import com.model.Project;
import com.model.User;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

@WebServlet(urlPatterns = {"/bid"})
public class bid extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StringBuffer stringBuilder = new StringBuffer();
        StringTokenizer tokenizer = new StringTokenizer(request.getRequestURI(), "/");
        String page = tokenizer.nextToken();
        String amount = request.getParameter("amount");
        String projectID = request.getParameter("projectID");
        JSONObject jsonObject = new JSONObject();
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        int bidAmount = Integer.parseInt(amount);
        Gson gson = new Gson();
        int id = -1;
        for (int i=0; i<Data.projects.size(); i++){
            if(Data.projects.get(i).getId().equals(projectID)){
                id = i;
                break;
            }
        }
        if(id != -1 && bidAmount<Data.projects.get(id).getBudget()) {
            User deepCopyUser = gson.fromJson(gson.toJson(Data.user), User.class);
            Project deepCopyProject = gson.fromJson(gson.toJson(Data.projects.get(id)), Project.class);
            Data.bids.add(new Bid(deepCopyUser, deepCopyProject, bidAmount));
            response.setStatus(HttpServletResponse.SC_OK);
            try {
                jsonObject.put(deepCopyProject.getTitle(), bidAmount);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        response.setContentType("application/json; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.print(jsonObject);
        out.flush();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
