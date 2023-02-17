package shop.mtcoding.blogv3.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import shop.mtcoding.blogv3.dto.reply.ReplyReqDto.ReplySaveReqDto;
import shop.mtcoding.blogv3.handler.ex.CustomApiException;
import shop.mtcoding.blogv3.handler.ex.CustomException;
import shop.mtcoding.blogv3.model.Reply;
import shop.mtcoding.blogv3.model.ReplyRepository;
import shop.mtcoding.blogv3.model.User;

@Slf4j
@Transactional(readOnly = true)
@Service
public class ReplyService {

    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private HttpSession session;

    @Transactional
    public void save(int principalId, ReplySaveReqDto replySaveReqDto) {

        int result = replyRepository.insert(principalId, replySaveReqDto.getBoardId(), replySaveReqDto.getComment());

        if (result != 1) {
            throw new CustomException("댓글 쓰기 실패", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    public void delete(int id, int principalId) {

        Reply reply = replyRepository.findById(id);

        if (reply == null) {
            throw new CustomApiException("댓글을 찾을 수 없습니다.");
        }

        User principal = (User) session.getAttribute("principal");

        if (reply.getUserId() != principalId && principal.getRoll().equals("USER")) {
            throw new CustomApiException("삭제 권한이 없습니다.", HttpStatus.FORBIDDEN);
        }

        try {
            replyRepository.deleteById(id);
        } catch (Exception e) {
            log.error("서버에러: " + e.getMessage());
            throw new CustomApiException("서버에 일시적인 문제가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
