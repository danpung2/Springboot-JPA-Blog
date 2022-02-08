package com.hs.blog.repository;

import com.hs.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

// DAO
// 자동으로 bean 등록이 된다.
// @Repository 라는 어노테이션을 추가하지 않아도 됨.
public interface UserRepository extends JpaRepository<User, Integer> {

    // SELECT * FROM user WHERE username = 1?;
    Optional<User> findByUsername(String username);


    //JPA Naming 쿼리
    // SELECT * FROM user WHERE username =?1 AND password = ?2;
    // User findByUsernameAndPassword(String username, String password);

    // @Query(value="SELECT * FROM user WHERE username =?1 AND password = ?2", nativeQuery = true)
    // User login(String username, String password);
}
