package com.control;

import com.google.gson.Gson;
import com.model.Data;
import com.model.Project;
import com.model.Skill;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

import org.json.*;

@WebServlet(urlPatterns = {"/project", "/project/*"})
public class project extends HttpServlet {

    public static String getHTML(String urlToRead) throws Exception {
        StringBuilder result = new StringBuilder();
        URL url = new URL(urlToRead);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        rd.close();
        return result.toString();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String projectsString = null;
        try {
            projectsString = getHTML("http://142.93.134.194:8000/joboonja/project");
        } catch (Exception e) {
            e.printStackTrace();
        }
        ObjectMapper mapper = new ObjectMapper();
        Data.projects = mapper.readValue(projectsString, new TypeReference<List<Project>>(){});
        String skillsString = null;
        try {
            skillsString = getHTML("http://142.93.134.194:8000/joboonja/skill");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Data.skills = mapper.readValue(skillsString, new TypeReference<List<Skill>>(){});

        int status=200;
        StringBuffer stringBuilder = new StringBuffer();


        StringTokenizer tokenizer = new StringTokenizer(request.getRequestURI(), "/");
        String page = tokenizer.nextToken();
        String idd=null;
        if(tokenizer.hasMoreElements()){

            int id = -1;
            try {
                String idString = tokenizer.nextToken();
                for (int i = 0; i < Data.projects.size(); i++) {
                    if(Data.projects.get(i).getId().equals(idString)){
                        id = i;
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
                    JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject.put("ID", Data.projects.get(id).getId());
                        jsonObject.put("Title", Data.projects.get(id).getTitle());
                        jsonObject.put("Description", Data.projects.get(id).getDescription());
                        jsonObject.put("ImageURL", Data.projects.get(id).getImageUrl());
                        jsonObject.put("Budget", Data.projects.get(id).getBudget());
                        jsonObject.put("Deadline", Data.projects.get(id).getDeadline());
                        JSONObject jsonObjectSkills = new JSONObject();
                        for(int i=0; i<Data.projects.get(id).getSkills().size(); i++){
                            jsonObjectSkills.put(Data.projects.get(id).getSkills().get(i).getName(), Data.projects.get(id).getSkills().get(i).getPoint());
                        }
                        jsonObject.put("Skills", jsonObjectSkills);
//                        JSONObject jsonObjectBids = new JSONObject();
//                        for(int i=0; i<Data.projects.get(id).getBids().size(); i++){
//                            jsonObjectBids.put(Data.projects.get(id).getBids().get(i).getBiddingUser().getId(), Data.projects.get(id).getBids().get(i).getBidAmount());
//                        }
//                        jsonObject.put("Bids", jsonObjectBids);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    response.setContentType("application/json; charset=UTF-8");
                    response.setCharacterEncoding("UTF-8");
                    PrintWriter out = response.getWriter();
                    out.print(jsonObject);
                    out.flush();
                }
                else{
                    JSONObject jsonObject = new JSONObject();
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    response.setContentType("application/json; charset=UTF-8");
                    response.setCharacterEncoding("UTF-8");
                    PrintWriter out = response.getWriter();
                    out.print(jsonObject);
                    out.flush();
                }
            }

//            request.setAttribute("ID", tokenizer.nextToken());
//            RequestDispatcher requestDispatcher;
//            requestDispatcher = request.getRequestDispatcher("/projectDetails.jsp");
//            requestDispatcher.forward(request, response);
        }
        else {

            JSONArray jsonArray = new JSONArray();
            List<JSONObject> list = new ArrayList<>();
            try {
                for (Project p : Data.projects) {
                    Boolean minReq = Data.user.hasMinReq(p.getId());
                    if (minReq) {
                        JSONObject object = new JSONObject();
                        object.put("ID", p.getId());
                        object.put("Title", p.getTitle());
                        object.put("Budget", p.getBudget());
                        object.put("Description", p.getDescription());
                        object.put("ImageURL", p.getImageUrl());
                        object.put("Deadline", p.getDeadline());
                        JSONObject jsonObjectSkills = new JSONObject();
                        for(int i=0; i<p.getSkills().size(); i++){
                            jsonObjectSkills.put(p.getSkills().get(i).getName(), p.getSkills().get(i).getPoint());
                        }
                        object.put("Skills", jsonObjectSkills);
                        jsonArray.put(object);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            response.setContentType("application/json; charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            out.print(jsonArray);
            out.flush();
//        RequestDispatcher requestDispatcher;
//        requestDispatcher = request.getRequestDispatcher("/project.jsp");
//        requestDispatcher.forward(request, response);
        }

    }
}
