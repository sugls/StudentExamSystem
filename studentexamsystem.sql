CREATE TABLE student
(
  stu_no     NUMBER(12) PRIMARY KEY,
  stu_name   VARCHAR2(20) NOT NULL,
  stu_sex    CHAR(1)      NOT NULL,
  stu_class  VARCHAR2(5)  NOT NULL,
  stu_passwd VARCHAR2(20) NOT NULL
);

CREATE TABLE question
(
  que_no     NUMBER(3) PRIMARY KEY,
  que_note   VARCHAR2(20) NOT NULL,
  que_detail VARCHAR2(1024),
  que_choice VARCHAR2(1024),
  que_answer VARCHAR2(6),
  stage      NUMBER(1)
);
ALTER TABLE question
  ADD stage CHAR(1);
CREATE TABLE score
(
  stu_no NUMBER(12) REFERENCES student (stu_no),
  stage  CHAR(1),
  score  NUMBER(3),
  CONSTRAINT score_stu_no_stage_pk PRIMARY KEY (stu_no, stage)
);
ALTER TABLE score
  ADD score NUMBER(3);
SELECT *
FROM student;
SELECT *
FROM question;
SELECT *
FROM score;

INSERT INTO question (que_no, que_note, que_detail, que_choice, que_answer, stage)
VALUES (1, 'multiple selection', 'Given
	1	public class X{
	2		public Object m(){
	3			Object o = new Float(3.14F);
	4			Object [] oa = new Object[1];
	5			oa[0] = o;
	6			o=null;
	7			return oa[0];
	8		}
	9	}
	When is the Float object, created in line 3,eligible for garbage collection?',
        'just after line 5
    just after line 6
    just after line 7(that is,as the method returns)
    never in this method', '0001', 1
)

INSERT INTO question (que_no, que_note, que_detail, que_choice, que_answer, stage)
VALUES
  (
    2,
    'single selection',
    'Which of these is the correct format to use to create the literal char value a?
      A)	''a''
      B)	"a"
      C)	new Character(a)
      D)	\000a
      Select the most appropriate answer.',
    'A
  B
  C
  D',
    '1000',
    1
  )
COMMIT
DELETE FROM question
WHERE que_no = 2;
SELECT 'test '''
FROM dual;
SELECT 'test' || ''''
FROM dual;
DELETE FROM question
ALTER TABLE question MODIFY stage CHAR(1);
SELECT * FROM question;