package com.fourthwall.moviesapi.config

import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority

class TokenAuthentication(private val token: String,private val subject: String, private val roles: List<String>) : AbstractAuthenticationToken(null) {
    override fun getCredentials(): Any {
        return token
    }

    override fun getPrincipal(): Any {
        return subject
    }

    override fun isAuthenticated(): Boolean {
        return token.isNotEmpty() && subject.isNotEmpty() && roles.isNotEmpty()
    }

    override fun getAuthorities(): List<GrantedAuthority> {
        return MutableList(roles.size) { index -> SimpleGrantedAuthority("ROLE_${roles[index]}") }
    }

}