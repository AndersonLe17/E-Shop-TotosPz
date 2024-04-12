package com.totospz.eshop.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.totospz.eshop.config.response.EntityResponse;
import com.totospz.eshop.config.response.ErrorResponse;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.util.List;

public class FilterManager {

    public static void responseExceptionFilter(HttpServletResponse response, Exception ex) throws IOException {
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(ex.getClass().getSimpleName());
        mapper.writeValue(response.getOutputStream(),
                EntityResponse.builder()
                        .code(HttpStatus.FORBIDDEN.value())
                        .status(HttpStatus.FORBIDDEN.getReasonPhrase())
                        .message("Denied")
                        .errors(List.of(ErrorResponse.builder().error("Unauthorized").msg(getMessageException(ex)).build()))
                        .build()
        );
    }

    private static String getMessageException(Exception ex) {
        switch (ex.getClass().getSimpleName()) {
            case "SignatureVerificationException":
                return "El token es inválido.";
            case "TokenExpiredException":
                return "El token expiró.";
            default: {
                return ex.getMessage();
            }
        }
    }

}
