package com.bootcamp.emazon.user.infraestructure.configuration.security;
import com.bootcamp.emazon.user.domain.util.DomainConstants;
import com.bootcamp.emazon.user.infraestructure.security.jwt.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(http -> {
                                        http.requestMatchers(HttpMethod.POST,"/auth/login").permitAll();
                                        http.requestMatchers(HttpMethod.POST, "/categories/**", "/brands/**", "/articles/**").hasAuthority(DomainConstants.Roles.ADMIN_ROLE);
                                        http.requestMatchers(HttpMethod.POST, "/users/create/aux_bodega").hasAuthority(DomainConstants.Roles.ADMIN_ROLE);
                                        http.requestMatchers(HttpMethod.POST, "/supply/**").hasAuthority(DomainConstants.Roles.AUX_ROLE);
                                        http.requestMatchers(HttpMethod.POST, "/cart/**").hasAuthority(DomainConstants.Roles.CUSTOMER_ROLE);
                                        http.requestMatchers(HttpMethod.DELETE, "/cart/**").hasAuthority(DomainConstants.Roles.CUSTOMER_ROLE);
                                        http.requestMatchers(HttpMethod.POST, "/buy/**").hasAuthority(DomainConstants.Roles.CUSTOMER_ROLE);

                                        http.anyRequest().authenticated();
                })
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
