package bitcamp.myapp.dao;

import java.util.Arrays;
import bitcamp.myapp.vo.Member;

public class MemberDao {
  //모든 인스턴스가 공유하는 데이터를 스태틱 필드로 만든다.
  // 특히 데이터를 조회하는 용으로 사용하는 final 변수는 스태틱 필드로 만들어야 한다.
  private static final int SIZE = 100;

  private int count;
  private Member[] members = new Member[SIZE];

  public void insert(Member member) {
    this.members[this.count++] = member;
  }

  public Member[] findAll() {
    return Arrays.copyOf(members, count);
  }

  public Member findByNo(int no) {
    for (int i = 0; i < this.count; i++) {
      if (this.members[i].getNo() == no) {
        return this.members[i];
      }
    }
    return null;
  }

  public void update(Member member) {
    this.members[this.indexOf(member)] = member;
  }

  public void delete(Member member) {
    for (int i = this.indexOf(member) + 1; i < this.count; i++) {
      this.members[i - 1] = this.members[i];
    }
    this.members[--this.count] = null; // 레퍼런스 카운트를 줄인다.
  }

  public int indexOf(Member m) {
    for (int i = 0; i < this.count; i++) {
      if (this.members[i].getNo() == m.getNo()) {
        return i;
      }
    }
    return -1;
  }
}
