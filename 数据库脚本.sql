CREATE OR REPLACE PROCEDURE add_student(v_sno IN VARCHAR2,v_sname IN VARCHAR2,v_sex IN CHAR,v_class IN VARCHAR2,v_passwd IN VARCHAR2,v_msg OUT NUMBER)
AS
BEGIN
       INSERT INTO student(stu_no,
                       stu_name,
                       stu_sex,
                       stu_class,
                       stu_passwd) VALUES (v_sno,v_sname,v_sex,v_class,v_passwd);
                       COMMIT;
                        EXCEPTION
						  WHEN DUP_VAL_ON_INDEX THEN
							v_msg := -1;
						   WHEN OTHERS THEN ROLLBACK;
      END;

SELECT * FROM student;


CREATE OR REPLACE PROCEDURE check_login(v_sno  IN  VARCHAR2, v_pwd IN VARCHAR2, v_msg OUT NUMBER,
                             v_name OUT VARCHAR2)
AS v_temp_pwd STUDENT.STU_PASSWD%TYPE;
    e_pwd EXCEPTION;
  BEGIN
    SELECT
      STU_PASSWD,
      STU_NAME
    INTO v_temp_pwd, v_name
    FROM STUDENT
    WHERE STU_NO = v_sno;
    IF v_temp_pwd = v_pwd
    THEN
      v_msg := 1;
    ELSE
      RAISE e_pwd;
    END IF;
    EXCEPTION
    WHEN NO_DATA_FOUND THEN
    v_msg := 2;
    WHEN e_pwd THEN
    v_msg := 3;
    WHEN OTHERS THEN
    v_msg := -1;
  END;
