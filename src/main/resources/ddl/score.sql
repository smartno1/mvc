SELECT NOW() FROM dual;

-- 성적 정보 저장 테이블
-- 테이블명은 단수가 관례analyze
-- testdb 를 use 중이라 CREATE 앞에 DB이름 생략
CREATE TABLE score (
	-- 컬럼 이름은 스네이크케이스 (자바는 카멜케이스)
    -- stu_num  -->  stuNum
	stu_num INT(10) PRIMARY KEY AUTO_INCREMENT -- 기본키, 숫자 자동으로 하나씩 커짐.
    , stu_name varchar(200) -- varchar 가변문자열 <=> char 고정문자열(설정된바이트중 사용안한만큼 뒤에 공백을 채움)
    , kor INT(3) NOT NULL
    , eng INT(3) NOT NULL
    , math INT(3) NOT NULL
    , total INT(3)
    , average DECIMAL(5,2) -- 총 5자리, 그중 소수점이하 2자리 (100.00)
    , grade CHAR(1) -- 한자리 고정이므로 CHAR (주민번호, 전화번호 등 고정된 자릿수 문자에 쓰면 좋음)

);