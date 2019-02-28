package com.control;

import com.model.Data;
import com.model.Skill;
import org.codehaus.jackson.type.TypeReference;
import org.codehaus.jackson.map.ObjectMapper;

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

@WebServlet(urlPatterns = {"/user/*"})
public class user extends HttpServlet {

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

        StringBuffer stringBuilder = new StringBuffer();
        StringTokenizer tokenizer = new StringTokenizer(request.getRequestURI(), "/");
//        String context = tokenizer.nextToken();
        String page = tokenizer.nextToken();
        String id=null;
        if(tokenizer.hasMoreElements()){
            id = tokenizer.nextToken();
        }
        stringBuilder.append("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>User</title>\n" +
                "</head>");
        if(id .equals(Data.user.getId())) {
            stringBuilder.append("<li>id:" );
            stringBuilder.append(Data.user.getId()+"</li>"+
                    "                       <li>first name:");
            stringBuilder.append(Data.user.getFirstName()+"</li>" +
                    "                        <li>last name:");
            stringBuilder.append(Data.user.getLastName()+"</li>" +
                    "                       <li>jobTitle:");
            stringBuilder.append(Data.user.getJobTitle()+"<" +
                    "/li>" +
                    "                       <li>bio:");
            stringBuilder.append(Data.user.getBio()+"</li>");
        }
        else{
            stringBuilder.append("<li>id:...</li>\\n\" +\n" +
                    "                \"        <li>first name: ...</li>\\n\" +\n" +
                    "                \"        <li>last name: ...</li>\\n\" +\n" +
                    "                \"        <li>jobTitle: ...</li>\\n\" +\n" +
                    "                \"        <li>bio: ...</li>\\n\" +");
        }

        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.println(stringBuilder.toString());

//        httpExchange.sendResponseHeaders(200, stringBuilder.toString().getBytes(StandardCharsets.UTF_8).length);
//        OutputStream os = httpExchange.getResponseBody();
//        os.write(stringBuilder.toString().getBytes(StandardCharsets.UTF_8));
//        os.close();

    }
}
