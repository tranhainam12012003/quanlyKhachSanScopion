package vn.teca.scopio.base.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import vn.teca.scopio.base.model.login.UserDTO;

import java.io.Serializable;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class JwtTokenUtil implements Serializable {

    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

    private static final long serialVersionUID = 6586152071785874075L;

    @Value("${jwt.secret}")
    private String secret;

    //retrieve username from jwt token
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    //retrieve expiration date from jwt token
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public UserDTO getUserFromToken(String token) {
        final Claims claims = getAllClaimsFromToken(token);
        Map<String, Object> map = (Map<String, Object>) claims.get("user_details");
        List<Map<String, Object>> authorities = (List<Map<String, Object>>) claims.get("authorities");

        List<SimpleGrantedAuthority> privileges = authorities.stream()
                .map(m -> m.getOrDefault("authority", "").toString())
                .filter(StringUtils::isNotBlank)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        return UserDTO.builder()
                .login(Optional.ofNullable(map.getOrDefault("login", null)).map(Object::toString).orElse(""))
                .type(Optional.ofNullable(map.getOrDefault("type", null)).map(o -> Long.parseLong(o.toString())).orElse(null))
                .fullName(Optional.ofNullable(map.getOrDefault("fullName", null)).map(Object::toString).orElse(""))
                .email(Optional.ofNullable(map.getOrDefault("email", null)).map(Object::toString).orElse(""))
                .userLevel(Optional.ofNullable(map.getOrDefault("userLevel", null)).map(Object::toString).orElse(""))
                .phoneNumber(Optional.ofNullable(map.getOrDefault("phoneNumber", null)).map(Object::toString).orElse(""))
                .position(Optional.ofNullable(map.getOrDefault("position", null)).map(Object::toString).orElse(""))
                .soDienThoai(Optional.ofNullable(map.getOrDefault("soDienThoai", null)).map(Object::toString).orElse(""))
                .maDonVi(Optional.ofNullable(map.getOrDefault("maDonVi", null)).map(Object::toString).orElse(""))
                .tenDonVi(Optional.ofNullable(map.getOrDefault("tenDonVi", null)).map(Object::toString).orElse(""))
                .maPhongBan(Optional.ofNullable(map.getOrDefault("maPhongBan", null)).map(Object::toString).orElse(""))
                .tenPhongBan(Optional.ofNullable(map.getOrDefault("tenPhongBan", null)).map(Object::toString).orElse(""))
                .maChucVu(Optional.ofNullable(map.getOrDefault("maChucVu", null)).map(Object::toString).orElse(""))
                .tenChucVu(Optional.ofNullable(map.getOrDefault("tenChucVu", null)).map(Object::toString).orElse(""))
                .authorities(new HashSet<>(privileges))
                .build();
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    //for retrieving any information from token we will need the secret key
    public Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    //check if the token has expired
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    //generate token for user
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("user_details", userDetails);

        return doGenerateToken(claims, userDetails.getUsername());
    }

    //while creating the token -
    //1. Define  claims of the token, like Issuer, Expiration, Subject, and the ID
    //2. Sign the JWT using the HS512 algorithm and secret key.
    //3. According to JWS Compact Serialization(https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1)
    //   compaction of the JWT to a URL-safe string
    private String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    //validate token
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
