package ca.gc.collectionscanada.gcwa;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Value("${gcwa.networkMask}")
    private String gcwaNetworkMask;
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/admin/**")
                .access("isAuthenticated() and (hasIpAddress('0:0:0:0:0:0:0:1') or hasIpAddress('127.0.0.1') or hasIpAddress('" + gcwaNetworkMask + "'))")
                .and()
            .formLogin()
                .loginPage("/admin/login")
                .permitAll()
                .and()
            .logout()
                .logoutUrl("/admin/logout")
                .permitAll();

        http
            .headers().frameOptions().sameOrigin().httpStrictTransportSecurity().disable();
    }
}
