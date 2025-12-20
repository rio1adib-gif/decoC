package com.digitalevidence.evidencecustody.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String jwtToken = null;
        String username = null;

        try {
            // 1. Try to get token from Header (API calls)
            final String authHeader = request.getHeader("Authorization");
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                jwtToken = authHeader.substring(7);
            }
            // 2. If no header, try to get token from Cookies (View Access)
            else if (request.getCookies() != null) {
                for (Cookie cookie : request.getCookies()) {
                    if ("jwt_token".equals(cookie.getName())) {
                        jwtToken = cookie.getValue();
                        break;
                    }
                }
            }

            // 3. Validate Token
            if (jwtToken != null && jwtUtil.validateToken(jwtToken)) {
                username = jwtUtil.extractUsername(jwtToken);
            }

            // 4. Authenticate User
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                // CRITICAL FIX: Try-Catch block prevents 401 crashes if the user is missing/deleted
                try {
                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(
                                    userDetails,
                                    null,
                                    userDetails.getAuthorities()
                            );

                    authToken.setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(request)
                    );

                    SecurityContextHolder.getContext().setAuthentication(authToken);
                } catch (Exception e) {
                    System.out.println("⚠️ Auth Failed (User not found): " + username);
                    // Do NOT throw exception. Let request proceed as Anonymous.
                }
            }
        } catch (Exception e) {
            System.out.println("⚠️ JWT Error: " + e.getMessage());
        }

        filterChain.doFilter(request, response);
    }
}