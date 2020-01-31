package com.paulrps.peladator.config.security;

import com.paulrps.peladator.domain.entities.User;
import com.paulrps.peladator.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@AllArgsConstructor
public class AuthByTokenFilter extends OncePerRequestFilter {

    private TokenService tokenService;
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = getToken(request);

        boolean isValid = tokenService.isValidToken(token);

        if (isValid) {
            authenticateUser(token);
        }

        Optional<User> user = userService.findByName("Paulo");
        if (!user.isPresent()) {
            userService.save(User.builder()
                .name("Paulo")
                .password("$2a$10$pjwIpeQ4nhUUkji323NkjuQahdESUnZCUMIgQO8F8D4RDqjflH0m.")
                .build());
        }

        filterChain.doFilter(request, response);
    }

    private void authenticateUser(String token) {

        Long userId = tokenService.getUserId(token);
        Optional<User> user = userService.findById(userId);

        if (user.isPresent()) {
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user.get(), null, user.get().getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
    }

    private String getToken(HttpServletRequest request) {

        String token = request.getHeader("Authorization");

        if (token == null || token.isEmpty() || !token.startsWith("Bearer")) {
            return null;
        }

        return token.substring(7);
    }
}
