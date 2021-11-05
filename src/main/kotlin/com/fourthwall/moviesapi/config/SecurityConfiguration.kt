package com.fourthwall.moviesapi.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter


@EnableWebSecurity
@EnableGlobalMethodSecurity(
    securedEnabled = true,
    jsr250Enabled = true,
    prePostEnabled = true
)
class SecurityConfiguration : WebSecurityConfigurerAdapter() {

    @Autowired
    lateinit var jwtFilter: JwtFilter

    override fun configure(http: HttpSecurity) {
        http
            .cors().and().csrf().disable()
            .addFilterBefore(jwtFilter, BasicAuthenticationFilter::class.java)
            .authorizeRequests()
            .antMatchers(HttpMethod.POST, "/v1/user").permitAll()
            .antMatchers(HttpMethod.POST, "/v1/auth").permitAll()
            .antMatchers(HttpMethod.GET, "/health").permitAll()
            .anyRequest().authenticated()
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    }

}