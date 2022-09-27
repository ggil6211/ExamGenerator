package edu.deakin.sit218.examgeneration.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

@Configuration
@EnableWebSecurity
public class ExamGenSpringSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private DataSource securityDataSource;
	protected static Logger logger =LogManager.getLogger(ExamGenSpringSecurityConfig.class);
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//use jdbc authentication
		auth.jdbcAuthentication().dataSource(securityDataSource);

	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
			.antMatchers("/examgen/**").hasAnyRole("STUDENT", "LECTURER")
			.antMatchers("/question-answer/**").hasRole("LECTURER")
			.antMatchers("/admin/**").hasRole("ADMIN")
			.anyRequest().authenticated()
			.and()
			.formLogin()
				.loginPage("/showMyLoginPage")
				.loginProcessingUrl("/authenticateTheUser")
				.permitAll()
				//logs the user logged in
				.successHandler(new AuthenticationSuccessHandler() {
				    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
				            Authentication authentication) throws IOException, ServletException {
				        // run custom logics upon successful login
				    	logger.info("---The USER -- " + authentication.getName() 
				    	+ " HAS_LOGGED_IN" + "-ROLE-" + authentication.getAuthorities());	
				        response.sendRedirect(request.getContextPath());
				    }
				})
			.and()
				.logout().clearAuthentication(true).permitAll().deleteCookies()
				//logs the user logged out
				.logoutSuccessHandler(new LogoutSuccessHandler() {
	                public void onLogoutSuccess(HttpServletRequest request,
	                            HttpServletResponse response, Authentication authentication)
	                        throws IOException, ServletException {
	                	logger.info("--- The USER -- " +authentication.getName() + " HAS_LOGOUT");
	                    response.sendRedirect(request.getContextPath());
	                }
	            })
			.and()
				.exceptionHandling()
				//logs the user has been denied access to resource
				.accessDeniedHandler(new AccessDeniedHandler() {
					@Override
					public void handle(HttpServletRequest request, HttpServletResponse response,
							AccessDeniedException accessDeniedException) throws IOException, ServletException {
						Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
						logger.error("---The USER - "+ authentication.getName() + " - "  
						+accessDeniedException.getMessage()+ " TO " +request.getRequestURI() );
						response.sendRedirect(request.getContextPath() +"/access-denied");
					}	
					});
		http.sessionManagement()
		.maximumSessions(1);
		
	}

        
}
