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

@Service
public class LikeService {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private LikeRepository likeRepository;

    @Transactional
    public void insert(int boardId, int userId) {

        Board boardPS = boardRepository.findById(boardId);
        if (boardPS == null) {
            throw new CustomApiException("존재하지 않는 게시물입니다");
        }

        int result = likeRepository.insert(boardId, userId, "fa-solid like-color");
        if (result != 1) {
            throw new CustomApiException("서버 오류", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    public void delete(int id) {

        int result = likeRepository.deleteById(id);

        if (result != 1) {
            throw new CustomApiException("서버 오류", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
