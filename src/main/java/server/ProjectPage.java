package server;

import java.io.IOException;
import java.io.OutputStream;

import com.sun.net.httpserver.HttpExchange;
import model.Data;


public class ProjectPage implements IPage {

    @Override
    public void HandleRequest(HttpExchange httpExchange) throws IOException {
//        StringBuffer stringBuilder = new StringBuffer();
//        stringBuilder.append("<!DOCTYPE html>\n" +
//                "<html lang=\"en\">\n" +
//                "<head>\n" +
//                "    <meta charset=\"UTF-8\">\n" +
//                "    <title>Projects</title>\n" +
//                "    <style>\n" +
//                "        table {\n" +
//                "            text-align: center;\n" +
//                "            margin: 0 auto;\n" +
//                "        }\n" +
//                "        td {\n" +
//                "            min-width: 300px;\n" +
//                "            margin: 5px 5px 5px 5px;\n" +
//                "            text-align: center;\n" +
//                "        }\n" +
//                "    </style>\n" +
//                "</head>\n" +
//                "<body>\n" +
//                "    <table>\n" +
//                "        <tr>\n" +
//                "            <th>id</th>\n" +
//                "            <th>title</th>\n" +
//                "            <th>budget</th>\n" +
//                "        </tr>\n" );
//        for (int i = 0;i<Data.projects.size();i++){
//            stringBuilder.append("        <tr>\n <td>");
//            stringBuilder.append(Data.projects.get(i).getId());
//            stringBuilder.append("</td>\\n<td>");
//            stringBuilder.append(Data.projects.get(i).getTitle());
//            stringBuilder.append("</td>\\n<td>");
//            stringBuilder.append(Data.projects.get(i).getBudget());
//            stringBuilder.append(" </td>\\n");
//        }
//        stringBuilder.append(
//                "        </tr>\n" +
//                        "    </table>\n" +
//                        "</body>\n" +
//                        "</html>");

        String ali="";
        for (int i = 0;i<Data.projects.size();i++){

           ali+=Data.projects.get(i).getId();
            ali+=" ";
           ali+=Data.projects.get(i).getTitle();
           ali+=" ";
            ali+=Data.projects.get(i).getBudget();
            ali+="\n";

        }

        System.out.println(ali);

        httpExchange.sendResponseHeaders(200, ali.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(ali.getBytes());
        os.close();

    }

}
