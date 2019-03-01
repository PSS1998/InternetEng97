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

@WebServlet(urlPatterns = {"/user", "/user/*"})
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
        if(tokenizer.hasMoreElements()) {
            id = tokenizer.nextToken();
            stringBuilder.append("<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <title>User</title>\n" +
                    "</head>");
            Boolean userFound = false;
            for (int i=0; i<Data.users.size(); i++) {
                if (id.equals(Data.users.get(i).getId())) {
                    userFound = true;
                    stringBuilder.append("<li>id:");
                    stringBuilder.append(Data.users.get(i).getId() + "</li>" +
                            "                       <li>first name:");
                    stringBuilder.append(Data.users.get(i).getFirstName() + "</li>" +
                            "                        <li>last name:");
                    stringBuilder.append(Data.users.get(i).getLastName() + "</li>" +
                            "                       <li>jobTitle:");
                    stringBuilder.append(Data.users.get(i).getJobTitle() + "<" +
                            "/li>" +
                            "                       <li>bio:");
                    stringBuilder.append(Data.users.get(i).getBio() + "</li>");
                }
            }
            if(!userFound) {
                stringBuilder.append("<li>id:...</li>" +
                        "                        <li>first name: ...</li>" +
                        "                        <li>last name: ...</li>" +
                        "                        <li>jobTitle: ...</li>" +
                        "                        <li>bio: ...</li>");
            }


            response.setContentType("text/html; charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            out.println(stringBuilder.toString());
        }
        else{

            stringBuilder.append("<!DOCTYPE html>" +
                    "<html lang=\"en\">" +
                    "<head>" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <title>Users</title>\n" +
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

            for (int i = 0; i < Data.users.size(); i++) {
                if(!Data.users.get(i).getId().equals(Data.user.getId())) {
                    stringBuilder.append("        <tr> <td>");
                    stringBuilder.append(Data.users.get(i).getId());
                    stringBuilder.append("</td><td>");
                    stringBuilder.append(Data.users.get(i).getJobTitle());
                    stringBuilder.append("</td><td>");
                    stringBuilder.append(Data.users.get(i).printSkills());
                    stringBuilder.append(" </td>" + "</tr>");
                }
            }

            stringBuilder.append(

                    "    </table>\n" +
                            "</body>\n" +
                            "</html>");

            response.setContentType("text/html; charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            out.println(stringBuilder.toString());
        }

//        httpExchange.sendResponseHeaders(200, stringBuilder.toString().getBytes(StandardCharsets.UTF_8).length);
//        OutputStream os = httpExchange.getResponseBody();
//        os.write(stringBuilder.toString().getBytes(StandardCharsets.UTF_8));
//        os.close();

    }
}
