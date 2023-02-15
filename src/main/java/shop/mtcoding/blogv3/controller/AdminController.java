package shop.mtcoding.blogv3.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import shop.mtcoding.blogv3.dto.ResponseDto;
import shop.mtcoding.blogv3.dto.board.BoardResDto.BoardMainResponseDto;
import shop.mtcoding.blogv3.dto.reply.ReplyResDto.ReplyAdminResDto;
import shop.mtcoding.blogv3.dto.user.UserReqDto.LoginReqDto;
import shop.mtcoding.blogv3.handler.ex.CustomApiException;
import shop.mtcoding.blogv3.handler.ex.CustomException;
import shop.mtcoding.blogv3.model.Board;
import shop.mtcoding.blogv3.model.BoardRepository;
import shop.mtcoding.blogv3.model.ReplyRepository;
import shop.mtcoding.blogv3.model.User;
import shop.mtcoding.blogv3.model.UserRepository;
import shop.mtcoding.blogv3.service.AdminService;

@Controller
public class AdminController {
    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private AdminService adminService;

    @Autowired
    private HttpSession session;

    @GetMapping("/admin")
    public String admin() {
        ;
        return "admin/loginForm";
    }

    @PostMapping("/admin/login")
    public String login(LoginReqDto loginReqDto) {
        if (loginReqDto.getUsername() == null || loginReqDto.getUsername().isEmpty()) {
            throw new CustomException("username을 입력해주세요");
        }

        if (loginReqDto.getPassword() == null || loginReqDto.getPassword().isEmpty()) {
            throw new CustomException("password를 입력해주세요");
        }

        User principal = adminService.login(loginReqDto);
        session.setAttribute("principal", principal);

        return "redirect:/admin/user";
    }

    @GetMapping("/admin/user")
    public String userPage(Model model) {
        List<User> userList = userRepository.findAll();
        model.addAttribute("userList", userList);
        return "admin/user";
    }

    @GetMapping("/admin/board")
    public String boardPage(Model model) {
        List<BoardMainResponseDto> boardList = boardRepository.findAllWithUser();
        model.addAttribute("boardList", boardList);
        return "admin/board";
    }

    @GetMapping("/admin/reply")
    public String replyPage(Model model) {
        List<ReplyAdminResDto> replyList = replyRepository.findAllWithUser();
        model.addAttribute("replyList", replyList);
        return "admin/reply";
    }

    @DeleteMapping("/admin/reply/delete/{id}")
    public ResponseEntity<?> replyDelete(@PathVariable int id) {
        User principal = (User) session.getAttribute("principal");

        if (principal == null) {
            throw new CustomApiException("인증되지 않았습니다", HttpStatus.UNAUTHORIZED);
        }

        adminService.replyDelete(id);
        return new ResponseEntity<>(new ResponseDto<>(1, "댓글 삭제 완료", null), HttpStatus.OK);
    }

    @DeleteMapping("/admin/board/delete/{id}")
    public ResponseEntity<?> boardDelete(@PathVariable int id) {
        User principal = (User) session.getAttribute("principal");

        if (principal == null) {
            throw new CustomApiException("인증되지 않았습니다", HttpStatus.UNAUTHORIZED);
        }

        adminService.boardDelete(id);
        return new ResponseEntity<>(new ResponseDto<>(1, "게시글 삭제 완료", null), HttpStatus.OK);
    }

    // @GetMapping("/admin/board/{id}")
    // public ResponseEntity<?> boardDetail(@PathVariable int id) {
    // Board boardPS = boardRepository.findById(id);
    // if (boardPS == null) {
    // throw new CustomApiException("해당 게시글을 찾을 수 없습니다");
    // }

    // return new ResponseEntity<>("", null);

    // }

    @GetMapping("/admin/board/{id}")
    public ResponseEntity<?> viewBoard(@PathVariable int boardId) {
        Board boardPS = boardRepository.findById(boardId);

        if (boardPS == null) {
            throw new CustomApiException("존재하지 않는 게시물입니다.");
        }

        return new ResponseEntity<>(new ResponseDto<>(1, "게시글 이동", null), HttpStatus.OK);
    }
}