package fr.opendevup.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
     
        auth.inMemoryAuthentication()
                .withUser("kamel").password("{noop}123").roles("USER")
                .and()
                .withUser("bigboss").password("{noop}1234").roles("ADMIN","USER");

    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http.formLogin();
    	http.authorizeRequests().antMatchers("/admin/consulterProduits","/admin/clients").hasRole("USER");
    	http.authorizeRequests().antMatchers("/admin/ajouterProduits","/admin/modifierProduit",
    			"/admin/deleteProduits","/admin/deleteClients","/admin/modifierClient")
    	.hasRole("ADMIN");
    	http.exceptionHandling().accessDeniedPage("/403");
    }
}