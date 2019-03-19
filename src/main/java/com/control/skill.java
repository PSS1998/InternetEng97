package com.control;

import com.model.Data;
import com.model.Endorse;
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

@WebServlet(urlPatterns = {"/skill", "/skill/endorse"})
public class skill extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StringBuffer stringBuilder = new StringBuffer();
        StringTokenizer tokenizer = new StringTokenizer(request.getRequestURI(), "/");
        String page = tokenizer.nextToken();
        if(!tokenizer.hasMoreTokens()) {
            String skillName = request.getParameter("skill");
            JSONObject jsonObject = new JSONObject();
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            for (int i = 0; i < Data.skills.size(); i++) {
                if (Data.skills.get(i).getName().equals(skillName)) {
                    response.setStatus(HttpServletResponse.SC_OK);
                    Data.user.addSkill(skillName);
                    try {
                        jsonObject.put("Skill", skillName);
                        jsonObject.put("Point", 0);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
            response.setContentType("application/json; charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            out.print(jsonObject);
            out.flush();
        }
        else{
            String token = tokenizer.nextToken();
            JSONObject jsonObject = new JSONObject();
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            if(token.equals("endorse")){
                String skillName = request.getParameter("skill");
                String idd = request.getParameter("id");
                int id = -1;
                try {
                    for (int i = 0; i < Data.users.size(); i++) {
                        if(Data.users.get(i).getId().equals(idd)){
                            id = i;
                            break;
                        }
                    }
                }
                catch (Exception E) {
                    System.out.println(E.toString());
                }
                if(id != -1){
                    for (int i = 0; i < Data.users.get(id).getSkills().size(); i++) {
                        if (Data.users.get(id).getSkills().get(i).getName().equals(skillName)) {
                            response.setStatus(HttpServletResponse.SC_OK);
                            boolean alreadyEndorsed = false;
                            for (int j=0; j<Data.endorses.size(); j++){
                                if(Data.endorses.get(j).getSkill().equals(skillName) && Data.endorses.get(j).getUser1().equals(Data.user.getId()) && Data.endorses.get(j).getUser2().equals(Data.users.get(id).getId())){
                                    alreadyEndorsed = true;
                                }
                            }
                            if(!alreadyEndorsed) {
                                Data.users.get(id).endorseSkill(skillName);
                                Data.endorses.add(new Endorse(skillName, Data.user.getId(), Data.users.get(id).getId()));
                            }
                            try {
                                jsonObject.put("Skill", skillName);
                                jsonObject.put("Point", Data.users.get(i).getSkills().get(i).getPoint());
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
            response.setContentType("application/json; charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            out.print(jsonObject);
            out.flush();
        }
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StringBuffer stringBuilder = new StringBuffer();
        StringTokenizer tokenizer = new StringTokenizer(request.getRequestURI(), "/");
        String page = tokenizer.nextToken();
        String id=null;
        String skillName = request.getParameter("skill");
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        for(int i=0; i<Data.user.getSkills().size(); i++){
            if(Data.user.getSkills().get(i).getName().equals(skillName)){
                response.setStatus(HttpServletResponse.SC_OK);
                Data.user.deleteSkill(skillName);
            }
        }
        JSONObject jsonObject = new JSONObject();
        response.setContentType("application/json; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.print(jsonObject);
        out.flush();
    }

}
