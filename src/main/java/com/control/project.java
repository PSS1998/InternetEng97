package com.control;

import com.model.Data;
import com.model.Project;
import com.model.Skill;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.StringTokenizer;

@WebServlet(urlPatterns = {"/project", "/project/*"})
public class project extends HttpServlet {

    public static String getHTML(String urlToRead) throws Exception {
        StringBuilder result = new StringBuilder();
        URL url = new URL(urlToRead);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        rd.close();
        return result.toString();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
//        String context = tokenizer.nextToken();
        String page = tokenizer.nextToken();
        String idd=null;
        if(tokenizer.hasMoreElements()){
            stringBuilder.append("<!DOCTYPE html>" +
                    "<html lang=\"en\">" +
                    "<head>" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <title>Projects</title>\n" +
                    "    <style>\n" +
                    "        table {\n" +
                    "            text-align: center;\n" +
                    "            margin: 0 auto;\n" +
                    "        }\n" +
                    "        td {\n" +
                    "            min-width: 300px;\n" +
                    "            margin: 5px 5px 5px 5px;\n" +
                    "            text-align: center;\n" +
                    "        }" +
                    "    </style>" +
                    "</head>" +
                    "<body>\n" +
                    "    <table>\n" +
                    "        <tr>\n" +
                    "            <th>id</th>\n" +
                    "            <th>title</th>\n" +
                    "            <th>budget</th>\n" +
                    "            <th>description</th>\n" +
                    "            <th>imageURL</th>\n" +
                    "        </tr>\n" );
            idd = tokenizer.nextToken();
            int id = -1;
            for (int i = 0; i < Data.projects.size(); i++) {
                if(Data.projects.get(i).getId().equals(idd)){
                    id =i;
                    break;
                }
            }

            if(id == -1){
                status =403;
            }else{
                stringBuilder.append("        <tr> <td>");
                stringBuilder.append(Data.projects.get(id).getId());
                stringBuilder.append("</td><td>");
                stringBuilder.append(Data.projects.get(id).getTitle());
                stringBuilder.append("</td><td>");
                stringBuilder.append(Data.projects.get(id).getBudget());
                stringBuilder.append("</td><td>");
                stringBuilder.append(Data.projects.get(id).getDescription());
                stringBuilder.append("</td><td>");
                stringBuilder.append(Data.projects.get(id).getImageUrl());
                stringBuilder.append(" </td> </tr>");
            }

        }
        else {
            stringBuilder.append("<!DOCTYPE html>" +
                    "<html lang=\"en\">" +
                    "<head>" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <title>Projects</title>\n" +
                    "    <style>\n" +
                    "        table {\n" +
                    "            text-align: center;\n" +
                    "            margin: 0 auto;\n" +
                    "        }\n" +
                    "        td {\n" +
                    "            min-width: 300px;\n" +
                    "            margin: 5px 5px 5px 5px;\n" +
                    "            text-align: center;\n" +
                    "        }" +
                    "    </style>" +
                    "</head>" +
                    "<body>\n" +
                    "    <table>\n" +
                    "        <tr>\n" +
                    "            <th>id</th>\n" +
                    "            <th>title</th>\n" +
                    "            <th>budget</th>\n" +
                    "        </tr>\n" );

            for (int i = 0; i < Data.projects.size(); i++) {
                Boolean minReq = Data.user.hasMinReq(Data.projects.get(i).getId());
                if(minReq) {
                    stringBuilder.append("        <tr> <td>");
                    stringBuilder.append(Data.projects.get(i).getId());
                    stringBuilder.append("</td><td>");
                    stringBuilder.append(Data.projects.get(i).getTitle());
                    stringBuilder.append("</td><td>");
                    stringBuilder.append(Data.projects.get(i).getBudget());
                    stringBuilder.append(" </td>" + "</tr>");
                }
            }
        }
        stringBuilder.append(

                "    </table>\n" +
                        "</body>\n" +
                        "</html>");


        if(status == 403){
            stringBuilder = new StringBuffer();
            stringBuilder.append("PROJECT NOT FOUND");
        }
        response.setStatus(status, "PROJECT NOT FOUND!!!" );
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
//        System.out.println(projectsString);
        out.println(stringBuilder.toString());
//        httpExchange.sendResponseHeaders(status, stringBuilder.toString().getBytes(StandardCharsets.UTF_8).length);
//        OutputStream os = httpExchange.getResponseBody();
//        os.write(stringBuilder.toString().getBytes(StandardCharsets.UTF_8));
//        os.close();

    }
}
