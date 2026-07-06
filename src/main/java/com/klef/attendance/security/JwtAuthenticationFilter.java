package com.klef.attendance.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.klef.attendance.config.JwtUtil;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        System.out.println("\n==========================================");
        System.out.println("JWT Authentication Filter Executed");
        System.out.println("Request URI : " + request.getRequestURI());

        final String authHeader = request.getHeader("Authorization");

        System.out.println("Authorization Header : " + authHeader);

        String jwt = null;
        String email = null;

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {

            System.out.println("No Bearer Token Found!");

            filterChain.doFilter(request, response);
            return;
        }

        jwt = authHeader.substring(7);

        System.out.println("JWT Token : " + jwt);

        try {

            email = jwtUtil.extractUsername(jwt);

            System.out.println("Email Extracted : " + email);

        } catch (ExpiredJwtException e) {

            System.out.println("JWT Token Expired!");

            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");

            response.getWriter().write("""
            {
                "status":401,
                "message":"JWT Token has expired. Please login again."
            }
            """);

            return;

        } catch (Exception e) {

            System.out.println("Invalid JWT!");
            e.printStackTrace();

            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");

            response.getWriter().write("""
            {
                "status":401,
                "message":"Invalid JWT Token."
            }
            """);

            return;
        }

        if (email != null &&
                SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails =
                    userDetailsService.loadUserByUsername(email);

            System.out.println("User Loaded : " + userDetails.getUsername());
            System.out.println("Authorities : " + userDetails.getAuthorities());

            boolean valid =
                    jwtUtil.validateToken(jwt, userDetails.getUsername());

            System.out.println("Token Valid : " + valid);

            if (valid) {

                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities());

                authToken.setDetails(
                        new WebAuthenticationDetailsSource()
                                .buildDetails(request));

                SecurityContextHolder
                        .getContext()
                        .setAuthentication(authToken);

                System.out.println("Authentication Successful!");
                System.out.println("Security Context : "
                        + SecurityContextHolder.getContext().getAuthentication());

            } else {

                System.out.println("Token Validation Failed!");
            }
        }

        System.out.println("JWT Filter Completed");
        System.out.println("==========================================\n");

        filterChain.doFilter(request, response);
    }
}