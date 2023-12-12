package com.hotelreservation.auth;

import com.hotelreservation.lib.constants.Constants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private static final int EXPIREDATE = 1000 * 60 * 5/**Minute**/
            ;
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }


    //todo 1:08:00

    public <T> T extractClaim(String token , Function<Claims,T>claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(
            Map<String,Object> extraClaims,
            UserDetails userDetails
    ){
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername()) //
                .setIssuedAt(new Date(System.currentTimeMillis())) //  means when this claim was created and this information will help us to calculate the expiration date or to check if the token is still valid or not okay
                .setExpiration(new Date(System.currentTimeMillis() + EXPIREDATE)) // how long this token should be valid
                .signWith(getSignInKey(), SignatureAlgorithm.HS256) // signwith like which key that we want to use to sign
                .compact(); // will generate and return token
    }

    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(),userDetails);
    }

    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
        // we want to make sure that username we have within the token is the same as the username we have as input(userdetails)

    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
        // .before - > because it's a date , i want to make sure it's before today's date
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);

        //expiration = sure sonu , sona ermesi.
    }

    private Claims extractAllClaims (String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(Constants.JWT_SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
