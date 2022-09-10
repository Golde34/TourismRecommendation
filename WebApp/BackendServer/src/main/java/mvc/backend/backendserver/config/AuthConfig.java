package mvc.backend.backendserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true
)
public class AuthConfig extends WebSecurityConfigurerAdapter {


    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    // Authentication step
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);

    }

    // Authorization step

//    http.authorizeRequests()
//            .antMatchers("/user/**").hasRole("USER")
//                .antMatchers("/admin/**").hasRole("ADMIN")
//                .antMatchers("/**").permitAll()
//                .and()
//                .formLogin();

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // disable Cross Site Request Forgery attack
        http.csrf().disable()
                // with this path - no need to authenticate
                .authorizeRequests()
                .antMatchers("/**").permitAll()
                // authenticate all others request
                .anyRequest().authenticated()
                // if exception occur => redirect to denied page
                .and().exceptionHandling().accessDeniedPage("/403")
                // to tell spring disable session
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // add filter (request need validated jwt to pass over the security)

    }
}