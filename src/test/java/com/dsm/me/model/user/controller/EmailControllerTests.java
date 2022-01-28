package com.dsm.me.model.user.controller;

import com.dsm.me.model.user.service.EmailService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
}
