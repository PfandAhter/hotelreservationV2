package com.hotelreservation.auth;
import com.hotelreservation.api.response.AuthUserResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.HandlerInterceptor;

public class BearerTokenInterceptor implements HandlerInterceptor {

    //private final AuthUserResponse authUserResponse;
    private final AuthUserResponse authUserResponse;

    // parameter is AuthUserResponse authUserResponse

    public BearerTokenInterceptor(AuthUserResponse authUserResponse){
         this.authUserResponse = authUserResponse;
    }

    String token = "";
   /* @Override
    public boolean preHandle (HttpServletRequest request,
                              HttpServletResponse response, Object handler) throws Exception{
        final String authorizationHeaderValue = request.getHeader("Authorization");
        if(authorizationHeaderValue != null && authorizationHeaderValue.startsWith("Bearer ")){
//            String token = authorizationHeaderValue.substring(7,authorizationHeaderValue.length());
            token = authorizationHeaderValue.split(" ")[1];
            if(authUserResponse.getToken() == null || !token.equals(authUserResponse.getToken())){
                authUserResponse.setToken(token);
            }
        }
        return true;
    }*/
}
