package com.hs.blog.test;

import org.springframework.web.bind.annotation.*;

// 사용자가 요청 -> 응답(HTML 파일)
// @Controller

// 사용자가 요청 -> 응답(Data)
@RestController
public class HTTPControllerTest {

    private static final String TAG = "HTTPControllerTest: ";

    @GetMapping("/http/lombok")
    public String lombokTest(){
        Member m = Member.builder()
                .username("ssar")
                        .password("1234")
                                .email("email").build();
        System.out.println(TAG + "getter: " + m.getUsername());
        m.setUsername("cos");
        System.out.println(TAG + "getter: " + m.getUsername());
        return "lombok test 완료";
    }
    
    // 인터넷 브라우저 요청은 무조건 get 요청 밖에 할 수 없다

    // http://localhost:8080/http/get
    @GetMapping("/http/get")
    public String getTest(Member m){
        return "get 요청: " + m.getId() + ", " + m.getUsername();
    }

    @PostMapping("/http/post")
    public String postTest(){
        return "post 요청";
    }

    @PutMapping("/http/put")
    public String putTest(){
        return "put 요청";
    }

    @DeleteMapping("/http/delete")
    public String deleteTest(){
        return "delete 요청";
    }
}
