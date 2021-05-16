package com.merenda.merenda.api.infra.security;



import com.merenda.merenda.api.infra.cors.CorsConfig;
import com.merenda.merenda.api.infra.security.jwt.JwtAuthenticationFilter;
import com.merenda.merenda.api.infra.security.jwt.JwtAuthorizationFilter;
import com.merenda.merenda.api.infra.security.jwt.handler.AccessDeniedHandler;
import com.merenda.merenda.api.infra.security.jwt.handler.UnauthorizedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("userDetailsService")
    private UserDetailsService userDetailsService;

    @Autowired
    private UnauthorizedHandler unauthorizedHandler;

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        AuthenticationManager authManager = authenticationManager();

        http.cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues())
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/email","/api/v1/login","/api/v1/users","/api/v1/itens/totalMes/{ano}","/api/v1/itens/totalCategoria/{ano}","/api/v1/itens/totalMes/{ano}","/api/v1/itens/total/{ano}","/api/v1/escolas/quantAlunos","/api/v1/itens/tradicional/{ano}","/api/v1/itens/familiar/{ano}","/api/v1/pnae/soma/{ano}","/api/v1/itens/totalMes/{ano}","/api/v1/itens/totalCategoria/{ano}","/api/v1/itens/totalEscolas/{ano}","/api/v1/itens/mediaAlunos/{ano}")
                .permitAll()
                .antMatchers(HttpMethod.POST, "/api/v1/users","/email")
                .permitAll()
                .antMatchers(HttpMethod.PUT,"/api/v1/usuarui/{$id}")
                .permitAll()
                .antMatchers("/v2/api-docs", "/configuration/**", "/swagger*/**", "/webjars/**")
                .permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable()
                .addFilter(new CorsConfig())
                .addFilter(new JwtAuthenticationFilter(authManager))
                .addFilter(new JwtAuthorizationFilter(authManager, userDetailsService))
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler)
                .authenticationEntryPoint(unauthorizedHandler)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        auth.userDetailsService(userDetailsService).passwordEncoder(encoder);
    }

}
