package com.github.jackson.jwtexample.jwt

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import java.util.*

class Jwt(
    secretKey: String,
    private val issuer: String,
    private val expirySeconds: Long
) {

    private val key = Keys.hmacShaKeyFor(secretKey.toByteArray())

    fun sign(claims: Claims): String {
        val builder = Jwts.builder()
        builder.setIssuer(issuer)
        builder.setClaims(claims)
        if (expirySeconds > 0) {
            val now = Date()
            builder.setExpiration(
                now.apply {
                    time = now.time + expirySeconds * 1000
                }
            )
        }
        builder.signWith(key, SignatureAlgorithm.HS512)
        return builder.compact()
    }

    fun verify(token: String): Jws<Claims> = Jwts.parserBuilder()
        .setSigningKey(key)
        .build()
        .parseClaimsJws(token)

}