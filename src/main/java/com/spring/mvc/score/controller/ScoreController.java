package com.spring.mvc.score.controller;


import com.spring.mvc.score.domain.Score;
import com.spring.mvc.score.repository.ScoreMemoryRepo;
import com.spring.mvc.score.repository.ScoreRepository;
import com.spring.mvc.score.service.ScoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@Log4j2
@RequiredArgsConstructor    // final 필드들만 초기화 생성자를 만듬.
//@AllArgsConstructor // 모든 필드들 초기화 생성자를 만듬
public class ScoreController {
    private final ScoreRepository repository; // 생성자 주입을 사용하고 나중에 세터로 바꾸지 못하게 final 을 해주면 객체 만들어지면서 주입된것이 불변. => 안정적.
                                                // 의존성 주입 : 생성자주입, 세터주입, 필드주입


//    public ScoreController(ScoreRepository repository) {    // 생성자엔 자동으로 @Autowired 를 붙여줌.
//        this.repository = repository;                         // 따라서 위의 리콰이어아그컨스트럭터를 해주면 끝.
//    }

    // 점수 등록 및 조회 화면 열기 요청
    @RequestMapping("/score/list")
    public String list(
            @RequestParam(defaultValue = "num") String sort
            , Model model) {
        log.info("/score/list GET 요청!! - param1 : {}", sort);

        List<Score> scoreList = ScoreService.findAllService(sort);
        model.addAttribute("scores", scoreList);

        // /WEB-INF/score/list.jsp 포워딩해서 열어주는 코드
        return "score/list";
    }

    // 점수 신규 등록 요청
    @PostMapping("/score/register")
    public String register(Score score) {
        score.calcTotalAndAvg();
        score.calcGrade();
        log.info("/score/register POST! - " + score);

        return repository.save(score) ? "redirect:/score/list" : "redirect:/"; // DB 에는 등록되었으나 현 페이지에는 반영 안된 상태 -> 갱신된 데이터를 가져오려면 다시 요청해야.
    }

    // 점수 삭제 요청
    @RequestMapping("/score/delete")
    public String delete(@RequestParam("stuNum") int sn) {  // @RequestParam("stuNum") int sn : 파라미터 stuNum 의 값을 int sn 에 대입해라.
        log.info("/score/delete GET - param1: {}", sn);
        return repository.remove(sn) ? "redirect:/score/list" : "redirect:/";
    }

    // 점수 상세 조회 요청
    @GetMapping("/score/detail")
    public String detail(int stuNum, Model model) {
        log.info("/score/detail GET - param1: {}", stuNum);
        Score score = repository.findOne(stuNum);
        model.addAttribute("s", score);

        return "score/detail";
    }

}