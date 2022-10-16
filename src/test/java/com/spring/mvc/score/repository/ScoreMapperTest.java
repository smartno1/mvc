package com.spring.mvc.score.repository;

import com.spring.mvc.score.domain.Grade;
import com.spring.mvc.score.domain.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// 스프링컨테이너의 의존성 주입을 사용 가능
@SpringBootTest
class ScoreMapperTest {

    @Autowired          // 테스트에서는 필드주입으로만 가능.
    ScoreMapper mapper;

    @Test
    @DisplayName("성적 정보 1개를 정확하게 데이터베이스에 저장해야 한다.")
    void saveTest() {   // 무조건 void 로
        //given : 테스트시 주어질 데이터
        Score s = new Score();
        s.setName("방정환");
        s.setKor(50);
        s.setEng(80);
        s.setMath(95);
        s.calcTotalAndAvg();
        s.calcGrade();

        //when : 테스트 상황
        boolean result = mapper.save(s);

        //then : 테스트 결과
        assertTrue((result));   // 나는 result 가 true 라고 단언한다.
    }

    @Test
    @DisplayName("성적정보 전체를 조회해야 한다.")
    void findAllTest(){
        // given

        // when
        List<Score> scoreList = mapper.findAll("");
        //then
        assertEquals(5, scoreList.size());
        assertEquals("김두환", scoreList.get(0).getName());
        assertEquals(50, scoreList.get(2).getKor());

        for (Score score : scoreList) {
            System.out.println(score);
        }
    }

    @Test
    @DisplayName("성적 정보 1개를 정확하게 조회해야 한다.")
    void findOneTest() {
        // given
        int stuNum = 2;

        //when
        Score score = mapper.findOne(stuNum);

        //then
        assertEquals("구마적", score.getName());
        assertEquals(Grade.C, score.getGrade());
        assertNotNull(score.getName());

        System.out.println("score = " + score);
    }

    @Test
    @DisplayName("성적 정보 1개를 삭제해야 한다.")
    // 지우고 다시 롤백 해놓게 하기
    @Transactional
    @Rollback
    void removeTest() {
        // given
        int stuNum  = 1;

        //when
        boolean result = mapper.remove(stuNum);
        Score score = mapper.findOne(stuNum);


        //then
        assertNull(score);
    }

}