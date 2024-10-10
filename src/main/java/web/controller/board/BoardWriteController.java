package web.controller.board;

import com.google.gson.JsonObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import web.controller.Controller;
import web.model.member.Member;

import java.io.IOException;

public class BoardWriteController implements Controller {

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response, JsonObject json, JsonObject retJson) throws ServletException, IOException {
        //글쓰기
        HttpSession session = request.getSession(false);
        if(session!=null){
            Member member = (Member)session.getAttribute("member");
            if(member!=null){//이미 로그인된 사용자일때
                String boardWriter = json.get("boardWriter").getAsString();
                if(member.getName().equals(boardWriter)){
                    String boardContent = json.get("boardContent").getAsString();

                    System.out.println("boardWriter:"+boardWriter);
                    System.out.println("boardContent:"+boardContent);
                    retJson.addProperty("msg","글쓰기 완료");
                }else{//hacking
                    retJson.addProperty("msg","정상 요청이 아닙니다");
                }
            }else{
                retJson.addProperty("msg", "로그인 먼저 해주세요");
            }

        }else{
            retJson.addProperty("msg","로그인 먼저 해주세요");
        }
    }
}
