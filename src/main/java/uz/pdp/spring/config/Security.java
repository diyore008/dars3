package uz.pdp.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class Security extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("super_admin").password(passwordEncoder().encode("super_admin")).roles("SUPER_ADMIN").authorities("READ_ALL","READ_ONE","ADD","UP","DELETE")
                .and()
                .withUser("moderator").password(passwordEncoder().encode("moderator")).roles("MODERATOR").authorities("READ_ALL","READ_ONE","ADD","UP")
                .and()
                .withUser("operator").password(passwordEncoder().encode("operator")).roles("OPERATOR").authorities("READ_ALL","READ_ONE","ADD");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/user/**").hasAnyAuthority("READ_ALL","READ_ONE","ADD")
                .antMatchers("/api/user/**").hasAnyAuthority("READ_ALL","READ_ONE","ADD","UP")
                .antMatchers("/api/user/**").hasAnyAuthority("READ_ALL","READ_ONE","ADD","UP","DELETE")
                //.antMatchers("/api/info/**").hasAnyAuthority("READ_ALL","READ_ONE","ADD")
                //.antMatchers("/api/info/**").hasAnyAuthority("READ_ALL","READ_ONE","ADD","UP")
                //.antMatchers("/api/info/**").hasAnyAuthority("READ_ALL","READ_ONE","ADD","UP","DELETE")
                .antMatchers("/api/currency/**").hasAnyAuthority("READ_ALL","READ_ONE","ADD")
                .antMatchers("/api/currency/**").hasAnyAuthority("READ_ALL","READ_ONE","ADD","UP")
                .antMatchers("/api/currency/**").hasAnyAuthority("READ_ALL","READ_ONE","ADD","UP","DELETE")
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
