package com.spring.mvc.score.service;

import com.spring.mvc.score.domain.Score;
import com.spring.mvc.score.repository.ScoreMapper;
import com.spring.mvc.score.repository.ScoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

// 컨트롤러 - 서비스 - 저장소
// 역할 : 컨트롤러와 저장소 사이의 중간 처리 닫당
@RequiredArgsConstructor
@Service    // 빈 등록 : Service 라고 빈 등록.
public class ScoreService {

//    private final ScoreRepository repository;
    private final ScoreMapper repository;   // 레포지토리를 DB 로 변경.

    // 전체목록 조회시 중간처리
    public List<Score> findAllService(String sort) {
        // jsp 에게 점수 정보 리스트를 전달해야 함.
        List<Score> scoreList = repository.findAll(sort);

        // 이름 마킹 처리
        for (Score s : scoreList) {
            String original = s.getName();
            StringBuilder markName = new StringBuilder(String.valueOf(original.charAt(0)));
            for (int i = 0; i < original.length() - 1; i++) {
                markName.append("*");
            }
            s.setName(markName.toString());
        }
        return scoreList;
    }
}
