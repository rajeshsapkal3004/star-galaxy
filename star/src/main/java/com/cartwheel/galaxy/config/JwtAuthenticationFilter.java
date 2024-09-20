package com.cartwheel.galaxy.config;

import com.cartwheel.galaxy.service.impl.UserDetailsServiceImpl;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    private JwtUtils jwtUtils;

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        final String requestTokenHeader = request.getHeader("Authorization");
        System.out.println(requestTokenHeader);
        String username=null;
        String jwtToken=null;
        if(requestTokenHeader!=null && requestTokenHeader.startsWith("Bearer "))
        {
            try {
                jwtToken=requestTokenHeader.substring(7);
                username = this.jwtUtils.extractUsername(jwtToken);

            }
            catch(ExpiredJwtException e)
            {
                e.printStackTrace();
                System.out.println("Jwt Token has expired");
            }
            catch(Exception e)
            {
                e.printStackTrace();
                System.out.println("error");
            }
        }
        else
        {
            System.out.println("Invalid Token, Not Start with Bearer String");
        }
        //validate token
        if(username!=null&&SecurityContextHolder.getContext().getAuthentication()==null)
        {
            final UserDetails userDetails = this.userDetailsServiceImpl.loadUserByUsername(username);

            if(this.jwtUtils.validateToken(jwtToken, userDetails)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthentication = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                usernamePasswordAuthentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                //if token is valid
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthentication);
            }
        }
        else
        {
            System.out.println("Token is not valid");
        }

        filterChain.doFilter(request, response);
    }


}
