package com.totospz.eshop.config.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.totospz.eshop.domain.model.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Base64;

@Service
public class TokenService {

    @Value("${api.security.secret}")
    private String apiSecret;

    public String generateToken(Usuario usuario) {
        try {
            return JWT.create()
                    .withIssuer("totospz")
                    .withSubject(usuario.getUsername())
                    .withClaim("usuCod", usuario.getUsuCod())
                    .withClaim("usuPerNom", usuario.getUsuPer().getPerNom()
                            + " | " + usuario.getUsuPer().getPerApePat()
                            + " | " + usuario.getUsuPer().getPerApeMat()
                    )
                    .withClaim("usuCorEle", usuario.getUsuCorEle())
                    .withClaim("usuPerf", usuario.getUsuPerf().getPerfNom())
                    .withExpiresAt(generateExpirationToken())
                    .sign(getAlgorithm());
        } catch (JWTCreationException exception) {
            throw new RuntimeException();
        }
    }

    public String getSubject(String token) {
        DecodedJWT verifier = JWT.require(getAlgorithm())
                .withIssuer("totospz")
                .build()
                .verify(token);

        return verifier.getSubject();
    }

    public Long getExpiredTime(String token) {
        DecodedJWT verifier = JWT.require(getAlgorithm())
                .withIssuer("totospz")
                .build()
                .verify(token);

        return verifier.getExpiresAtAsInstant().getEpochSecond();
    }

    public Integer getExpiredSeconds(String token) {
        Long now = Instant.now().getEpochSecond();
        Long expired = this.getExpiredTime(token);
        return (int) (expired - now);
    }

    public Instant generateExpirationToken() {
        return LocalDateTime.now().plusHours(4).toInstant(ZoneOffset.of("-05:00"));
    }

    private Algorithm getAlgorithm() {
        byte[] decodeSecret = Base64.getDecoder().decode(apiSecret);
        return  Algorithm.HMAC256(new String(decodeSecret));
    }

}
