package com.goku.security;

import com.goku.entity.RoleEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;

@Component
public class AuthJwtTokenProvider {

    @Value("${jwt.secret}")
    private String secretKey;

    private final long validityInMs = 3600000;

    private final ImplementUserDetails implementUserDetails;

    @Autowired
    public AuthJwtTokenProvider(ImplementUserDetails implementUserDetails) {
        this.implementUserDetails = implementUserDetails;
    }

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String generateAuthToken(String username, RoleEntity roleEntity) {

        Claims claims = Jwts.claims().setSubject(username);
        claims.put("auth", Arrays.asList(new SimpleGrantedAuthority(roleEntity.getRoleName())));

        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMs);

        return Jwts.builder().setClaims(claims).setIssuedAt(now).setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secretKey).compact();
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = implementUserDetails.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getUsername(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    public String resolveAuthToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public boolean validateAuthToken(String token) throws Exception {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);

        } catch (JwtException | IllegalArgumentException e) {
            throw new Exception("Expired or invalid JWT token");
        }
        return true;
    }
}
