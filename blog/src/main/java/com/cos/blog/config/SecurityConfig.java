package com.cos.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cos.blog.config.auth.PrincipalDetailService;


@SuppressWarnings("deprecation")
@Configuration	//빈 등록 : 스프링 컨테이너에서 객체를 관리할 수 있게 하는 것(IoC관리)
@EnableWebSecurity	//시큐리티 필터가 등록이 된다.
@EnableGlobalMethodSecurity(prePostEnabled = true)	// 특정 주소로 접근을 하면 권한 및 인증을 미리 체크하겠다는 뜻.
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired 
	private PrincipalDetailService principalDetailService;
	
	
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {		
		return super.authenticationManagerBean();
	}

	@Bean	// 함수가 리턴하는 값을 스프링이 관리. 필요할 때 마다 사용하면 됨. IoC가 되요
	public BCryptPasswordEncoder encodePWD() {
		return new BCryptPasswordEncoder();
	}
	
	// 시큐리티가 대신 로그인해주는데 password를 가로채기를 하는데
	// 해당 password가 뭘로 해쉬가 되어 회원가입이 되었는지 알아야
	// 같은 해쉬로 암호화해서 DB에 있는 해쉬랑 비교할 수 있다.
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(principalDetailService).passwordEncoder(encodePWD());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()	// csrf 토큰 비활성화 (테스트시 걸어두는 게 좋음)
			.authorizeRequests()	//request가 들어올때
				.antMatchers("/", "/auth/**" ,"/js/**" , "/css/**" , "/image/**")	//auth쪽으로 들어오는건 다 허락해준다.
				.permitAll()
				.anyRequest()	//이게 아닌 다른 모든 요청은
				.authenticated()	//인증이 되야함
			.and()
				.formLogin()
				.loginPage("/auth/loginForm")	//인증이 필요한 모든 페이지는 로그인 폼으로 온다.
				.loginProcessingUrl("/auth/loginProc")	// 로그인 버튼을 누르면, 스프링 시큐리티가 해당 로그인을 가로챈다.		
				.defaultSuccessUrl("/");	//완료가 되면 가는 url
	}
}
