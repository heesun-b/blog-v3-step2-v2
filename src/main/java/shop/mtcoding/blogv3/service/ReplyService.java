package shop.mtcoding.blogv3.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shop.mtcoding.blogv3.controller.ReplyController.ReplySaveReqDto;
import shop.mtcoding.blogv3.handler.ex.CustomException;
import shop.mtcoding.blogv3.model.ReplyRepository;

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

}
