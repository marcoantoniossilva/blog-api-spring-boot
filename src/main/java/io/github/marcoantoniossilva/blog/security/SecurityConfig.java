package io.github.marcoantoniossilva.blog.security;

import io.github.marcoantoniossilva.blog.common.TokenConfiguration;
import io.github.marcoantoniossilva.blog.domain.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final UserService userService;
  private final CustomPasswordEncoder customPasswordEncoder;
  private final TokenConfiguration tokenConfiguration;
  private final JwtManager jwtManager;

  public SecurityConfig(UserService userService, CustomPasswordEncoder customPasswordEncoder, TokenConfiguration tokenConfiguration, JwtManager jwtManager) {
    this.userService = userService;
    this.customPasswordEncoder = customPasswordEncoder;
    this.tokenConfiguration = tokenConfiguration;
    this.jwtManager = jwtManager;
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userService).passwordEncoder(customPasswordEncoder);
  }

  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring()
        .antMatchers(HttpMethod.POST,"/users/login")
        .antMatchers(HttpMethod.OPTIONS, "/**")
        .antMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable()
        .authorizeRequests()
        .anyRequest()
        .authenticated();

    http.addFilterBefore(new AuthorizationFilter(tokenConfiguration,jwtManager, userService), UsernamePasswordAuthenticationFilter.class);
  }

  @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }
}
