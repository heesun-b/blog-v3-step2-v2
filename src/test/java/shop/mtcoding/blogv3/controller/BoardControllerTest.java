package shop.mtcoding.blogv3.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import org.springframework.http.MediaType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import shop.mtcoding.blogv3.dto.board.BoardReqDto.BoardSaveReqDto;
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
}
