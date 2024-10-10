package web.controller;

import com.google.gson.JsonObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface Controller {
    public void service(HttpServletRequest request
            , HttpServletResponse response
            , JsonObject json
            , JsonObject retJson
    ) throws ServletException, IOException;
}
