package ar.zotta.forozotta.infra.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;

import ar.zotta.forozotta.domain.user.User;

@Service
public class TokenService {

  @Value("${forozotta.secret}")
  private String jwtSecret;

  public String generateToken(User user) {
    Instant expDate = LocalDateTime.now(ZoneOffset.UTC)
        .plusMinutes(20)
        .toInstant(ZoneOffset.UTC);

    try {
      Algorithm algorithm = Algorithm.HMAC256(jwtSecret);

      String token = JWT.create()
          .withIssuer("ZOTTA Inc.")
          .withSubject(user.getUsername())
          .withClaim("id", user.getId())
          .withExpiresAt(expDate)
          .sign(algorithm);

      return token;

    } catch (Exception e) {
      throw new RuntimeException("ERROR EN EL TOKEN");
    }
  }

  public String getSubject(String token) {
    if (token == null) {
      throw new RuntimeException();
    }
    DecodedJWT verifier = null;
    try {
      Algorithm algorithm = Algorithm.HMAC256(jwtSecret); // validando firma
      verifier = JWT.require(algorithm)
          // .withIssuer("ZOTTA Inc.")
          .build()
          .verify(token);
      verifier.getSubject();
    } catch (TokenExpiredException e) {
      throw new TokenExpiredException("Token expirado desde ", e.getExpiredOn());
    } catch (JWTVerificationException exception) {
      throw new JWTVerificationException("Error de token: " + exception.getMessage());
    }

    return verifier.getSubject();
  }

  public DecodedJWT decodeJwt(String token) {
    return JWT.decode(token);
  }

}
