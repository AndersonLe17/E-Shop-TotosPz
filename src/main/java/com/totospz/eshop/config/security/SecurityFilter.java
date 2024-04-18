package com.totospz.eshop.config.security;

import com.totospz.eshop.config.cookie.CookieUtil;
import com.totospz.eshop.domain.model.Usuario;
import com.totospz.eshop.repository.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = CookieUtil.getTokenFromCookie(request);

            if (request.getRequestURI().endsWith("/auth/login")) {}
            else if (token != null) {
                String subject = tokenService.getSubject(token);
                Usuario usuario = usuarioRepository.findByUsuNom(subject).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
                UsernamePasswordAuthenticationToken user = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(user);
                request.setAttribute("usuCod", usuario.getUsuCod());
                String newJWT = tokenService.generateToken(usuario);
                CookieUtil.create(response, "jwt", newJWT, false, tokenService.getExpiredSeconds(newJWT), "localhost");
            } else throw new Exception("No se ha enviado el token.");

            filterChain.doFilter(request, response);
        } catch (Exception ex) {
            FilterManager.responseExceptionFilter(response, ex);
        }
    }

}
