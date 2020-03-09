package pl.siusta.why.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.siusta.why.service.UserDetailsServiceImpl;
import pl.siusta.why.repo.UserRepo;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailsServiceImpl userDetailsService;
    private UserRepo userRepo;

    public WebSecurityConfig(UserDetailsServiceImpl userDetailsService, UserRepo userRepo){
        this.userDetailsService=userDetailsService;
        this.userRepo=userRepo;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(
                        "/",
                        "/home",
                        "/register",
                        "/signup",
                        "/nikodem",
                        "/pasta",
                        "/viwus",
                        "/css/**",
                        "/img/**").permitAll()
                .antMatchers("/hello").authenticated()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").defaultSuccessUrl("/hello").permitAll()
                .and()
                .logout().permitAll();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


  /*  @EventListener(ApplicationReadyEvent.class)
    public void saveUser() {
        User user = new User("user1", passwordEncoder().encode("pass"));
        User user1 = new User("user2", passwordEncoder().encode("pass"));
        User user3 = new User("anon", passwordEncoder().encode("pass"));
        userRepo.save(user);
        userRepo.save(user1);
        userRepo.save(user3);
    }
*/
}
