package web.model.board;

import java.util.Date;

public class Board {
    private int bno;
    private String writeId;
    private String content;
    private Date regDate;


    public Board(String writeId, String content) {
        this.writeId = writeId;
        this.content = content;
    }

    public Board(int bno, String writeId, String content, Date regDate) {
        this.bno = bno;
        this.writeId = writeId;
        this.content = content;
        this.regDate = regDate;
    }


    public int getBno() {
        return bno;
    }

    public void setBno(int bno) {
        this.bno = bno;
    }

    public String getWriteId() {
        return writeId;
    }

    public void setWriteId(String writeId) {
        this.writeId = writeId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    @Override
    public String toString() {
        return "Board{" +
                "bno=" + bno +
                ", writeId='" + writeId + '\'' +
                ", content='" + content + '\'' +
                ", regDate=" + regDate +
                '}';
    }
}
