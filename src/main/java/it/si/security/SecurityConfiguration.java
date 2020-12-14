package it.si.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	@Autowired
	private UtentePrincipaleDetailService utentePrincipaleDetailService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}
	
	
	
	private final String[] adminPermission = {
			"/menu/sottomenu-budget",
			"/budget/**"
	};
	
	private final String[] authenticatedPermission = {
			"/menu/**",
			"/aliquota/**",
			"/area/**",
			"/fornitore/**",
			"/progetto/**",
			"/sottocategoria/**",
			"/preventivo/**",
			"/fattura/**"
	};
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
			.antMatchers("/menu/principale").permitAll()
			.antMatchers(adminPermission).hasRole("ADMIN")
			.antMatchers(authenticatedPermission).authenticated()
			.antMatchers("/ordine/gestione").hasAuthority("ORDINE_EDIT")
			.antMatchers("/ordine/ricerca").hasAuthority("ORDINE_LIST")
			.and()
//			.httpBasic();
			.formLogin()
			.loginPage("/login").permitAll()
			.loginProcessingUrl("/login")
			.failureUrl("/login?error")
			.usernameParameter("username")
			.passwordParameter("password")
			.and()
			.exceptionHandling()
			.accessDeniedPage("/login?forbidden")
			.and()
			.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login?logout")
			.invalidateHttpSession(true)
			.deleteCookies("JSESSIONID","remember-me")
			.and()
			.rememberMe().tokenValiditySeconds(1800).key("remember-me")
	        .userDetailsService(this.utentePrincipaleDetailService);
//			.and()
//			.csrf().disable();
			;
			
	}
	
	
	
	
	@Bean
	DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		daoAuthenticationProvider.setUserDetailsService(this.utentePrincipaleDetailService);
		return daoAuthenticationProvider;
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
