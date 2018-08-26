package be.vdab.proefpakket.security;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	private static final String ADMINISTRATOR = "administrator";
	
	@Bean
	JdbcDaoImpl jdbcDaoImpl(DataSource dataSource) {
		JdbcDaoImpl impl = new JdbcDaoImpl();
		impl.setDataSource(dataSource);
		return impl;
	};
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
		.mvcMatchers("/images/**")
		.mvcMatchers("/css/**")
		.mvcMatchers("/scripts/**");
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.formLogin().loginPage("/login")
				.and().authorizeRequests()
				.mvcMatchers("/brouwer/*/ondernemingsnummer").hasAuthority(ADMINISTRATOR);
	}
}
