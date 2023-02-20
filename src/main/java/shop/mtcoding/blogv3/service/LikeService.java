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
    public int 좋아요(int boardId, int userId) {

        Board boardOP = boardRepository.findById(boardId);

        if (boardOP == null) {
            throw new CustomApiException("존재하지 않는 게시물입니다.");
        }

        Like like = new Like();
        like.setBoardId(boardId);
        like.setUserId(userId);
        likeRepository.insert(like);

        return like.getId();
    }

    @Transactional
    // 권한체크 위해서 userId를 꼭 받기
    public void 좋아요취소(int id, int principalId) {
        Like likePS = likeRepository.findById(id);

        if (likePS == null) {
            throw new CustomApiException("좋아요 이력이 존재하지 않습니다.");
        }

        if (likePS.getUserId() != principalId) {
            throw new CustomApiException("권한이 없습니다.", HttpStatus.FORBIDDEN);
        }

        int result = likeRepository.deleteById(id);
        if (result != 1) {
            throw new CustomApiException("서버 에러", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
