package web.controller.member;

import com.google.gson.JsonObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import web.controller.Controller;
import web.controller.XmlBeanFactory;
import web.model.member.MemberService;
import web.model.member.MyException;

import java.io.IOException;

public class MemberDeleteController implements Controller {
    private MemberService memberService;

    public MemberDeleteController() {
        memberService = (MemberService) XmlBeanFactory.getBeans2().get("memberService");
    }

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response, JsonObject json, JsonObject retJson) throws ServletException, IOException {
        String id = json.get("id").getAsString();
        try {
            memberService.deleteMember(id);
        } catch (MyException e) {
            retJson.addProperty("msg", e.getMessage());
            e.printStackTrace();
        }
    }
}
