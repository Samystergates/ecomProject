package com.project.ecom1.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.project.ecom1backend.config.HibernateConfig;

@Configuration
@EnableWebSecurity
@ComponentScan("com.project.ecom1backend")
public class WebSecurity extends WebSecurityConfigurerAdapter {

	@Autowired
	HibernateConfig ds = new HibernateConfig();

	@Bean
	public BCryptPasswordEncoder passwordEnc() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers("/manage/**").access("hasAuthority('ADMIN')").antMatchers("/cart/**")
				.access("hasAuthority('USER')").antMatchers("/**").permitAll().antMatchers("/resources/**").permitAll()
				.anyRequest().authenticated().and().formLogin().loginPage("/login").permitAll().and()
				.exceptionHandling().accessDeniedPage("/access-denied");

	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

		auth.jdbcAuthentication().dataSource(ds.getDataSource()).passwordEncoder(passwordEnc())
				.authoritiesByUsernameQuery("select email, role from user_detail where email = ?")
				.usersByUsernameQuery("select email, password, enabled from user_detail where email = ?");
	}

}