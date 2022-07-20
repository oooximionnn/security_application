package com.zaurtregulov.spring.security.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;

import javax.sql.DataSource;

@EnableWebSecurity //конфигурация как класса ответственный за безопасность; пароли, роли
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public DataSource dataSource;
    @Override// вход на сайт по логину и паролю: аутентификация
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //теперь спринг конфигурация знает, что информацию о юзерах нужно брать из базы данных
        auth.jdbcAuthentication().dataSource(dataSource);
        //если хотим ложить логин и пароль в базу данных используем то, что написано выше

        // если храним здесь логин и пароль в коде - пишем как написано ниже

//        UserBuilder userBuilder = User.withDefaultPasswordEncoder();
//        auth.inMemoryAuthentication()
//                .withUser(userBuilder.username("zaur").password("zaur").roles("EMPLOYEE"))
//                .withUser(userBuilder.username("elena").password("elena").roles("HR"))
//                .withUser(userBuilder.username("ivan").password("ivan").roles("MANAGER", "HR"));
    }

    @Override // настройка авторизации - то есть допуск к страничкам по определенным ролям
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").hasAnyRole("EMPLOYEE", "HR", "MANAGER")
                .antMatchers("/hr_info").hasRole("HR")
                .antMatchers("/manager_info/**").hasRole("MANAGER")
                .and().formLogin().permitAll(); // форма логин и пароль будет запрашиваться у всех
    }
}
