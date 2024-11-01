package com.uri.qualuga.services;

import com.uri.qualuga.entities.Account;
import com.uri.qualuga.entities.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class TokenService {

    @Autowired
    JwtEncoder jwtEncoder;

    @Autowired
    JwtDecoder jwtDecoder;

    public String generateToken(Account account, Long expiresIn) {
        Instant now = Instant.now();

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("myBackend")
                .subject(account.getId().toString())
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiresIn)).build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    public String getUserIdFromToken(String token) {
        Jwt jwt = jwtDecoder.decode(token);
        return (String) jwt.getClaims().get("sub");
    }

}
