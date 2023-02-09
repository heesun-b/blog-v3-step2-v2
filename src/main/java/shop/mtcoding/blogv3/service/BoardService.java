package shop.mtcoding.blogv3.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shop.mtcoding.blogv3.dto.board.BoardReqDto.BoardSaveReqDto;
import shop.mtcoding.blogv3.dto.board.BoardReqDto.BoardUpdateReqDto;
import shop.mtcoding.blogv3.handler.ex.CustomApiException;
import shop.mtcoding.blogv3.handler.ex.CustomException;
import shop.mtcoding.blogv3.model.Board;
import shop.mtcoding.blogv3.model.BoardRepository;
import shop.mtcoding.blogv3.util.Thumbnail;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private HttpSession session;

    @Transactional
    public void save(BoardSaveReqDto boardSaveReqDto, int userId) {

        String img = Thumbnail.thumbnailParse(boardSaveReqDto.getContent());

        int result = boardRepository.insert(userId, boardSaveReqDto.getTitle(), boardSaveReqDto.getContent(), img);

        if (result != 1) {
            throw new CustomException("글쓰기 실패", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    public void boardDelete(int id, int userId) {
        Board boardPS = boardRepository.findById(id);

        if (boardPS == null) {
            throw new CustomApiException("게시글이 존재하지 않습니다.");
        }

        if (boardPS.getUserId() != userId) {
            throw new CustomApiException("게시물 삭제 권한이 없습니다.", HttpStatus.FORBIDDEN);
        }

        int result = boardRepository.deleteById(id);

        if (result != 1) {
            throw new CustomApiException("서버에 일시적인 문제가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    public void boardUpdate(int id, int userId, BoardUpdateReqDto boardUpdateReqDto) {
        Board boardPS = boardRepository.findById(id);

        String img = Thumbnail.thumbnailParse(boardUpdateReqDto.getContent());

        int result = boardRepository.updateById(id, boardUpdateReqDto.getTitle(),
                boardUpdateReqDto.getContent(), img);

        if (result != 1) {
            throw new CustomApiException("서버에 일시적인 문제가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
