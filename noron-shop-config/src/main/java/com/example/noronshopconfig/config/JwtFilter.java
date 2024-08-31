package com.example.noronshopconfig.config;

import com.example.noronshopconfig.exception.NotTokenException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

@Configuration
public class  JwtFilter extends OncePerRequestFilter {

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    CustomUserDetailService customUserDetailService;

    @Autowired
    HandlerExceptionResolver handlerExceptionResolver;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = getTokenFromHeader(request);

            String authHeader = request.getHeader("Authorization");

            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                throw new NotTokenException("Don't have token.");
            }

            if (StringUtils.hasText(token) && jwtUtil.verifyToken(token)) {
                String username = jwtUtil.extractUsername(token);
                UserDetails userDetails = customUserDetailService.loadUserByUsername(username);

                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities()
                );
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }

            filterChain.doFilter(request, response);
        } catch (ExpiredJwtException e) {
            handlerExceptionResolver.resolveException(request, response, null, e);
        } catch (MalformedJwtException e) {
            handlerExceptionResolver.resolveException(request, response, null, e);
        } catch (UnsupportedJwtException e) {
            handlerExceptionResolver.resolveException(request, response, null, e);
        } catch (SignatureException e) {
            handlerExceptionResolver.resolveException(request, response, null, e);
        } catch (JwtException e) {
            handlerExceptionResolver.resolveException(request, response, null, e);
        } catch (IllegalArgumentException e) {
            handlerExceptionResolver.resolveException(request, response, null, e);
        } catch (NotTokenException e) {
            handlerExceptionResolver.resolveException(request, response, null, e);
        }
    }

        public String getTokenFromHeader(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        String token = null;


        if (StringUtils.hasText(header) && header.startsWith("Bearer") && header.substring(6) != ""){
            token = header.substring(7);
        }
            return token;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        if (request.getRequestURI().contains("api") || request.getRequestURI().contains("signin") || request.getRequestURI().contains("register")) {
            return true;
        } else {
            return false;
        }
    }
}
