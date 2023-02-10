package shop.mtcoding.blogv3.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import shop.mtcoding.blogv3.dto.ResponseDto;
import shop.mtcoding.blogv3.dto.reply.ReplyReqDto.ReplySaveReqDto;
import shop.mtcoding.blogv3.handler.ex.CustomApiException;
import shop.mtcoding.blogv3.handler.ex.CustomException;
import shop.mtcoding.blogv3.model.ReplyRepository;
import shop.mtcoding.blogv3.model.User;
import shop.mtcoding.blogv3.service.ReplyService;

@Controller
public class ReplyController {

    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private ReplyService replyService;

    @Autowired
    private HttpSession session;

    @PostMapping("/reply")
    public String save(ReplySaveReqDto replySaveReqDto) {

        User principal = (User) session.getAttribute("principal");
        if (principal == null) {
            throw new CustomException("인증되지 않았습니다.", HttpStatus.UNAUTHORIZED);
        }

        if (replySaveReqDto.getComment() == null || replySaveReqDto.getComment().isEmpty()) {
            throw new CustomException("comment를 작성해주세요.");
        }

        if (replySaveReqDto.getBoardId() == null) {
            throw new CustomException("boardId가 필요합니다.");
        }

        if (replySaveReqDto.getComment().length() > 100) {
            throw new CustomException("100자 이하로 작성가능합니다.");
        }

        replyService.save(principal.getId(), replySaveReqDto);

        return "redirect:/board/" + replySaveReqDto.getBoardId();
    }

    @DeleteMapping("/reply/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {

        User principal = (User) session.getAttribute("principal");

        if (principal == null) {
            throw new CustomApiException("인증 되지 않았습니다.");
        }

        replyService.delete(id, principal.getId());
        return new ResponseEntity<>(new ResponseDto<>(1, "삭제 성공", null), HttpStatus.OK);
    }

}
