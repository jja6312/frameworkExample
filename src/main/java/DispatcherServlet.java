import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import web.controller.Controller;
import web.controller.XmlBeanFactory;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/main")
public class DispatcherServlet  extends HttpServlet {
    Map<String, Controller> beans;

    @Override
    public void init() throws ServletException {
        try {
            String controllerPath = getServletContext().getRealPath("/WEB-INF/beans.xml");
            String modelPath = getServletContext().getRealPath("/WEB-INF/beans2.xml");
            XmlBeanFactory factory = new XmlBeanFactory(controllerPath,modelPath);
            beans = factory.getBeans();
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     process(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     process(request,response);
    }

    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();
        JsonObject json = (JsonObject)JsonParser.parseReader(request.getReader());
        String sign = json.get("sign").getAsString();
        System.out.println("sign:"+sign);

        JsonObject retJson = new JsonObject();

        beans.get(sign).service(request,response,json, retJson);
        out.append(retJson.toString());
        out.close();
    }


}
