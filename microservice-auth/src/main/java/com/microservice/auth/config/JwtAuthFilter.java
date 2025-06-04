package com.microservice.auth.config;

import java.io.IOException;
import java.util.Optional;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.microservice.auth.client.AuthClient;
import com.microservice.auth.entity.UsuarioEntity;
import com.microservice.auth.service.JwtService;

import io.micrometer.common.lang.NonNull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter{

	private final JwtService jwtService;
	private final UserDetailsService userDetailsService;
	private final AuthClient authClient;
	@Override
	protected void doFilterInternal(@NonNull HttpServletRequest request,@NonNull HttpServletResponse response,@NonNull FilterChain filterChain)
			throws ServletException, IOException {
		if(request.getServletPath().contains("/gpp/auth") || request.getServletPath().contains("/gpp/usuario")) {
			filterChain.doFilter(request, response);
			return;
		}
		
		final String authHeader =  request.getHeader(HttpHeaders.AUTHORIZATION);
		if(authHeader == null || !authHeader.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}
		
		final String jwtToken = authHeader.substring(7);
		final String email = jwtService.extractUsername(jwtToken);
		
		if(email == null || SecurityContextHolder.getContext().getAuthentication() != null) {
			return;
		}
		
		final UserDetails userDetails = this.userDetailsService.loadUserByUsername(email);
		final UsuarioEntity usuario = UsuarioEntity.parse(authClient.buscarPorEmail(userDetails.getUsername()));
		
		if(usuario == null) {
			filterChain.doFilter(request, response);
			return;
		}
		
		final var authToken = new UsernamePasswordAuthenticationToken(
				userDetails,
				null,
				userDetails.getAuthorities()
				);
			authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			SecurityContextHolder.getContext().setAuthentication(authToken);
			filterChain.doFilter(request, response);
		
	}
	
	
}
