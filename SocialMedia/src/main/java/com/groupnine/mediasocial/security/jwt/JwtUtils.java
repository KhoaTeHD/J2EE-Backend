package com.groupnine.mediasocial.security.jwt;

import java.security.Key;
import java.util.Date;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.util.WebUtils;
import org.springframework.http.ResponseCookie;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.groupnine.mediasocial.service.UserDetailsImpl;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtils {
	private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

	@Value("${groupnine.mediasocial.app.jwtSecret}")
	private String jwtSecret;

	@Value("${groupnine.mediasocial.app.jwtExpirationMs}")
	private int jwtExpirationMs;

	private String jwtCookie;
	
	public String getJwtFromCookies(HttpServletRequest request) {
	    Cookie cookie = WebUtils.getCookie(request, jwtCookie);
	    if (cookie != null) {
	      return cookie.getValue();
	    } else {
	      return null;
	    }
	  }

	  public ResponseCookie generateJwtCookie(UserDetailsImpl userPrincipal) {
	    String jwt = generateTokenFromUsername(userPrincipal.getGmail());
	    ResponseCookie cookie = ResponseCookie.from(jwtCookie, jwt).path("/api").maxAge(24 * 60 * 60).httpOnly(true).build();
	    return cookie;
	  }

	  public ResponseCookie getCleanJwtCookie() {
	    ResponseCookie cookie = ResponseCookie.from(jwtCookie, null).path("/api").build();
	    return cookie;
	  }
	public String generateJwtToken(Authentication authentication) {

		UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

		return Jwts.builder().setSubject((userPrincipal.getGmail())).setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
				.signWith(key(), SignatureAlgorithm.HS256).compact();
	}

	private Key key() {
		return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
	}

	public String getUserNameFromJwtToken(String token) {
		return Jwts.parserBuilder().setSigningKey(key()).build().parseClaimsJws(token).getBody().getSubject();
	}

	public boolean validateJwtToken(String authToken) {
		try {
			Jwts.parserBuilder().setSigningKey(key()).build().parse(authToken);
			return true;
		} catch (MalformedJwtException e) {
			logger.error("Invalid JWT token: {}", e.getMessage());
		} catch (ExpiredJwtException e) {
			logger.error("JWT token is expired: {}", e.getMessage());
		} catch (UnsupportedJwtException e) {
			logger.error("JWT token is unsupported: {}", e.getMessage());
		} catch (IllegalArgumentException e) {
			logger.error("JWT claims string is empty: {}", e.getMessage());
		}

		return false;
	}
	 public String generateTokenFromUsername(String gmail) {   
		    return Jwts.builder()
		               .setSubject(gmail)
		               .setIssuedAt(new Date())
		               .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
		               .signWith(key(), SignatureAlgorithm.HS256)
		               .compact();
		  }
}
