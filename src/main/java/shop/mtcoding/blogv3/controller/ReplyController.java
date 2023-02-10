package shop.mtcoding.blogv3.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.Getter;
import lombok.Setter;
import shop.mtcoding.blogv3.handler.ex.CustomApiException;
import shop.mtcoding.blogv3.handler.ex.CustomException;
import shop.mtcoding.blogv3.model.User;

@Controller
public class ReplyController {

    @Autowired
    private HttpSession session;

    @PostMapping("/reply")
    public String save(ReplySaveReqDto replySaveReqDto) {

        User principal = (User) session.getAttribute("principal");
        if (principal == null) {
            throw new CustomException("인증되지 않았습니다.", HttpStatus.UNAUTHORIZED);
        }

        if (replySaveReqDto.getComment() == null || replySaveReqDto.getComment().isEmpty()) {
            throw new CustomApiException("comment를 작성해주세요.");
        }

        if (replySaveReqDto.getBoardId() == null) {
            throw new CustomApiException("boardId가 필요합니다.");
        }

        if (replySaveReqDto.getComment().length() > 100) {
            throw new CustomException("100자 이하로 작성가능합니다.");
        }

        return "redirect:/board/" + replySaveReqDto.getBoardId();
    }

    @Getter
    @Setter
    public static class ReplySaveReqDto {
        private String comment;
        private Integer boardId;
    }
}
