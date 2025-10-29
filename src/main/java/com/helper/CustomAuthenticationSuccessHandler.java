package com.helper;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import com.Entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    // logger
    Logger logger = LoggerFactory.getLogger(CustomAuthenticationSuccessHandler.class);

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {

        User userDetails = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // String userId = userDetails.getUsername();
        String redirectURL = request.getContextPath();

        if (userDetails.hasRole("ADMIN")) {
            redirectURL = "/admin/home";
            logger.info("Admin user logged in ");

        } else if (userDetails.hasRole("DEVELOPER")) {
            redirectURL = "/developer/home";
            logger.info("Developer user logged in ");
        } else if (userDetails.hasRole("TESTER")) {
            redirectURL = "/tester/home";
            logger.info("Tester user is logged in: ");
        }
        response.sendRedirect(redirectURL);

    }

}
