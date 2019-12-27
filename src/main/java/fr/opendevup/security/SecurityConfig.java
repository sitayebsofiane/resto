package fr.opendevup.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	DataSource dataSource;
	@Autowired
    protected void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
    	//utilisateur depuis le code java
        auth.inMemoryAuthentication()
                .withUser("kamel").password(passwordEncoder().encode("123")).roles("USER")
                .and()
                .withUser("bigboss").password(passwordEncoder().encode("1234" )).roles("ADMIN","USER");
        //utilisateur depuis la base de donnee

        auth.jdbcAuthentication().dataSource(dataSource)
        .usersByUsernameQuery(
         "select username as principal ,password as credentials, enabled from users where username=?")
        .authoritiesByUsernameQuery(
         "select username as principal, role as role from user_roles where username=?")
    	.passwordEncoder(new BCryptPasswordEncoder())
    	.rolePrefix("_ROLE");
    }
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http.formLogin().loginPage("/login");
    	http.authorizeRequests().antMatchers("/","/pages/*").permitAll();
    	http.authorizeRequests().antMatchers("/admin/consulterProduits","/admin/clients","/admin/commandes").hasRole("USER");
    	http.authorizeRequests().antMatchers("/admin/*").hasRole("ADMIN");
    	http.exceptionHandling().accessDeniedPage("/403");
    	
    }
    @Bean
    PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
    	
    }
	
}