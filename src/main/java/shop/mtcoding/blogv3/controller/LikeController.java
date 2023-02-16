package shop.mtcoding.blogv3.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import shop.mtcoding.blogv3.dto.LikeReqDto;
import shop.mtcoding.blogv3.dto.ResponseDto;
import shop.mtcoding.blogv3.handler.ex.CustomApiException;
import shop.mtcoding.blogv3.model.Like;
import shop.mtcoding.blogv3.model.LikeRepository;
import shop.mtcoding.blogv3.model.User;
import shop.mtcoding.blogv3.service.LikeService;

@Controller
public class LikeController {

    @Autowired
    private HttpSession session;

    @Autowired
    private LikeService likeService;

    @Autowired
    private LikeRepository likeRepository;

    @PostMapping("/like")
    public ResponseEntity<?> insert(@RequestBody LikeReqDto likeReqDto) {
        User principal = (User) session.getAttribute("principal");
        if (principal == null) {
            throw new CustomApiException("인증이 되지 않았습니다", HttpStatus.UNAUTHORIZED);
        }

        likeService.insert(likeReqDto.getBoardId(), principal.getId());
        Like likePS = likeRepository.findByBoardIdAndUserId(likeReqDto.getBoardId(), principal.getId());
        return new ResponseEntity<>(new ResponseDto<>(1, "add like", likePS.getId()), HttpStatus.CREATED);
    }

    @DeleteMapping("/like/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        User principal = (User) session.getAttribute("principal");
        if (principal == null) {
            throw new CustomApiException("인증이 되지 않았습니다", HttpStatus.UNAUTHORIZED);
        }

        likeService.delete(id);
        return new ResponseEntity<>(new ResponseDto<>(1, "delete like", 0), HttpStatus.OK);
    }
}
