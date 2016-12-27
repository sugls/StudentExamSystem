DECLARE v_msg NUMBER(1);v_name VARCHAR2(20);
BEGIN
  check_login('201326811010','123456',v_msg,v_name);
    dbms_output.put_line(v_msg);
  dbms_output.put_line(v_name);
END;

SELECT * FROM student;


INSERT INTO STUDENT(STU_NAME,STU_PASSWD,STU_CLASS,STU_NO,STU_SEX) VALUES ('????','123456','J2010','201326811010','1');
INSERT INTO STUDENT(STU_NAME,STU_PASSWD,STU_CLASS,STU_NO,STU_SEX) VALUES ('????','123456','J2010','1010','1');
COMMIT;

CREATE OR REPLACE PROCEDURE add_student(v_sno IN VARCHAR2,v_sname IN VARCHAR2,v_sex IN CHAR,v_class IN VARCHAR2,v_passwd IN VARCHAR2) 
AS
BEGIN INSERT INTO student(stu_no,
                       stu_name,
                       stu_sex,
                       stu_class,
                       stu_passwd) VALUES (v_sno,v_sname,v_sex,v_class,v_passwd);
COMMIT;                      
 EXCEPTION WHEN OTHERS THEN ROLLBACK;
                         END;
                         
                       SELECT * FROM student;
                          
   SELECT * FROM User_Procedures
