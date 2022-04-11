package com.sda.transporeon.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;

import static org.springframework.security.core.userdetails.User.UserBuilder;

/*Equivalent to applicationContext.xml*/

@Configuration /*1st thing*/
@EnableWebSecurity /*2nd thing*/
public class AppConfig extends WebSecurityConfigurerAdapter /*third thing*/ {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        UserBuilder users = User.withDefaultPasswordEncoder();

        auth.inMemoryAuthentication()
                .withUser(users.username("admin").password("123").roles("CEO"))
                .withUser(users.username("guest").password("").roles("GUEST"))
                .withUser(users.username("employee").password("12345").roles("EMPLOYEE"))
                .withUser(users.username("sdaadmin").password("123456789").roles("SDAADMIN"));


    }

    //This guy configures paths for: Login form, logout, error page, etc.
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/myloginpage") //this is URL to custom login page. Spring Security says: OK, this will be the URL where I will redirect every unauthenticated HTTP request!
                .loginProcessingUrl("/process") //Spring Security tells you: Do not you worry, you don't have to implement anything! I will take care of this processing! just make sure, you submit login data to this URL!
                .permitAll();

        /*
        as a little homework: in order to add Logout functionality
        * after permitAll(), you just have to append: .and().logout().permitAll()
        * */


    }
}
