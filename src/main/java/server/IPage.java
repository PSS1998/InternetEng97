package server;

import java.io.IOException;

import com.sun.net.httpserver.HttpExchange;

public interface IPage {
	public void HandleRequest(HttpExchange httpExchange) throws IOException;
}
