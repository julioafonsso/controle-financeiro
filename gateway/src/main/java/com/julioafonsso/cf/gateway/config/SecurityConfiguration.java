package com.julioafonsso.cf.gateway.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Configuration
@EnableResourceServer
public class SecurityConfiguration extends ResourceServerConfigurerAdapter {

	@Value("${security.oauth2.client.resource-ids}")
	private String resourceIds;

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) {
		resources.resourceId(resourceIds);
	}

	 @Override
     public void configure(HttpSecurity http) throws Exception {
     
         
         http
         .logout()
         .invalidateHttpSession(true)
         .clearAuthentication(true)
         .and().authorizeRequests()
         .antMatchers(HttpMethod.POST, "/auth/**").permitAll()
         .antMatchers("/teste").permitAll()
         .anyRequest().fullyAuthenticated();
     }
}