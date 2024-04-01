package com.totospz.eshop.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.totospz.eshop.config.response.EntityResponse;
import com.totospz.eshop.config.response.ErrorResponse;
import com.totospz.eshop.domain.model.Usuario;
import com.totospz.eshop.repository.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String authHeader = request.getHeader("Authorization");

            if (authHeader != null && !authHeader.isBlank()) {
                String token = authHeader.replace("Bearer ", "");
                String subject = tokenService.getSubject(token);
                Usuario usuario = usuarioRepository.findByUsuNom(subject).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
                UsernamePasswordAuthenticationToken user = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(user);
                request.setAttribute("usuCod", usuario.getUsuCod());
            } else if (request.getRequestURI().endsWith("/auth/login")) {}
            else throw new Exception("No se ha enviado el token.");

            filterChain.doFilter(request, response);
        } catch (Exception ex) {
            this.responseExceptyionFilter(response, ex);
        }
    }

    private void responseExceptyionFilter(HttpServletResponse response, Exception ex) throws IOException {
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

    private String getMessageException(Exception ex) {
        switch (ex.getClass().getSimpleName()) {
            case "SignatureVerificationException":
                return "El token es invalido.";
            case "TokenExpiredException":
                return "El token expiró.";
            default:
                return ex.getMessage();
        }
    }
}
