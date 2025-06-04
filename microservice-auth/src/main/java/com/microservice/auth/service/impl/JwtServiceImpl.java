package com.microservice.auth.service.impl;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.microservice.auth.entity.UsuarioEntity;
import com.microservice.auth.service.JwtService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtServiceImpl implements JwtService{
	
	@Value("${application.security.jwt.secret-key}")
	private String secretKey;
	@Value("${application.security.jwt.expiration}")
	private long jwtExpiration;

	@Override
	public String generarToken(UsuarioEntity usuario) {
		return tokenBuilder(usuario, jwtExpiration);
	}

	@Override
	public String tokenBuilder(UsuarioEntity usuario, long jwtExpiration) {
		return Jwts.builder()
				.id(UUID.randomUUID().toString())
				.claim("id",usuario.getId())
				.claim("nombre", usuario.getNombre())
				.subject(usuario.getEmail())
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis() + jwtExpiration))
				.signWith(obtenerKey())
				.compact();
	}
	private SecretKey obtenerKey() {
		byte [] keyBytes = Decoders.BASE64.decode(secretKey);
		return Keys.hmacShaKeyFor(keyBytes);
	}

	@Override
	public String extractUsername(String token) {
		Claims jwtToken = Jwts.parser()
				.verifyWith(obtenerKey())
				.build()
				.parseSignedClaims(token)
				.getPayload();
		return jwtToken.getSubject();
	}
}
