package bitcamp.myapp;

import java.sql.Date;

public class BoardHandler {
  static final int Size = 100;
  int count;
  int viewCount;

  Board[] boards = new Board[Size];

  String title;

  BoardHandler(String title) {
    this.title = title;
  }

  void inputBoard() {
    Board b = new Board();

    b.no = Prompt.inputInt("번호? ");
    b.title = Prompt.inputString("제목? ");
    b.contents = Prompt.inputString("내용? ");
    b.createDate = new Date(System.currentTimeMillis()).toString();

    this.boards[count++] = b;
  }

  void printBoards() {
    System.out.println("번호\t제목\t작성일\t\t조회수");

    for (int i = 0; i < count; i++) {
      Board b = this.boards[i];
      System.out.printf("%d\t%s\t%s\t%d\n", b.no, b.title, b.createDate, b.view);
    }
  }

  void printBoard() {
    int boardNo = Prompt.inputInt("번호? ");

    Board b = this.findByNo(boardNo);

    if (b == null) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return;
    }

    System.out.printf("제목: %s\n", b.title);
    System.out.printf("내용: %s\n", b.contents);
    System.out.printf("작성일: %s\n", b.createDate);
    System.out.printf("조회수: %s\n", ++b.view);
  }

  void modifyBoard() {
    int boardNo = Prompt.inputInt("번호? ");

    Board old = this.findByNo(boardNo);

    if (old == null) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return;
    }

    Board b = new Board();
    b.no = old.no;
    b.createDate = old.createDate;
    b.view = old.view;
    b.title = Prompt.inputString(String.format("제목(%s)?", old.title));
    b.contents = Prompt.inputString(String.format("내용(%s)?", old.contents));

    String str = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");
    if (str.equalsIgnoreCase("Y")) {
      this.boards[this.indexOf(old)] = b;
      System.out.println("변경 완료!");
    } else {
      System.out.println("변경 취소!");
    }
  }

  void deleteBoard() {
    int boardNo = Prompt.inputInt("번호? ");

    Board b = this.findByNo(boardNo);

    if (b == null) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return;
    }

    String str = Prompt.inputString("정말 삭제하시겠습니까?(y/N) ");
    if (!str.equalsIgnoreCase("Y")) {
      System.out.println("삭제 취소했습니다.");
      return;
    }

    for (int i = this.indexOf(b) + 1; i < this.count; i++) {
      this.boards[i - 1] = this.boards[i];
    }
    this.boards[--this.count] = null; // 레퍼런스 카운트를 줄인다.

    System.out.println("삭제했습니다.");
  }

  Board findByNo(int no) {
    for (int i = 0; i < this.count; i++) {
      if (this.boards[i].no == no) {
        return this.boards[i];
      }
    }
    return null;
  }

  int indexOf(Board b) {
    for (int i = 0; i < this.count; i++) {
      if (this.boards[i].no == b.no) {
        return i;
      }
    }
    return -1;
  }

  void service() {
    while (true) {
      System.out.printf("[%s]\n", this.title);
      System.out.println("1. 입력");
      System.out.println("2. 목록");
      System.out.println("3. 조회");
      System.out.println("4. 변경");
      System.out.println("5. 삭제");
      System.out.println("0. 이전");
      int menuNo = Prompt.inputInt(String.format("%s> ", this.title));

      switch (menuNo) {
        case 0: return;
        case 1: this.inputBoard(); break;
        case 2: this.printBoards(); break;
        case 3: this.printBoard(); break;
        case 4: this.modifyBoard(); break;
        case 5: this.deleteBoard(); break;
        default:
          System.out.println("잘못된 메뉴 번호 입니다.");
      }
    }
  }
}
