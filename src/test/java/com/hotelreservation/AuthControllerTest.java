package com.hotelreservation;


import com.hotelreservation.rest.controller.AuthController;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

//@ExtendWith(MockitoJUnitRunner.class) // @RunWith replaced by the more powerful @ExtendWith annotation...
@ContextConfiguration(classes = {HotelreservationApplicationTests.class})

@WebMvcTest(AuthController.class)
@RequiredArgsConstructor

public class AuthControllerTest {


    private final MockMvc mockMvc;

    @MockBean
    private final AuthController authController;


    @Test
    public void getRoomList() throws Exception{

    }




}
