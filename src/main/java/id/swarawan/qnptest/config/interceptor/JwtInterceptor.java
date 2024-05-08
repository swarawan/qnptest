package id.swarawan.qnptest.config.interceptor;

import id.swarawan.qnptest.service.JwtService;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class JwtInterceptor implements HandlerInterceptor {

    private JwtService service;

    @Autowired
    public JwtInterceptor(JwtService service) {
        this.service = service;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        Boolean isLoginEndpoint = request.getServletPath().endsWith("login/default");
        if (isLoginEndpoint) {
            return true;
        }

        String bearerToken = request.getHeader("Authorization");
        if (bearerToken == null || bearerToken.isEmpty()) {
            throw new JwtException("Access token is required");
        }

        String accessToken = bearerToken.substring("Bearer ".length());
        Boolean valid = service.validateToken(accessToken);
        if (!valid) {
            throw new JwtException("Access token is expire");
        }

        return true;
    }
}
