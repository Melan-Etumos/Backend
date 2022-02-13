package com.dsm.me.model.user.controller;

import com.dsm.me.model.user.service.EmailService;
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

@WebMvcTest(controllers = EmailController.class)
public class EmailControllerTests {
    @Autowired
    private MockMvc mvc;
    @MockBean
    EmailService emailService;

    @Test
    @DisplayName("email send success test")
    public void sendEmailCodeTest() throws Exception {
        String email = "test@naver.com";
        mvc.perform(get("/email").param("email", email))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("email send failed test")
    public void sendEmailFailedTest() throws Exception {
        final String email = "test.com";
        mvc.perform(get("/email").param("email", email))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("email verification code check success test")
    public void emailCodeCheckTest() throws Exception {
        final String email = "test@naver.com";

        mvc.perform(post("/email/{email}",email)
                .content("000000")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("email verification code check fail test")
    public void emailCodeCheckFailTest() throws Exception {
        final String email = "test.com";

        mvc.perform(post("/email/{email}",email)
                .content("000000")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

}
