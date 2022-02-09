package com.hs.blog.service;

import com.hs.blog.model.Board;
import com.hs.blog.model.Reply;
import com.hs.blog.model.User;
import com.hs.blog.repository.BoardRepository;
import com.hs.blog.repository.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


// 스프링이 컴포넌트 스캔을 통해서 Bean에 등록을 해줌(IoC를 해준다)
@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private ReplyRepository replyRepository;

    @Transactional
    public void 글쓰기(Board board, User user){ // title, content

        board.setCount(0);
        board.setUser(user);
        boardRepository.save(board);

    }

    @Transactional(readOnly = true)
    public Page<Board> 글목록(Pageable pageable){
        return boardRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Board 글상세보기(int id){
        return boardRepository.findById(id)
                .orElseThrow(()->{
                   return new IllegalArgumentException("글 상세보기 실패: 아이디를 찾을 수 없습니다.: " + id);
                });
    }

    @Transactional
    public void 글삭제하기(int id){
        boardRepository.deleteById(id);
    }

    @Transactional
    public void 글수정하기(int id, Board requestBoard){
        Board board = boardRepository.findById(id)
                .orElseThrow(()->{
                    return new IllegalArgumentException("글 찾기 실패: 아이디를 찾을 수 없습니다.: " + id);
                }); // 영속화

        board.setTitle(requestBoard.getTitle());
        board.setContent(requestBoard.getContent());
        // 해당 함수가 종료 시(Service가 종료될 때) 트랜잭션이 종료. 이때 더티체킹 - 자동 업데이트. db flush
    }

    @Transactional
    public void 댓글쓰기(User user, int boardId, Reply requestReply){
        requestReply.setUser(user);
        Board board = boardRepository.findById(boardId)
                .orElseThrow(()->{
                    return new IllegalArgumentException("댓글 쓰기 실패: 게시글 id를 찾을 수 없습니다.");
                });
        requestReply.setBoard(board);
        replyRepository.save(requestReply);
    }

    @Transactional
    public void 댓글삭제(int replyId){
        replyRepository.deleteById(replyId);
    }
}
