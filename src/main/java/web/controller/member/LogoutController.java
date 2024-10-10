package web.controller.member;

import com.google.gson.JsonObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import web.controller.Controller;
import web.controller.XmlBeanFactory;
import web.model.member.MemberService;

import java.io.IOException;

public class LogoutController implements Controller {
    private MemberService memberService;

    public LogoutController() {
        memberService = (MemberService) XmlBeanFactory.getBeans2().get("memberService");
    }

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response, JsonObject json, JsonObject retJson) throws ServletException, IOException {
        //세션 무효화
        HttpSession session = request.getSession(false);
        if (session != null) session.invalidate();

    }
}
