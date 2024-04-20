package com.whkj.spikes.bootstrap;

import com.whkj.spikes.bo.HelloMessageGenerator;
import com.whkj.spikes.bo.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;

@Configuration
public class WebScopeConfig {

	@Bean
	@Scope("singleton")
	public Person personSingleton() {
		return new Person();
	}

	@Bean
	@Scope("prototype")
	public Person personPrototype() {
		return new Person();
	}

	@Bean
	//or  @RequestScope
	@Scope(value = WebApplicationContext.SCOPE_REQUEST,proxyMode = ScopedProxyMode.TARGET_CLASS)
	public HelloMessageGenerator requestScopedBean() {
		System.out.println("A new HelloMessageGenerator instance created for current request (requestScopedBean)");
		return new HelloMessageGenerator();
	}

	@Bean
	// or @SessionScope
	@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
	public HelloMessageGenerator sessionScopedBean() {
		System.out.println("A new HelloMessageGenerator instance created for current request (sessionScopedBean)");
		return new HelloMessageGenerator();
	}

	@Bean
	// or @ApplicationScope
	@Scope(value = WebApplicationContext.SCOPE_APPLICATION, proxyMode = ScopedProxyMode.TARGET_CLASS)
	public HelloMessageGenerator applicationScopedBean() {
		System.out.println("A new HelloMessageGenerator instance created for current request (applicationScopedBean)");
		return new HelloMessageGenerator();
	}


}
