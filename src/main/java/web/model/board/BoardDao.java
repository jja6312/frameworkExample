package web.model.board;

import web.model.member.MyException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BoardDao {
    DataSource ds;

    public BoardDao() {
        try {
            Context ctx = new InitialContext();
            Context javaCtx = (Context)ctx.lookup("java:comp/env");
            ds = (DataSource) javaCtx.lookup("jdbc/JesDB");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void writeBoard(Board board) throws MyException {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try{
            conn = ds.getConnection();
            String sql = "insert into board(writer_id, content, reg_date) values (?,?,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, board.getWriteId());
            pstmt.setString(2, board.getContent());
            pstmt.setDate(3, new java.sql.Date(new Date().getTime()));
            pstmt.executeUpdate();
        }catch(Exception e){
            throw new MyException("글쓰기 오류");
        }finally{
            try {
                pstmt.close();
            conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public List<Board> listBoard() throws MyException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            conn = ds.getConnection();
            String sql = "select * from board";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            List<Board> list = new ArrayList<>();
            while(rs.next()){
                int bno = rs.getInt("bno");
                String write = rs.getString("writer_id");
                String content = rs.getString("content");
                Date regDate = rs.getDate("reg_date");
                list.add(new Board(bno,write,content,regDate));
            }
            return list;
        }catch(Exception e){
            throw new MyException("글쓰기 오류");
        }finally{
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
