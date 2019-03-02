package com.control;

import com.model.Data;
import com.model.Skill;
import org.codehaus.jackson.type.TypeReference;
import org.codehaus.jackson.map.ObjectMapper;

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

@WebServlet(urlPatterns = {"/user", "/user/*"})
public class user extends HttpServlet {

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
            if (Data.user.getId().equals(id)) {
                RequestDispatcher requestDispatcher;
                requestDispatcher = request.getRequestDispatcher("/userLoggedin.jsp");
                requestDispatcher.forward(request, response);
            } else {
                request.setAttribute("ID", id);
                RequestDispatcher requestDispatcher;
                requestDispatcher = request.getRequestDispatcher("/userGuest.jsp");
                requestDispatcher.forward(request, response);
            }
        }

        RequestDispatcher requestDispatcher;
        requestDispatcher = request.getRequestDispatcher("/users.jsp");
        requestDispatcher.forward(request, response);

    }
}
