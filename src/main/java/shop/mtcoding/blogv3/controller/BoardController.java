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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import shop.mtcoding.blogv3.dto.ResponseDto;
import shop.mtcoding.blogv3.dto.board.BoardReqDto.BoardSaveReqDto;
import shop.mtcoding.blogv3.dto.board.BoardReqDto.BoardUpdateReqDto;
import shop.mtcoding.blogv3.dto.board.BoardResDto.BoardDetailResponseDto;
import shop.mtcoding.blogv3.dto.board.BoardResDto.BoardMainResponseDto;
import shop.mtcoding.blogv3.handler.ex.CustomApiException;
import shop.mtcoding.blogv3.handler.ex.CustomException;
import shop.mtcoding.blogv3.model.Board;
import shop.mtcoding.blogv3.model.BoardRepository;
import shop.mtcoding.blogv3.model.User;
import shop.mtcoding.blogv3.service.BoardService;

@Controller
public class BoardController {

    @Autowired
    private HttpSession session;

    @Autowired
    private BoardService boardService;

    @Autowired
    private BoardRepository boardRepository;

    @GetMapping("/")
    public String main(Model model) {
        List<BoardMainResponseDto> dtos = boardRepository.findAllWithUser();
        model.addAttribute("dtos", dtos);
        return "board/main";
    }

    @GetMapping("/saveForm")
    public String saveForm() {
        return "board/saveForm";
    }

    @PostMapping("/board")
    public ResponseEntity<?> save(@RequestBody BoardSaveReqDto boardSaveReqDto) {
        User principal = (User) session.getAttribute("principal");

        if (principal == null) {
            throw new CustomException("인증 불가 : 로그인 필요");
        }

        if (boardSaveReqDto.getTitle() == null || boardSaveReqDto.getTitle().isEmpty()) {
            throw new CustomException("title을 입력해주세요");
        }
        if (boardSaveReqDto.getContent() == null || boardSaveReqDto.getContent().isEmpty()) {
            throw new CustomException("Content를 입력해주세요");
        }
        if (boardSaveReqDto.getTitle().length() > 100) {
            throw new CustomException("title의 길이가 100자 이하여야 합니다");
        }

        boardService.save(boardSaveReqDto, principal.getId());

        return new ResponseEntity<>(new ResponseDto<>(1, "게시글 등록 완료", null), HttpStatus.CREATED);
    }

    @GetMapping("/board/{id}")
    public String detail(@PathVariable int id, Model model) {
        BoardDetailResponseDto boardPS = boardRepository.findByIdWithUser(id);
        User user = (User) session.getAttribute("principal");
        System.out.println(user.getId());

        model.addAttribute("dto", boardPS);
        return "board/detail";
    }

    @DeleteMapping("/board/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {

        User principal = (User) session.getAttribute("principal");

        if (principal == null) {
            throw new CustomApiException("인증이 되지 않았습니다.");
        }

        boardService.boardDelete(id, principal.getId());
        return new ResponseEntity<>(new ResponseDto<>(1, "삭제 완료", null), HttpStatus.OK);
    }

    @GetMapping("/board/{id}/updateForm")
    public String boardupdateForm(@PathVariable int id, Model model) {
        Board boardPS = boardRepository.findById(id);
        User principal = (User) session.getAttribute("principal");

        if (principal == null) {
            throw new CustomException("인증이 되지 않았습니다.", HttpStatus.UNAUTHORIZED);
        }

        if (boardPS == null) {
            throw new CustomException("게시글이 존재하지 않습니다");
        }

        if (boardPS.getUserId() != principal.getId()) {
            throw new CustomException("게시글 수정 권한이 없습니다.");
        }

        model.addAttribute("board", boardPS);
        return "board/updateForm";

    }

    @PutMapping("/board/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody BoardUpdateReqDto boardUpdateReqDto) {

        User principal = (User) session.getAttribute("principal");

        if (principal == null) {
            throw new CustomApiException("인증이 되지 않았습니다.", HttpStatus.UNAUTHORIZED);
        }
        if (boardUpdateReqDto.getTitle() == null || boardUpdateReqDto.getTitle().isEmpty()) {
            throw new CustomApiException("title을 입력해주세요");
        }
        if (boardUpdateReqDto.getContent() == null || boardUpdateReqDto.getContent().isEmpty()) {
            throw new CustomApiException("Content를 입력해주세요");
        }
        if (boardUpdateReqDto.getTitle().length() > 100) {
            throw new CustomApiException("title의 길이가 100자 이하여야 합니다");
        }

        boardService.boardUpdate(id, principal.getId(), boardUpdateReqDto);

        return new ResponseEntity<>(new ResponseDto<>(1, "수정 완료", null), HttpStatus.OK);

    }
}