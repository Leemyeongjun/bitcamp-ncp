-- 1:1문의
DROP TABLE IF EXISTS hl_inquiry RESTRICT;

-- 게시판유형
DROP TABLE IF EXISTS hl_board_type RESTRICT;

-- 좋아요
DROP TABLE IF EXISTS hl_like RESTRICT;

-- 회원
DROP TABLE IF EXISTS hl_member RESTRICT;

-- 팔로워
DROP TABLE IF EXISTS hl_follow RESTRICT;

-- 회원알림설정
DROP TABLE IF EXISTS hl_alarm RESTRICT;

-- 댓글
DROP TABLE IF EXISTS hl_comment RESTRICT;

-- 게시글
DROP TABLE IF EXISTS hl_post RESTRICT;

-- 대댓글
DROP TABLE IF EXISTS hl_c_comment RESTRICT;

-- 알림로그
DROP TABLE IF EXISTS hl_alarm_log RESTRICT;

-- 첨부파일
DROP TABLE IF EXISTS hl_file RESTRICT;

-- 신고
DROP TABLE IF EXISTS hl_report RESTRICT;

-- 알림유형
DROP TABLE IF EXISTS hl_alarm_type RESTRICT;

-- 1:1문의
CREATE TABLE hl_inquiry (
  inquiry_id   INTEGER      NOT NULL COMMENT '문의번호', -- 문의번호
  member_id    INTEGER      NOT NULL COMMENT '회원번호', -- 회원번호
  title        VARCHAR(100) NOT NULL COMMENT '문의제목', -- 문의제목
  content      VARCHAR(255) NOT NULL COMMENT '문의내용', -- 문의내용
  created_date DATE         NOT NULL COMMENT '문의일', -- 문의일
  answer       VARCHAR(255) NULL     COMMENT '답변', -- 답변
  answer_bool  INTEGER      NOT NULL COMMENT '답변여부', -- 답변여부
  answer_date  DATE         NOT NULL COMMENT '답변일' -- 답변일
)
COMMENT '1:1문의';

-- 1:1문의
ALTER TABLE hl_inquiry
  ADD CONSTRAINT PK_hl_inquiry -- 1:1문의 기본키
  PRIMARY KEY (
  inquiry_id -- 문의번호
  );

-- 1:1문의 인덱스
CREATE INDEX IX_hl_inquiry
  ON hl_inquiry( -- 1:1문의
    title ASC,   -- 문의제목
    content ASC  -- 문의내용
  );

-- 게시판유형
CREATE TABLE hl_board_type (
  board_type_id INTEGER     NOT NULL COMMENT '게시판유형번호', -- 게시판유형번호
  board_name    VARCHAR(50) NOT NULL COMMENT '게시판이름' -- 게시판이름
)
COMMENT '게시판유형';

-- 게시판유형
ALTER TABLE hl_board_type
  ADD CONSTRAINT PK_hl_board_type -- 게시판유형 기본키
  PRIMARY KEY (
  board_type_id -- 게시판유형번호
  );

-- 게시판유형 유니크 인덱스
CREATE UNIQUE INDEX UIX_hl_board_type
  ON hl_board_type ( -- 게시판유형
    board_name ASC -- 게시판이름
  );

-- 좋아요
CREATE TABLE hl_like (
  post_id   INTEGER NOT NULL COMMENT '게시글번호', -- 게시글번호
  member_id INTEGER NOT NULL COMMENT '회원번호' -- 회원번호
)
COMMENT '좋아요';

-- 좋아요
ALTER TABLE hl_like
  ADD CONSTRAINT PK_hl_like -- 좋아요 기본키
  PRIMARY KEY (
  post_id,   -- 게시글번호
  member_id  -- 회원번호
  );

-- 회원
CREATE TABLE hl_member (
  member_id    INTEGER      NOT NULL COMMENT '회원번호', -- 회원번호
  email        VARCHAR(40)  NOT NULL COMMENT '아이디(이메일)', -- 아이디(이메일)
  nickname     VARCHAR(50)  NOT NULL COMMENT '별명', -- 별명
  name         VARCHAR(50)  NOT NULL COMMENT '이름', -- 이름
  tel          VARCHAR(30)  NULL     COMMENT '전화번호', -- 전화번호
  password     VARCHAR(20)  NOT NULL COMMENT '비밀번호', -- 비밀번호
  created_date DATE         NOT NULL COMMENT '가입일', -- 가입일
  state        INTEGER      NULL     COMMENT '상태', -- 상태
  introduce    VARCHAR(255) NULL     COMMENT '자기소개' -- 자기소개
)
COMMENT '회원';

-- 회원
ALTER TABLE hl_member
  ADD CONSTRAINT PK_hl_member -- 회원 기본키
  PRIMARY KEY (
  member_id -- 회원번호
  );

-- 회원 유니크 인덱스
CREATE UNIQUE INDEX UIX_hl_member
  ON hl_member ( -- 회원
    email ASC,    -- 아이디(이메일)
    nickname ASC  -- 별명
  );

-- 회원 인덱스
CREATE INDEX IX_hl_member
  ON hl_member( -- 회원
    nickname ASC, -- 별명
    name ASC      -- 이름
  );

-- 팔로워
CREATE TABLE hl_follow (
  member_id  INTEGER NOT NULL COMMENT '회원번호', -- 회원번호
  member_id2 INTEGER NOT NULL COMMENT '팔로잉회원번호' -- 팔로잉회원번호
)
COMMENT '팔로워';

-- 팔로워
ALTER TABLE hl_follow
  ADD CONSTRAINT PK_hl_follow -- 팔로워 기본키
  PRIMARY KEY (
  member_id,  -- 회원번호
  member_id2  -- 팔로잉회원번호
  );

-- 회원알림설정
CREATE TABLE hl_alarm (
  member_id     INTEGER NOT NULL COMMENT '회원번호', -- 회원번호
  alarm_type_id INTEGER NOT NULL COMMENT '알림유형번호' -- 알림유형번호
)
COMMENT '회원알림설정';

-- 회원알림설정
ALTER TABLE hl_alarm
  ADD CONSTRAINT PK_hl_alarm -- 회원알림설정 기본키
  PRIMARY KEY (
  member_id,     -- 회원번호
  alarm_type_id  -- 알림유형번호
  );

-- 댓글
CREATE TABLE hl_comment (
  comment_id   INTEGER      NOT NULL COMMENT '댓글번호', -- 댓글번호
  post_id      INTEGER      NOT NULL COMMENT '게시글번호', -- 게시글번호
  member_id    INTEGER      NOT NULL COMMENT '회원번호', -- 회원번호
  content      VARCHAR(255) NOT NULL COMMENT '댓글내용', -- 댓글내용
  created_date DATE         NOT NULL COMMENT '댓글작성일' -- 댓글작성일
)
COMMENT '댓글';

-- 댓글
ALTER TABLE hl_comment
  ADD CONSTRAINT PK_hl_comment -- 댓글 기본키
  PRIMARY KEY (
  comment_id -- 댓글번호
  );

-- 게시글
CREATE TABLE hl_post (
  post_id       INTEGER      NOT NULL COMMENT '게시글번호', -- 게시글번호
  board_type_id INTEGER      NOT NULL COMMENT '게시판유형번호', -- 게시판유형번호
  member_id     INTEGER      NOT NULL COMMENT '회원번호', -- 회원번호
  title         VARCHAR(100) NOT NULL COMMENT '게시글명', -- 게시글명
  content       VARCHAR(255) NULL     COMMENT '게시글내용', -- 게시글내용
  created_date  DATE         NOT NULL COMMENT '등록일', -- 등록일
  view_no       INTEGER      NOT NULL COMMENT '조회수', -- 조회수
  location      VARCHAR(255) NULL     COMMENT '위치' -- 위치
)
COMMENT '게시글';

-- 게시글
ALTER TABLE hl_post
  ADD CONSTRAINT PK_hl_post -- 게시글 기본키
  PRIMARY KEY (
  post_id -- 게시글번호
  );

-- 게시글 인덱스
CREATE INDEX IX_hl_post
  ON hl_post( -- 게시글
    title ASC,   -- 게시글명
    content ASC  -- 게시글내용
  );

-- 대댓글
CREATE TABLE hl_c_comment (
  c_comment_id INTEGER      NOT NULL COMMENT '대댓글번호', -- 대댓글번호
  member_id    INTEGER      NOT NULL COMMENT '회원번호', -- 회원번호
  comment_id   INTEGER      NOT NULL COMMENT '댓글번호', -- 댓글번호
  content      VARCHAR(255) NOT NULL COMMENT '대댓글내용', -- 대댓글내용
  created_date DATE         NOT NULL COMMENT '대댓글작성일' -- 대댓글작성일
)
COMMENT '대댓글';

-- 대댓글
ALTER TABLE hl_c_comment
  ADD CONSTRAINT PK_hl_c_comment -- 대댓글 기본키
  PRIMARY KEY (
  c_comment_id -- 대댓글번호
  );

-- 알림로그
CREATE TABLE hl_alarm_log (
  alarm_log_id  INTEGER      NOT NULL COMMENT '알림로그번호', -- 알림로그번호
  alarm_type_id INTEGER      NOT NULL COMMENT '알림유형번호', -- 알림유형번호
  member_id     INTEGER      NOT NULL COMMENT '회원번호', -- 회원번호
  alarm_date    DATE         NOT NULL COMMENT '알림일', -- 알림일
  content       VARCHAR(255) NOT NULL COMMENT '알림내용', -- 알림내용
  alarm_bool    INTEGER      NOT NULL COMMENT '읽음여부' -- 읽음여부
)
COMMENT '알림로그';

-- 알림로그
ALTER TABLE hl_alarm_log
  ADD CONSTRAINT PK_hl_alarm_log -- 알림로그 기본키
  PRIMARY KEY (
  alarm_log_id -- 알림로그번호
  );

-- 첨부파일
CREATE TABLE hl_file (
  file_id      INTEGER     NOT NULL COMMENT '첨부파일번호', -- 첨부파일번호
  post_id      INTEGER     NOT NULL COMMENT '게시글번호', -- 게시글번호
  file_name    VARCHAR(50) NOT NULL COMMENT '파일명', -- 파일명
  created_date DATE        NOT NULL COMMENT '등록일', -- 등록일
  mimetype     VARCHAR(50) NOT NULL COMMENT 'MIMETYPE' -- MIMETYPE
)
COMMENT '첨부파일';

-- 첨부파일
ALTER TABLE hl_file
  ADD CONSTRAINT PK_hl_file -- 첨부파일 기본키
  PRIMARY KEY (
  file_id -- 첨부파일번호
  );

-- 신고
CREATE TABLE hl_report (
  report_id    INTEGER      NOT NULL COMMENT '신고번호', -- 신고번호
  post_id      INTEGER      NOT NULL COMMENT '게시글번호', -- 게시글번호
  member_id    INTEGER      NOT NULL COMMENT '회원번호', -- 회원번호
  title        VARCHAR(100) NOT NULL COMMENT '신고제목', -- 신고제목
  content      VARCHAR(255) NOT NULL COMMENT '신고내용', -- 신고내용
  created_date DATE         NOT NULL COMMENT '신고일', -- 신고일
  answer       VARCHAR(255) NULL     COMMENT '신고답변', -- 신고답변
  answer_date  DATE         NULL     COMMENT '답변일' -- 답변일
)
COMMENT '신고';

-- 신고
ALTER TABLE hl_report
  ADD CONSTRAINT PK_hl_report -- 신고 기본키
  PRIMARY KEY (
  report_id -- 신고번호
  );

-- 알림유형
CREATE TABLE hl_alarm_type (
  alarm_type_id INTEGER      NOT NULL COMMENT '알림유형번호', -- 알림유형번호
  alarm         VARCHAR(255) NOT NULL COMMENT '알림' -- 알림
)
COMMENT '알림유형';

-- 알림유형
ALTER TABLE hl_alarm_type
  ADD CONSTRAINT PK_hl_alarm_type -- 알림유형 기본키
  PRIMARY KEY (
  alarm_type_id -- 알림유형번호
  );

-- 1:1문의
ALTER TABLE hl_inquiry
  ADD CONSTRAINT FK_hl_member_TO_hl_inquiry -- 회원 -> 1:1문의
  FOREIGN KEY (
  member_id -- 회원번호
  )
  REFERENCES hl_member ( -- 회원
  member_id -- 회원번호
  );

-- 좋아요
ALTER TABLE hl_like
  ADD CONSTRAINT FK_hl_post_TO_hl_like -- 게시글 -> 좋아요
  FOREIGN KEY (
  post_id -- 게시글번호
  )
  REFERENCES hl_post ( -- 게시글
  post_id -- 게시글번호
  );

-- 좋아요
ALTER TABLE hl_like
  ADD CONSTRAINT FK_hl_member_TO_hl_like -- 회원 -> 좋아요
  FOREIGN KEY (
  member_id -- 회원번호
  )
  REFERENCES hl_member ( -- 회원
  member_id -- 회원번호
  );

-- 팔로워
ALTER TABLE hl_follow
  ADD CONSTRAINT FK_hl_member_TO_hl_follow -- 회원 -> 팔로워
  FOREIGN KEY (
  member_id -- 회원번호
  )
  REFERENCES hl_member ( -- 회원
  member_id -- 회원번호
  );

-- 팔로워
ALTER TABLE hl_follow
  ADD CONSTRAINT FK_hl_member_TO_hl_follow2 -- 회원 -> 팔로워2
  FOREIGN KEY (
  member_id2 -- 팔로잉회원번호
  )
  REFERENCES hl_member ( -- 회원
  member_id -- 회원번호
  );

-- 회원알림설정
ALTER TABLE hl_alarm
  ADD CONSTRAINT FK_hl_member_TO_hl_alarm -- 회원 -> 회원알림설정
  FOREIGN KEY (
  member_id -- 회원번호
  )
  REFERENCES hl_member ( -- 회원
  member_id -- 회원번호
  );

-- 회원알림설정
ALTER TABLE hl_alarm
  ADD CONSTRAINT FK_hl_alarm_type_TO_hl_alarm -- 알림유형 -> 회원알림설정
  FOREIGN KEY (
  alarm_type_id -- 알림유형번호
  )
  REFERENCES hl_alarm_type ( -- 알림유형
  alarm_type_id -- 알림유형번호
  );

-- 댓글
ALTER TABLE hl_comment
  ADD CONSTRAINT FK_hl_post_TO_hl_comment -- 게시글 -> 댓글
  FOREIGN KEY (
  post_id -- 게시글번호
  )
  REFERENCES hl_post ( -- 게시글
  post_id -- 게시글번호
  );

-- 댓글
ALTER TABLE hl_comment
  ADD CONSTRAINT FK_hl_member_TO_hl_comment -- 회원 -> 댓글
  FOREIGN KEY (
  member_id -- 회원번호
  )
  REFERENCES hl_member ( -- 회원
  member_id -- 회원번호
  );

-- 게시글
ALTER TABLE hl_post
  ADD CONSTRAINT FK_hl_member_TO_hl_post -- 회원 -> 게시글
  FOREIGN KEY (
  member_id -- 회원번호
  )
  REFERENCES hl_member ( -- 회원
  member_id -- 회원번호
  );

-- 게시글
ALTER TABLE hl_post
  ADD CONSTRAINT FK_hl_board_type_TO_hl_post -- 게시판유형 -> 게시글
  FOREIGN KEY (
  board_type_id -- 게시판유형번호
  )
  REFERENCES hl_board_type ( -- 게시판유형
  board_type_id -- 게시판유형번호
  );

-- 대댓글
ALTER TABLE hl_c_comment
  ADD CONSTRAINT FK_hl_comment_TO_hl_c_comment -- 댓글 -> 대댓글
  FOREIGN KEY (
  comment_id -- 댓글번호
  )
  REFERENCES hl_comment ( -- 댓글
  comment_id -- 댓글번호
  );

-- 대댓글
ALTER TABLE hl_c_comment
  ADD CONSTRAINT FK_hl_member_TO_hl_c_comment -- 회원 -> 대댓글
  FOREIGN KEY (
  member_id -- 회원번호
  )
  REFERENCES hl_member ( -- 회원
  member_id -- 회원번호
  );

-- 알림로그
ALTER TABLE hl_alarm_log
  ADD CONSTRAINT FK_hl_alarm_type_TO_hl_alarm_log -- 알림유형 -> 알림로그
  FOREIGN KEY (
  alarm_type_id -- 알림유형번호
  )
  REFERENCES hl_alarm_type ( -- 알림유형
  alarm_type_id -- 알림유형번호
  );

-- 알림로그
ALTER TABLE hl_alarm_log
  ADD CONSTRAINT FK_hl_member_TO_hl_alarm_log -- 회원 -> 알림로그
  FOREIGN KEY (
  member_id -- 회원번호
  )
  REFERENCES hl_member ( -- 회원
  member_id -- 회원번호
  );

-- 첨부파일
ALTER TABLE hl_file
  ADD CONSTRAINT FK_hl_post_TO_hl_file -- 게시글 -> 첨부파일
  FOREIGN KEY (
  post_id -- 게시글번호
  )
  REFERENCES hl_post ( -- 게시글
  post_id -- 게시글번호
  );

-- 신고
ALTER TABLE hl_report
  ADD CONSTRAINT FK_hl_post_TO_hl_report -- 게시글 -> 신고
  FOREIGN KEY (
  post_id -- 게시글번호
  )
  REFERENCES hl_post ( -- 게시글
  post_id -- 게시글번호
  );

-- 신고
ALTER TABLE hl_report
  ADD CONSTRAINT FK_hl_member_TO_hl_report -- 회원 -> 신고
  FOREIGN KEY (
  member_id -- 회원번호
  )
  REFERENCES hl_member ( -- 회원
  member_id -- 회원번호
  );