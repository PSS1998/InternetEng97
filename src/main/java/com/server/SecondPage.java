package com.server;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;


public class SecondPage implements IPage {

	@Override
	public void HandleRequest(HttpExchange httpExchange) throws IOException {
        String response = 
        		"<html>"
        		+ "<body><h1>This Is The Second Page!<h1></body>"
        		+ "</html>";

        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
	}

}
