package com.dsm.me.model.user.controller;

import com.dsm.me.model.user.dto.UserCreateRequestDto;
import com.dsm.me.model.user.dto.UserLoginRequestDto;
import com.dsm.me.model.user.service.AuthService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = AuthController.class)
public class AuthControllerTests {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private AuthService authService;

    @Test
    @DisplayName("Join Success Test")
    public void joinTest() throws Exception {
        String content = objectMapper.writeValueAsString(new UserCreateRequestDto("email@naver.com","password1!", "nickname"));

        mvc.perform(post("/users")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("@Email Error Test: 이메일 형식을 갖추고 있어야 한다")
    public void emailExceptionJoinTest() throws Exception {
        String content = objectMapper.writeValueAsString(new UserCreateRequestDto("email.test","password1!", "nickname"));

        mvc.perform(post("/users")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Password Error Test: 숫자와 특수문자가 하나 이상 들어가야 한다")
    public void textPasswordJoinTest() throws Exception {
        String content = objectMapper.writeValueAsString(new UserCreateRequestDto("email@naver.com","password", "nickname"));

        mvc.perform(post("/users")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Nickname Error Test: 글자수가 1자 이상 25자 이하여야 한다")
    public void nicknameLengthJoinTest() throws Exception {
        String content = objectMapper.writeValueAsString(new UserCreateRequestDto("email@naver.com","password1!", "가나다라마바사아자차카타파하갸냐댜랴먀뱌샤야쟈챠캬탸퍄햐"));

        mvc.perform(post("/users")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("비밀번호 찾기 테스트")
    public void passwordFind() throws Exception {
        final String email = "test@naver.com";
        final String id = "test_id";
        MultiValueMap<String, String> info = new LinkedMultiValueMap<>();
        info.add("email",email);
        info.add("id",id);
        mvc.perform(get("/password").params(info)).andExpect(status().isAccepted());
    }

    @Test
    @DisplayName("로그인ㅋ.ㅋ")
    public void loginSuccessTest() throws Exception {
        String content = objectMapper.writeValueAsString(new UserLoginRequestDto("email@naver.com","password1!"));

        mvc.perform(post("/auth")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("로그인ㅋ.ㅋ 실패: 비밀번호 형식 일치하지 않음")
    public void loginFailedTest() throws Exception {
        String content = objectMapper.writeValueAsString(new UserLoginRequestDto("email@naver.com","password1"));

        mvc.perform(post("/auth")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
