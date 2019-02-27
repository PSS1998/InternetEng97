package com.server;

import com.sun.net.httpserver.HttpExchange;
import model.Data;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.StringTokenizer;


public class user implements IPage {

    @Override
    public void HandleRequest(HttpExchange httpExchange) throws IOException {
        StringBuffer stringBuilder = new StringBuffer();
        StringTokenizer tokenizer = new StringTokenizer(httpExchange.getRequestURI().getPath(), "/");
        String context = tokenizer.nextToken();
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



        httpExchange.sendResponseHeaders(200, stringBuilder.toString().getBytes(StandardCharsets.UTF_8).length);
        OutputStream os = httpExchange.getResponseBody();
        os.write(stringBuilder.toString().getBytes(StandardCharsets.UTF_8));
        os.close();

    }

}

