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
ALTER TABLE SCORE
  MODIFY STU_NO VARCHAR2(12);

DECLARE v_msg NUMBER(1);v_name VARCHAR2(20);
BEGIN
  check_login('201326811010','123456',v_msg,v_name);
    dbms_output.put_line(v_msg);
  dbms_output.put_line(v_name);
END;
