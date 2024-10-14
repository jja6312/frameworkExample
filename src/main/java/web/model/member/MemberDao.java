package web.model.member;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.*;

public class MemberDao {
    DataSource ds;
    public MemberDao() throws MyException{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/jesdb";
            String user = "root";
            String pwd = "1234";

            Context initCtx = new InitialContext();
            Context envCtx = (Context)initCtx.lookup("java:comp/env");
            ds = (DataSource)envCtx.lookup("jdbc/JesDB");

        }catch(Exception e){
            throw new MyException("DB 연결 오류");
        }
    }

    public Member login(String id, String pw) throws MyException {
        Connection connection = null;

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            connection = ds.getConnection();
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

    public void deleteMember(String id) throws MyException {
        Connection connection = null;
        PreparedStatement pstmt = null;
        try {
            connection = ds.getConnection();
            String sql = "delete from member where id=?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.executeUpdate();

        }catch (Exception e){
            throw new MyException("delete 오류");
        }finally{
            try {
                pstmt.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }
}
