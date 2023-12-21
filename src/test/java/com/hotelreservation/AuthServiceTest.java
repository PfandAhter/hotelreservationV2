package com.hotelreservation;


import com.hotelreservation.rest.controller.AuthController;
import com.hotelreservation.rest.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthController.class)

public class AuthServiceTest {

    @MockBean
    private  AuthService authService;

    private  MockMvc mockMvc;


    @Test
    void testingSomeThingShouldReturnString() throws Exception{
        when(authService.testingSomeThing()).thenReturn("Selamun Aleykum Calisiyoruz...");
        this.mockMvc.perform(get("/greeting")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Selamun Aleykum Calisiyoruz...")));

    }
}
