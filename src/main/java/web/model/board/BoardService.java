package web.model.board;

import web.controller.XmlBeanFactory;
import web.model.member.MyException;

import java.util.List;

public class BoardService {
    private BoardDao boardDao;
    public BoardService() {
        boardDao = (BoardDao) XmlBeanFactory.getBeans2().get("boardDao");
    }

    public void writeBoard(Board board) throws MyException {
        boardDao.writeBoard(board);
    }

    public List<Board> listBoard() throws MyException {

        return boardDao.listBoard();
    }
}
