package com.server;

import com.sun.net.httpserver.HttpExchange;
import model.Data;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.StringTokenizer;


public class project implements IPage {

    @Override
    public void HandleRequest(HttpExchange httpExchange) throws IOException {
        int status=200;
        StringBuffer stringBuilder = new StringBuffer();


        StringTokenizer tokenizer = new StringTokenizer(httpExchange.getRequestURI().getPath(), "/");
        String context = tokenizer.nextToken();
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
                stringBuilder.append("        <tr> <td>");
                stringBuilder.append(Data.projects.get(i).getId());
                stringBuilder.append("</td><td>");
                stringBuilder.append(Data.projects.get(i).getTitle());
                stringBuilder.append("</td><td>");
                stringBuilder.append(Data.projects.get(i).getBudget());
                stringBuilder.append(" </td>" + "</tr>");

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
        httpExchange.sendResponseHeaders(status, stringBuilder.toString().getBytes(StandardCharsets.UTF_8).length);
        OutputStream os = httpExchange.getResponseBody();
        os.write(stringBuilder.toString().getBytes(StandardCharsets.UTF_8));
        os.close();

    }

}
