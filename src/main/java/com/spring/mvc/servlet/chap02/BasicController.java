package com.spring.mvc.servlet.chap02;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

// Controller 클라이언트의 요청, 응답처리 클래스 : 서브컨트롤러
@Controller //빈 등록 : 스프링컨테이너에 객체 생성을 위임, @Component 를 포함하고 있다.
// 스프링의 프론트컨트롤러(Dispatcher)가 이 클래스를 HandlerMapping 으로 찾음.
@Log4j2 // log. 을 사용할수 있게 해주는 롬복
public class BasicController {

    @Getter @Setter @ToString   // 롬복 : 게터 세터 투스트링 생성.
    static class Order {    // static : 접근을 쉽게 하기 위해서.
        private int num;
        private String goods;
        private int price;
    }

    @RequestMapping("/spring/about")
    public String about() {
        log.trace("/about 요청 발생!"); // 추적           개발단계
        log.debug("/about 요청 발생!"); // 디버그         개발단계
        log.info("/about 요청 발생!");  // 간단한정보      운영단계
        log.warn("/about 요청 발생!");  // 경고성 1        운영단계
        log.error("/about 요청 발생!"); // 에러            운영단계   위로 갈수록 낮은 단계
        System.out.println("/about 요청 발생!");
        return "test";
    }

    @RequestMapping("/spring/hello")
    public String hello() {
        System.out.println("/hello 요청 발생!");
        return "redirect:/spring/about";        // /spring/about 로 요청을 다시 보냄.
    }

//   클라이언트의 요청파라미터 읽기
    @RequestMapping("/spring/join")
    public String join(HttpServletRequest request) {
        String id = request.getParameter("id");
        System.out.println("id = " + id);
        return  "";
    }

    @RequestMapping("/spring/join2")
    public String join(String hobby) {

        System.out.println("hobby = " + hobby);
        return  "";
    }

    @RequestMapping("/spring/join3")
    public String join3(@RequestParam("h") String hb) {     // 파라미터에 h 라는 이름을 부여

        System.out.println("hobby = " + hb);
        return  "";
    }

    // 커멘더 객체로 파라미터 읽기
    // /spring/order?num=5566&goods=book&price=5000
    @RequestMapping("/spring/order")
    public String order(Order order){       // 파라미터가 많으면 클래스로 만들어서 이용.
        System.out.println("order = " + order);

        return "";
    }

}
