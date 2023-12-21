package com.hotelreservation;

import com.hotelreservation.api.request.UserAddRequest;
import com.hotelreservation.rest.exception.AuthException;
import com.hotelreservation.rest.validator.UserValidator;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;


//@ExtendWith(MockitoJUnitRunner.class) // @RunWith replaced by the more powerful @ExtendWith annotation...
@ContextConfiguration(classes = {HotelreservationApplicationTests.class})

@WebMvcTest(UserValidatorTest.class)

@RequiredArgsConstructor

public class UserValidatorTest {

    private final MockMvc mockMvc;

    @MockBean
    private final UserValidator userValidator;

    @Test
    public void testValidtaor() throws AuthException {
        UserAddRequest userAddRequest = new UserAddRequest();

        userAddRequest.setFirstName("Ataberk");
        userAddRequest.setUsername("Pfand");
        userAddRequest.setLastName("Bakir");
        userAddRequest.setEmail("bakirr.ataberk@gmail.com");
        userAddRequest.setPassword("2023konya");

        userValidator.validateUserRegister(userAddRequest);

        //todo learn how to test uservalidator like this

//        assertEquals(null,userValidator.validateUserRegister(userAddRequest));
    }
}