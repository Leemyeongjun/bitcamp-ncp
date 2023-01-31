package bitcamp.myapp.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Date;
import java.util.Iterator;
import java.util.List;
import bitcamp.myapp.vo.Board;

public class BoardDao {

  List<Board> list;
  int lastNo;

  public BoardDao(List<Board> list) {
    this.list = list;
  }

  public void insert(Board board) {
    board.setNo(++lastNo);
    board.setCreatedDate(new Date(System.currentTimeMillis()).toString());
    list.add(board);
  }

  public Board[] findAll() {
    Board[] boards = new Board[list.size()];
    Iterator<Board> i = list.iterator();
    int index = 0;
    while (i.hasNext()) {
      boards[index++] = i.next();
    }
    return boards;
  }

  public Board findByNo(int no) {
    Board b = new Board();
    b.setNo(no);

    int index = list.indexOf(b);
    if (index == -1) {
      return null;
    }

    return list.get(index);
  }

  public void update(Board b) {
    int index = list.indexOf(b);
    list.set(index, b);
  }

  public boolean delete(Board b) {
    return list.remove(b);
  }

  public void save(String filename) {
    try (BufferedWriter out = new BufferedWriter(new FileWriter(filename, true))) {

      for (int i = 0; i < list.size(); i++) {
        Board b = list.get(i);
        String data = "";
        data = b.getNo() + "," + b.getTitle() + "," + b.getContent() + "," + b.getPassword()  + "," + b.getViewCount() + "," + b.getCreatedDate();
        out.write(data);
        out.newLine();
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @SuppressWarnings("unchecked")
  public void load(String filename) {
    if (list.size() > 0) {
      return;
    }

    try (BufferedReader in = new BufferedReader(new FileReader(filename))) {

      while (in.readLine() != null) {
        String line = in.readLine();
        String[] value = line.split(",");

        Board b = new Board();

        b.setNo(Integer.parseInt(value[0]));
        b.setTitle(value[1]);
        b.setContent(value[2]);
        b.setPassword(value[3]);
        b.setViewCount(Integer.parseInt(value[4]));
        b.setCreatedDate(value[5]);

        list.add(b);
      }

      if (list.size() > 0) {
        lastNo = list.get(list.size() - 1).getNo();
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}























