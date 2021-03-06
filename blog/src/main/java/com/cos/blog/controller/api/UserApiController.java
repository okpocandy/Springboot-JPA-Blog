package com.cos.blog.controller.api;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;

@RestController
public class UserApiController {
	
	@Autowired	//dependency injection을 받아서 사용
	private UserService userService;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	
	@PostMapping("/auth/joinProc")
	public ResponseDto<Integer> save(@RequestBody User user) {	//User로 받는것: username, password, email
		System.out.println("UserApiController : save 호출됨");
		userService.회원가입(user);	// 1이면 성공,아니면 실패
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);	//자바오브젝트를 JSON으로 변환해서 리턴
	}
	
	@PutMapping("/user")
	public  ResponseDto<Integer> update(@RequestBody User user){	
		userService.회원수정(user);
		// 여기서는 트랜잭션이 종료되기 때문에 DB에 값은 변경이 됐음.
		// 하지만 세션값은 변겨오디지 않은 상태이기 때문에 회원정보를 다시 누르면 적용이 되어있지 않음.
		//  logout을 하고 다시 login 을 해야 적용이 되있음. 그래서 직접 세션값을 변경해줘야 함.
		
		// 세션 등록
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);

		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
	
	
	
}

/* 전통적인 로그인 방식
@PostMapping("/api/user/login")
public ResponseDto<Integer> login(@RequestBody User user, HttpSession session){	//User로 받는것:username, password
	System.out.println("UserApiController : login호출됨");
	User principal = userService.로그인(user);	//principal(접근주체)
	
	if(principal != null) {
		session.setAttribute("principal", principal);		//세션이 만들어짐
	}
	return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
}
*/