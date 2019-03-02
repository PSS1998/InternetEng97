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
            request.setAttribute("ID", tokenizer.nextToken());
            RequestDispatcher requestDispatcher;
            requestDispatcher = request.getRequestDispatcher("/projectDetails.jsp");
            requestDispatcher.forward(request, response);
        }



//        if(status == 403){
//            stringBuilder = new StringBuffer();
//            stringBuilder.append("PROJECT NOT FOUND");
//        }
//        response.setStatus(status, "PROJECT NOT FOUND!!!" );
//        response.setContentType("text/html; charset=UTF-8");
//        response.setCharacterEncoding("UTF-8");
//        PrintWriter out = response.getWriter();
//        out.println(stringBuilder.toString());


        RequestDispatcher requestDispatcher;
        requestDispatcher = request.getRequestDispatcher("/project.jsp");
        requestDispatcher.forward(request, response);


//        httpExchange.sendResponseHeaders(status, stringBuilder.toString().getBytes(StandardCharsets.UTF_8).length);
//        OutputStream os = httpExchange.getResponseBody();
//        os.write(stringBuilder.toString().getBytes(StandardCharsets.UTF_8));
//        os.close();

    }
}
