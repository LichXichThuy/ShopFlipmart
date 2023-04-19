package groupthree.shopflipmart.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Autowired
    FilterChain filterChain = new FilterChain();

    @Bean
    public SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {// @formatter:off

        httpSecurity
                .cors()
                .and().authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/assets/**").anonymous()
                .antMatchers("/assets/**").permitAll()
                .anyRequest().authenticated()
//                .and().httpBasic()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().csrf().disable()
                .addFilterBefore(filterChain, UsernamePasswordAuthenticationFilter.class)
        ;
        return httpSecurity.build();
    }
}
