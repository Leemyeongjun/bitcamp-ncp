package bitcamp.myapp.servlet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import com.google.gson.Gson;
import bitcamp.myapp.dao.StudentDao;
import bitcamp.myapp.vo.Student;

public class StudentServlet {

  private StudentDao memberDao;

  public StudentServlet(StudentDao memberDao) {
    this.memberDao = memberDao;
  }

  private void onInsert(DataInputStream in, DataOutputStream out) throws Exception {
    Student s = new Gson().fromJson(in.readUTF(), Student.class);
    this.memberDao.insert(s);
    out.writeUTF("200");
    out.writeUTF("success");
  }

  private void onFindAll(DataInputStream in, DataOutputStream out) throws Exception {
    out.writeUTF("200");
    out.writeUTF(new Gson().toJson(this.memberDao.findAll()));
  }

  private void onFindByNo(DataInputStream in, DataOutputStream out) throws Exception {
    int studentNo = new Gson().fromJson(in.readUTF(), int.class);

    Student s = this.memberDao.findByNo(studentNo);
    if (s == null) {
      out.writeUTF("400");
      return;
    }
    out.writeUTF("200");
    out.writeUTF(new Gson().toJson(s));
  }

  // 인스턴스 멤버(필드나 메서드)를 사용하지 않기 때문에
  // 그냥 스태틱 메서드로 두어라!
  private static String getLevelText(int level) {
    switch (level) {
      case 0: return "비전공자";
      case 1: return "준전공자";
      default: return "전공자";
    }
  }

  private void onUpdate(DataInputStream in, DataOutputStream out) throws Exception {
    Student student = new Gson().fromJson(in.readUTF(), Student.class);

    Student old = this.memberDao.findByNo(student.getNo());
    if (old == null) {
      out.writeUTF("400");
      return;
    }
    this.memberDao.update(student);
    out.writeUTF("200");
    out.writeUTF("success");
  }

  private void onDelete(DataInputStream in, DataOutputStream out) throws Exception {
    Student student = new Gson().fromJson(in.readUTF(), Student.class);

    Student s = this.memberDao.findByNo(student.getNo());
    if (s == null) {
      out.writeUTF("400");
      return;
    }

    this.memberDao.delete(s);
    out.writeUTF("200");
    out.writeUTF("success");
  }

  public void service(DataInputStream in, DataOutputStream out) throws Exception {
    try {
      // 클라이언트가 요구하는 액션을 읽는다.
      String action = in.readUTF();

      switch (action) {
        case "insert": this.onInsert(in, out); break;
        case "findAll": this.onFindAll(in, out); break;
        case "findByNo": this.onFindByNo(in, out); break;
        case "update": this.onUpdate(in, out); break;
        case "delete": this.onDelete(in, out); break;
        default:
          System.out.println("잘못된 메뉴 번호 입니다.");
      }
    } catch (Exception e) {
      out.writeUTF("500");
    }
  }
}
