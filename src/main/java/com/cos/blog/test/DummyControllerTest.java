package com.cos.blog.test;

import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

//html파일이 아니라 data를 리턴해주는 controller = RestController
@RestController
public class DummyControllerTest {
	
	@Autowired	//의존성 주입(DI)
	private UserRepository userRepository;
	
	
	//{id} 주소로 파라미터를 전달 받을 수 있음.
	//http://localhost:8000/blog/dummy/user/3
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		
		// user/4를 찾으면 내가 베이터베이스에서 못찾아오게 되면 user가 null이 됨.
		// 그럼 return null 이 리턴이 되서 프로그램에 문제가 될 것이다.
		// Optional로 User객체를 감싸서 가져올테니 null인지 아닌지 판단해서 return 해줌
		//뒤에 .get을 하면 null이 없으니까 줘. .odrElseGet은 user가 없으면 객체 하나 만들어서 넣어줘. 라는 뜻.
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			@Override
			public IllegalArgumentException get() {
				// TODO Auto-generated method stub
				return new IllegalArgumentException("해당 유저는 없습니다. id: "+id);
			}
		});
		//요청 : 웹브라우저
		// User 객체 = 자바 오브젝트 
		// 자바 오브젝트 ->변환 -> json(웹브라우저가 이해할 수 있는 데이터)
		//스프링부트 = MessageConverter라는 애가 응답시에 자동 작동
		//만약에 자바 오브젝트를 리턴하게 되면 MessageConverter가 Jackson 라이브러리를 호출해섯
		// user 오브젝트를 json으로 변환해서 브라우저에게 던져줍니다.
		return user;	
	}
	// 람다식 User user = userRepository.findById(id).orElseThrow(()->{ return new IllegalArgumentException("오류")});
	
	
	//http://localhost:8000/blog/dummy/join (요청)
	//http의 body에 username, password, email 데이터를 가지고 (요청)
	@PostMapping("/dummy/join")
	public String join(User user) {	//key=value (약속된 규칙)
		System.out.println("id: "+user.getId());
		System.out.println("username: "+user.getPassword());
		System.out.println("password: "+user.getPassword());
		System.out.println("email: "+user.getEmail());
		System.out.println("role: "+user.getRole());
		System.out.println("createDate: "+user.getCreateDate());
		
		user.setRole(RoleType.USER);
		userRepository.save(user);
		return "회원가입이 완료되었습니다.";
	}
}
