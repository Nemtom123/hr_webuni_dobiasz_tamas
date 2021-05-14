package hu.webuni.hr.tamasdobiasz.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .passwordEncoder(passwordEncoder())
                .withUser("user").authorities("user").password(passwordEncoder().encode("pass"))
                .and()
                .withUser("user").authorities("user","admin").password(passwordEncoder().encode("pass"));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        Object sessionCreationPolicy;
        http
                     .httpBasic()
                     .and()
                     .csrf().disable()
                     .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)//REST API BEJELENTKEZÉS
                     .and()
                     .authorizeRequests()
                     .antMatchers(HttpMethod.POST, "/api/hr/**").hasAnyAuthority(("admin"))
                     .antMatchers(HttpMethod.PUT, "/api/hr/**").hasAnyAuthority("user","admin")
                     .anyRequest().authenticated();
    }

}
