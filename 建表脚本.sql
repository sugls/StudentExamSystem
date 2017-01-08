-- 学生表
CREATE TABLE student
(
  stu_no     VARCHAR2(12) PRIMARY KEY,
  stu_name   VARCHAR2(20) NOT NULL,
  stu_sex    CHAR(1)      NOT NULL,
  stu_class  VARCHAR2(5)  NOT NULL,
  stu_passwd VARCHAR2(20) NOT NULL
);
-- 试题表
CREATE TABLE question
(
  que_no     NUMBER(3) PRIMARY KEY,
  que_note   VARCHAR2(20) NOT NULL,
  que_detail VARCHAR2(1024),
  que_choice VARCHAR2(1024),
  que_answer VARCHAR2(6),
  stage      CHAR(1)
);
-- 学生分数表
CREATE TABLE score
(
  stu_no NUMBER(12) REFERENCES student (stu_no),
  stage  CHAR(1),
  score  NUMBER(3),
  CONSTRAINT score_stu_no_stage_pk PRIMARY KEY (stu_no, stage)
);

