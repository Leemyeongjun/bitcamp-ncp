package bitcamp.bootapp.vo;

public class Teacher {
  private int no;
  private String name;
  private String tel;
  private String email;          // 이메일
  private String education;      // 최종학력
  private String university;     // 대학
  private String major;          // 전공
  private  int tuition;          // 수강료
  private String createdDate;

  public int getNo() {
    return no;
  }
  public void setNo(int no) {
    this.no = no;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getTel() {
    return tel;
  }
  public void setTel(String tel) {
    this.tel = tel;
  }
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public String getEducation() {
    return education;
  }
  public void setEducation(String education) {
    this.education = education;
  }
  public String getUniversity() {
    return university;
  }
  public void setUniversity(String university) {
    this.university = university;
  }
  public String getMajor() {
    return major;
  }
  public void setMajor(String major) {
    this.major = major;
  }
  public int getTuition() {
    return tuition;
  }
  public void setTuition(int tuition) {
    this.tuition = tuition;
  }
  public String getCreatedDate() {
    return createdDate;
  }
  public void setCreatedDate(String createdDate) {
    this.createdDate = createdDate;
  }
}
