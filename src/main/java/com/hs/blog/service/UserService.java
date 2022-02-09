package com.hs.blog.service;

import com.hs.blog.model.RoleType;
import com.hs.blog.model.User;
import com.hs.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


// 스프링이 컴포넌트 스캔을 통해서 Bean에 등록을 해줌(IoC를 해준다)
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;


    @Transactional
    public void 회원가입(User user){

        String rawPassword = user.getPassword(); // 원 비밀번호
        String encPassword = encoder.encode(rawPassword); // 해쉬

        user.setPassword(encPassword);
        user.setRole(RoleType.USER);
        userRepository.save(user);
    }

    @Transactional
    public void 회원수정(User user){
        // 수정 시에는 영속성 컨텍스트 User 오브젝트를 영속화시키고, 영속화 된 User 오브젝트를 수정
        // select를 해서 User 오브젝트를 DB로부터 가져와서 영속화 해야함
        // 영속화 된 오브젝트를 변경하면 자동으로 DB에 update문을 날려줌
        User persistance = userRepository.findById(user.getId()).orElseThrow(()->{
            return new IllegalArgumentException("회원정보를 찾을 수 없습니다");
        });
        String rawPassword = user.getPassword();
        String encPassword = encoder.encode(rawPassword);

        persistance.setPassword(encPassword);
        persistance.setEmail(user.getEmail());

        // 회원수정 함수 종료 = 서비스 종료 -> 트랜잭션 종료 -> 자동 commit -> 더티체킹
    }
}
