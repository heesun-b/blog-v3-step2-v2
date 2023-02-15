package shop.mtcoding.blogv3.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shop.mtcoding.blogv3.dto.user.UserReqDto.LoginReqDto;
import shop.mtcoding.blogv3.handler.ex.CustomApiException;
import shop.mtcoding.blogv3.handler.ex.CustomException;
import shop.mtcoding.blogv3.model.BoardRepository;
import shop.mtcoding.blogv3.model.ReplyRepository;
import shop.mtcoding.blogv3.model.User;
import shop.mtcoding.blogv3.model.UserRepository;

@Service
public class AdminService {

    @Autowired
    private HttpSession session;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public User login(LoginReqDto loginReqDto) {
        User principal = userRepository.findByUsernameAndPassword(loginReqDto.getUsername(), loginReqDto.getPassword());

        if (principal == null) {
            throw new CustomException("Username 또는 Password를 확인하세요.");
        }

        if (!principal.getRoll().equals("ADMIN")) {
            throw new CustomException("페이지 권한이 없습니다");
        }
        return principal;
    }

    @Transactional
    public void replyDelete(int id) {
        User principal = (User) session.getAttribute("principal");

        if (!principal.getRoll().equals("ADMIN")) {
            throw new CustomApiException("삭제 권한이 없습니다.", HttpStatus.FORBIDDEN);
        }

        int result = replyRepository.deleteById(id);

        if (result != 1) {
            throw new CustomApiException("삭제 실패", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    public void boardDelete(int id) {
        User principal = (User) session.getAttribute("principal");

        if (!principal.getRoll().equals("ADMIN")) {
            throw new CustomApiException("삭제 권한이 없습니다.", HttpStatus.FORBIDDEN);
        }

        int result = boardRepository.deleteById(id);

        if (result != 1) {
            throw new CustomApiException("삭제 실패", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
