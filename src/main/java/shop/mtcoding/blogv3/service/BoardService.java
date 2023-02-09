package shop.mtcoding.blogv3.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shop.mtcoding.blogv3.dto.board.BoardReqDto.BoardSaveReqDto;
import shop.mtcoding.blogv3.handler.ex.CustomException;
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
}
