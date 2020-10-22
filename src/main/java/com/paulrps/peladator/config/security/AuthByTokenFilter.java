package com.paulrps.peladator.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.paulrps.peladator.config.exceptions.ApiErrorResponse;
import com.paulrps.peladator.config.exceptions.ApiException;
import com.paulrps.peladator.config.exceptions.ApiExceptionService;
import com.paulrps.peladator.config.exceptions.ApiMessageEnum;
import com.paulrps.peladator.domain.entities.User;
import com.paulrps.peladator.services.TokenService;
import com.paulrps.peladator.services.UserService;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

@AllArgsConstructor
public class AuthByTokenFilter extends OncePerRequestFilter {

  private TokenService tokenService;
  private UserService userService;
  private SpringSecuritedPaths springSecuritedPaths;
  private ApiExceptionService apiExceptionService;

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    String token = getToken(request);
    boolean isPathPermited = isRequestedPathPermited(request).isPresent();
    if (Objects.isNull(token) && !isPathPermited) {
      handleException(new ApiException(ApiMessageEnum.ERROR_TOKEN_NOT_PRESENT), request, response);
      return;
    }

    boolean isValid = tokenService.isValidToken(token);

    if (isValid) {
      authenticateUser(token);
    } else if (!isPathPermited) {
      handleException(
          new ApiException(ApiMessageEnum.ERROR_TOKEN_INVALID, token), request, response);
      return;
    }

    filterChain.doFilter(request, response);
  }

  private void authenticateUser(String token) {

    Long userId = tokenService.getUserId(token);
    Optional<User> user = userService.find(userId);

    if (user.isPresent()) {
      UsernamePasswordAuthenticationToken auth =
          new UsernamePasswordAuthenticationToken(user.get(), null, user.get().getAuthorities());
      SecurityContextHolder.getContext().setAuthentication(auth);
    }
  }

  private String getToken(HttpServletRequest request) {

    String token = request.getHeader("Authorization");

    if (token == null || token.isEmpty() || !token.startsWith(tokenService.getTokenType())) {
      return null;
    }

    return token.substring(7);
  }

  private void handleException(
      ApiException e, HttpServletRequest request, HttpServletResponse response) {
    ResponseEntity<ApiErrorResponse> error =
        apiExceptionService.handlePeladatorExceptino(e, request);

    try {
      response.setStatus(error.getStatusCode().value());
      response.getWriter().write(new ObjectMapper().writeValueAsString(error.getBody()));
    } catch (IOException ioException) {
      ioException.printStackTrace();
    }
  }

  private Optional<String> isRequestedPathPermited(HttpServletRequest request) {
    return Stream.of(springSecuritedPaths.getAllPermitedPaths())
        .filter(
            path ->
                path.equals(request.getRequestURI().substring(request.getContextPath().length())))
        .findFirst();
  }
}
