package shop.mtcoding.blogv3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shop.mtcoding.blogv3.handler.ex.CustomApiException;
import shop.mtcoding.blogv3.model.Board;
import shop.mtcoding.blogv3.model.BoardRepository;
import shop.mtcoding.blogv3.model.Like;
import shop.mtcoding.blogv3.model.LikeRepository;
import shop.mtcoding.blogv3.model.UserRepository;

@Service
public class LikeService {
    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LikeRepository likeRepository;

    @Transactional
    public void insert(int boardId, int principalId) {
        Board boardPS = boardRepository.findById(boardId);

        if (boardPS == null) {
            throw new CustomApiException("존재하지 않는 게시물입니다");
        }

        Like likePS = likeRepository.findByBoardIdAndUserId(boardId, principalId);

        if (likePS != null) {
            throw new CustomApiException("이미 좋아요 처리된 게시물입니다.");
        }

        int result = likeRepository.insert(principalId, boardId);

        if (result != 1) {
            throw new CustomApiException("서버에 일시적 문제가 발생", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    public void delete(int boardId, int principalId) {
        Board boardPS = boardRepository.findById(boardId);

        if (boardPS == null) {
            throw new CustomApiException("존재하지 않는 게시물입니다");
        }

        Like likePs = likeRepository.findByBoardIdAndUserId(boardId, principalId);
        // if(likePs == null) {
        // throw new CustomApiException("");
        // }
        if (likePs == null) {
            throw new CustomApiException("이미 좋아요가 취소된 게시물입니다.");
        }
        int result = likeRepository.deleteById(likePs.getId());
        if (result != 1) {
            throw new CustomApiException("서버에 일시적 문제가 발생", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
