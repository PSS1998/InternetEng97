package server;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.URL;
import java.util.List;
import java.util.StringTokenizer;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import model.Data;
import model.Project;
import model.Skill;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;



public class ReflectionServer {
	public void startServer() throws Exception {

		HttpServer server = HttpServer.create(new InetSocketAddress(8085), 0);
        server.createContext("/phase2", new MyHandler());
        server.setExecutor(null);
        server.start();

		String projectsString = getHTML("http://142.93.134.194:8000/joboonja/project");

		ObjectMapper mapper = new ObjectMapper();
		Data.projects = mapper.readValue(projectsString, new TypeReference<List<Project>>(){});


		String skillsString = getHTML("http://142.93.134.194:8000/joboonja/skill");

		Data.skills = mapper.readValue(skillsString, new TypeReference<List<Skill>>(){});


	}


	public static String getHTML(String urlToRead) throws Exception {
		StringBuilder result = new StringBuilder();
		URL url = new URL(urlToRead);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String line;
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		rd.close();
		return result.toString();
	}

    class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange httpExchange) throws IOException {
        	StringTokenizer tokenizer = new StringTokenizer(httpExchange.getRequestURI().getPath(), "/");
        	String context = tokenizer.nextToken();
        	String page = tokenizer.nextToken();
        	String id=null;
			if(tokenizer.hasMoreElements()){
				id = tokenizer.nextToken();
			}
            Class<IPage> pageClass;
			try {
				if(id == null) {
					pageClass = (Class <IPage>) Class.forName("server." + page);
					IPage newInstance = pageClass.getDeclaredConstructor().newInstance();
					newInstance.HandleRequest(httpExchange);
				}
				else if(page.equals("project")){
					pageClass = (Class <IPage>) Class.forName("server.project");
					IPage newInstance = pageClass.getDeclaredConstructor().newInstance();
					newInstance.HandleRequest(httpExchange);
				}else if(page.equals("user")){
					pageClass = (Class <IPage>) Class.forName("server.user");
					IPage newInstance = pageClass.getDeclaredConstructor().newInstance();
					newInstance.HandleRequest(httpExchange);
				}
			} catch (ClassNotFoundException |
					InstantiationException | 
					IllegalAccessException | 
					IllegalArgumentException | 
					InvocationTargetException | 
					NoSuchMethodException | 
					SecurityException e) {
				e.printStackTrace();
		        String response = 
		        		"<html>"
		        		+ "<body>Page \""+ page + "\" not found.</body>"
		        		+ "</html>";
		        httpExchange.sendResponseHeaders(404, response.length());
		        OutputStream os = httpExchange.getResponseBody();
		        os.write(response.getBytes());
		        os.close();
			}
        }
    }
    
    public static void main(String[] args) throws Exception {
		ReflectionServer server = new ReflectionServer();
		server.startServer();

	}
}
