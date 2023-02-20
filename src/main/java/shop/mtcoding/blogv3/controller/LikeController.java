package shop.mtcoding.blogv3.controller;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import shop.mtcoding.blogv3.dto.ResponseDto;
import shop.mtcoding.blogv3.dto.like.LikeReqDto.LoveSaveReqDto;
import shop.mtcoding.blogv3.handler.ex.CustomApiException;
import shop.mtcoding.blogv3.model.BoardRepository;
import shop.mtcoding.blogv3.model.User;
import shop.mtcoding.blogv3.service.LikeService;

@RestController
public class LikeController {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private HttpSession session;

    @Autowired
    private LikeService likeService;

    @PostMapping("/like")
    public ResponseEntity<?> save(@RequestBody LoveSaveReqDto loveSaveReqDto) {
        User principal = (User) session.getAttribute("principal");

        if (principal == null) {
            throw new CustomApiException("인증이 되지 않았습니다.", HttpStatus.UNAUTHORIZED);
        }

        if (loveSaveReqDto.getBoardId() == null) {
            throw new CustomApiException("boardId를 전달해주세요");
        }

        int likeId = likeService.좋아요(loveSaveReqDto.getBoardId(), principal.getId());

        return new ResponseEntity<>(new ResponseDto<>(1, "좋아요 성공", likeId), HttpStatus.CREATED);
    }

    @DeleteMapping("/like/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        User principal = (User) session.getAttribute("principal");

        if (principal == null) {
            throw new CustomApiException("인증이 되지 않았습니다.", HttpStatus.UNAUTHORIZED);
        }

        likeService.좋아요취소(id, principal.getId());

        return new ResponseEntity<>(new ResponseDto<>(1, "좋아요 취소 성공", null), HttpStatus.OK);
    }
}
