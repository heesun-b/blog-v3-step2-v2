package shop.mtcoding.blogv3.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import shop.mtcoding.blogv3.dto.board.BoardReqDto.BoardSaveReqDto;
import shop.mtcoding.blogv3.dto.board.BoardReqDto.BoardUpdateReqDto;
import shop.mtcoding.blogv3.dto.board.BoardResDto;
import shop.mtcoding.blogv3.model.User;

@Transactional
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
public class BoardControllerTest {

    @Autowired
    private MockHttpSession mockSession;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper om;

    @BeforeEach
    public void setUp() {
        User user = new User();
        user.setId(1);
        user.setUsername("ssar");
        user.setPassword("1234");
        user.setEmail("ssar@nate.com");
        user.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));

        mockSession = new MockHttpSession();
        mockSession.setAttribute("principal", user);
    }

    @Test
    public void save_test() throws Exception {
        BoardSaveReqDto boardSaveReqDto = new BoardSaveReqDto();
        boardSaveReqDto.setTitle("제목입니다.");
        boardSaveReqDto.setContent("내용입니다.");

        String requestBody = om.writeValueAsString(boardSaveReqDto);

        ResultActions resultActions = mvc.perform(post("/board").content(requestBody)
                .contentType(MediaType.APPLICATION_JSON_VALUE).session(mockSession));

        resultActions.andExpect(status().isCreated());
    }

    @Test
    public void main_test() throws Exception {

        ResultActions resultActions = mvc.perform(get("/"));
        Map<String, Object> map = resultActions.andReturn().getModelAndView().getModel();

        List<BoardResDto.BoardMainResponseDto> dtos = (List<BoardResDto.BoardMainResponseDto>) map.get("dtos");
        String model = om.writeValueAsString(dtos);
        System.out.print("테스트 : " + model);

        resultActions.andExpect(status().isOk());

        assertThat(dtos.size()).isEqualTo(7);
        assertThat(dtos.get(0).getTitle()).isEqualTo("제목6");
    }

    /*
     * @Test
     * public void detail_test() throws Exception {
     * 
     * // given
     * int id = 1;
     * 
     * // when
     * ResultActions resultActions = mvc.perform(get("/board/" +
     * id).session(mockSession));
     * Map<String, Object> map =
     * resultActions.andReturn().getModelAndView().getModel();
     * 
     * BoardDetailResponseDto boardDto = (BoardDetailResponseDto)
     * map.get("boardDto");
     * String boardDtoJson = om.writeValueAsString(boardDto);
     * List<ReplyResDto.ReplyDetailResDto> replyDtos =
     * (List<ReplyResDto.ReplyDetailResDto>) map.get("replyDtos");
     * Like likeDto = (Like) map.get("likeDto");
     * 
     * // String replyListJson = om.writeValueAsString(replyDtos);
     * 
     * // System.out.print("테스트 : " + boardDtoJson);
     * // System.out.print("테스트 2: " + replyListJson);
     * 
     * // then
     * // resultActions.andExpect(status().isOk());
     * // assertThat(dto.getUserId()).isEqualTo(1);
     * resultActions.andExpect(status().isOk());
     * assertThat(boardDto.getUsername()).isEqualTo("ssar");
     * assertThat(likeDto.getBoardId()).isEqualTo(1);
     * }
     */

    @Test
    public void delete_test() throws Exception {

        // given
        int id = 2;

        // when
        ResultActions resultActions = mvc.perform(delete("/board/" + id).session(mockSession));

        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("테스트: " + responseBody);
        // then
        resultActions.andExpect(status().isOk());
        resultActions.andExpect(jsonPath("$.code").value(1));

    }

    @Test
    public void update_test() throws Exception {
        // given
        int id = 1;
        BoardUpdateReqDto boardUpdateRequestDto = new BoardUpdateReqDto();
        boardUpdateRequestDto.setTitle("제목1변경");
        boardUpdateRequestDto.setContent("내용1변경");
        String requestBody = om.writeValueAsString(boardUpdateRequestDto);

        // when
        ResultActions resultActions = mvc
                .perform(put("/board/" + id).content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON_VALUE).session(mockSession));

        // then
        resultActions.andExpect(status().isOk());
        resultActions.andExpect(jsonPath("$.code").value(1));

    }
}
