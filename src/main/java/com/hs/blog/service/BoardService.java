package com.hs.blog.service;

import com.hs.blog.model.Board;
import com.hs.blog.model.User;
import com.hs.blog.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


// 스프링이 컴포넌트 스캔을 통해서 Bean에 등록을 해줌(IoC를 해준다)
@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Transactional
    public void 글쓰기(Board board, User user){ // title, content

        board.setCount(0);
        board.setUser(user);
        boardRepository.save(board);

    }

    public Page<Board> 글목록(Pageable pageable){
        return boardRepository.findAll(pageable);
    }

    public Board 글상세보기(int id){
        return boardRepository.findById(id)
                .orElseThrow(()->{
                   return new IllegalArgumentException("글 상세보기 실패: 아이디를 찾을 수 없습니다.: " + id);
                });
    }
}
