package web.model.member;

import java.sql.*;

public class MemberDao {
    Connection connection;
    public MemberDao() throws MyException{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/jesdb";
            String user = "ssafy";
            String pwd = "1234";
            connection = DriverManager.getConnection(url, user, pwd);
        }catch(Exception e){
            throw new MyException("DB 연결 오류");
        }
    }

    public Member login(String id, String pw) throws MyException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String sql = "select name from member where id=? and pw=?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.setString(2, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                String name = rs.getString(1);
                return new Member(id, pw, name);
            }
        }catch (Exception e){
            throw new MyException("login 오류");
        }finally{
            try {
                rs.close();
                pstmt.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }

        return null;

    }
}
