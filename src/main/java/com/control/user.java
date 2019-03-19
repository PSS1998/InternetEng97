package com.control;

import com.model.Data;
import com.model.Project;
import com.model.User;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

@WebServlet(urlPatterns = {"/user", "/user/*"})
public class user extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        StringBuffer stringBuilder = new StringBuffer();
        StringTokenizer tokenizer = new StringTokenizer(request.getRequestURI(), "/");
        String page = tokenizer.nextToken();
        String id=null;
        if(tokenizer.hasMoreElements()) {
            id = tokenizer.nextToken();
            JSONObject jsonObject = new JSONObject();
            if (Data.user.getId().equals(id)) {
                try {
                    jsonObject.put("ID", Data.user.getId());
                    jsonObject.put("FirstName", Data.user.getFirstName());
                    jsonObject.put("LastName", Data.user.getLastName());
                    jsonObject.put("JobTitle", Data.user.getJobTitle());
                    jsonObject.put("ProfilePictureURL", Data.user.getProfilePictureURL());
                    jsonObject.put("Bio", Data.user.getBio());
                    JSONObject jsonObjectSkills = new JSONObject();
                    for(int i=0; i<Data.user.getSkills().size(); i++){
                        jsonObjectSkills.put(Data.user.getSkills().get(i).getName(), Data.user.getSkills().get(i).getPoint());
                    }
                    jsonObject.put("Skills", jsonObjectSkills);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                response.setContentType("application/json; charset=UTF-8");
                response.setCharacterEncoding("UTF-8");
                PrintWriter out = response.getWriter();
                out.print(jsonObject);
                out.flush();
//                RequestDispatcher requestDispatcher;
//                requestDispatcher = request.getRequestDispatcher("/userLoggedin.jsp");
//                requestDispatcher.forward(request, response);
            } else {
                int idd = -1;
                for (int i=0; i<Data.users.size(); i++){
                    if(Data.users.get(i).getId().equals(id)){
                        idd = i;
                    }
                }
                try {
                    jsonObject.put("ID", Data.users.get(idd).getId());
                    jsonObject.put("FirstName", Data.users.get(idd).getFirstName());
                    jsonObject.put("LastName", Data.users.get(idd).getLastName());
                    jsonObject.put("JobTitle", Data.users.get(idd).getJobTitle());
                    jsonObject.put("ProfilePictureURL", Data.users.get(idd).getProfilePictureURL());
                    jsonObject.put("Bio", Data.users.get(idd).getBio());
                    JSONObject jsonObjectSkills = new JSONObject();
                    for(int i=0; i<Data.users.get(idd).getSkills().size(); i++){
                        jsonObjectSkills.put(Data.users.get(idd).getSkills().get(i).getName(), Data.users.get(idd).getSkills().get(i).getPoint());
                    }
                    jsonObject.put("Skills", jsonObjectSkills);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                response.setContentType("application/json; charset=UTF-8");
                response.setCharacterEncoding("UTF-8");
                PrintWriter out = response.getWriter();
                out.print(jsonObject);
                out.flush();
//                request.setAttribute("ID", id);
//                RequestDispatcher requestDispatcher;
//                requestDispatcher = request.getRequestDispatcher("/userGuest.jsp");
//                requestDispatcher.forward(request, response);
            }
        }
        else {

            JSONArray jsonArray = new JSONArray();
            List<JSONObject> list = new ArrayList<>();
            try {
                for (User u : Data.users) {
                    if (!u.getId().equals(Data.user.getId())) {
                        JSONObject object = new JSONObject();
                        object.put("ID", u.getId());
                        object.put("Title", u.getFirstName() + u.getLastName());
                        object.put("Budget", u.getJobTitle());
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
//        requestDispatcher = request.getRequestDispatcher("/users.jsp");
//        requestDispatcher.forward(request, response);

        }

    }
}
