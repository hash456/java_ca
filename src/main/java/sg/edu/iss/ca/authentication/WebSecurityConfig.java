package sg.edu.iss.ca.authentication;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter 
{
	@Autowired
	private DataSource dataSource;
		
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http.authorizeRequests()
			.antMatchers("/product/add","/product/edit/**","/product/delete/**").hasRole("ADMIN")
			.antMatchers("/brand/add","/brand/edit/**","/brand/delete/**").hasRole("ADMIN")
			.antMatchers("/supplier/add","/supplier/edit/**","/supplier/delete/**").hasRole("ADMIN")
			.antMatchers("/staff/**").hasRole("ADMIN")
			.antMatchers("/inventory/add","/inventory/edit/**","/inventory/delete/**").hasRole("ADMIN")
			.anyRequest().authenticated()
			.and()
			.formLogin().permitAll()
			.and()
			.logout().permitAll()
			.and()
			.exceptionHandling().accessDeniedPage("/403")
			;
	}
	
	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().passwordEncoder(new BCryptPasswordEncoder())
            .dataSource(dataSource)
            .usersByUsernameQuery("select username, password, enabled from staff where username=?")
            .authoritiesByUsernameQuery("select username, role from staff where username=?")
            ;
	}
}
