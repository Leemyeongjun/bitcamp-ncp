package bitcamp.bootapp.controller;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import bitcamp.bootapp.dao.TeacherDao;
import bitcamp.bootapp.vo.Teacher;

@CrossOrigin(origins = {"http://127.0.0.1:5500", "http://localhost:5500"})
@RestController
public class TeacherController {

  TeacherDao teacherDao = new TeacherDao();

  @PostMapping("/teachers")
  public Object addTeacher(
      Teacher teacher
      ) {

    teacher.setCreatedDate(new Date(System.currentTimeMillis()).toString());
    this.teacherDao.insert(teacher);

    // 응답 결과를 담을 맵 객체 준비
    Map<String,Object> contentMap = new HashMap<>();
    contentMap.put("status", "success");

    return contentMap;
  }


  @GetMapping("/teachers")
  public Object getTeachers() {

    Teacher[] teachers = this.teacherDao.findAll();

    Map<String,Object> contentMap = new HashMap<>();
    contentMap.put("status", "success");
    contentMap.put("data", teachers);

    return contentMap;
  }

  @GetMapping("/teachers/{no}")
  public Object getTeacher(Teacher teacher) {

    Teacher t = this.teacherDao.findByNo(teacher.getNo());

    // 응답 결과를 담을 맵 객체 준비
    Map<String,Object> contentMap = new HashMap<>();

    if (t == null) {
      contentMap.put("status", "failure");
      contentMap.put("data", "해당 번호의 회원이 없습니다.");
    } else {
      contentMap.put("status", "success");
      contentMap.put("data", t);
    }

    return contentMap;
  }

  @PutMapping("/teachers/{no}")
  public Object updateTeacher(

      Teacher teacher) {

    Map<String,Object> contentMap = new HashMap<>();

    Teacher old = this.teacherDao.findByNo(teacher.getNo());
    if (old == null) {
      contentMap.put("status", "failure");
      contentMap.put("data", "회원이 없습니다.");
      return contentMap;
    }

    teacher.setCreatedDate(old.getCreatedDate());

    this.teacherDao.update(teacher);

    contentMap.put("status", "success");

    return contentMap;
  }

  @DeleteMapping("/teachers/{teacherNo}")
  public Object deleteTeacher(

      @PathVariable int teacherNo) {

    Teacher t = this.teacherDao.findByNo(teacherNo);

    // 응답 결과를 담을 맵 객체 준비
    Map<String,Object> contentMap = new HashMap<>();

    if (t == null) {
      contentMap.put("status", "failure");
      contentMap.put("data", "게시글이 없거나 암호가 맞지 않습니다.");
    } else {
      this.teacherDao.delete(t);
      contentMap.put("status", "success");
    }

    return contentMap;
  }
}
