package com.hs.blog.repository;

import com.hs.blog.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

// DAO
// 자동으로 bean 등록이 된다.
// @Repository 라는 어노테이션을 추가하지 않아도 됨.
public interface BoardRepository extends JpaRepository<Board, Integer> {


}
