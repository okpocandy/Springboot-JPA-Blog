package com.cos.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


//사용자가 요청->응답(HTML파일)
//@Controller

//사용자가 요청->응답(Data)
@RestController
public class HttpControllerTest {

	
	private static final String TAG="HttpControllerTest: ";
	
	@GetMapping("/http/lombok")
	public String lombokTest() {
		Member m = new Member(1, "ssar", "1234", "ssar@nate.com");
		System.out.println(TAG+"getter: "+m.getId());
		m.setId(5000);
		System.out.println(TAG+"setter: "+m.getId());
		return "lombok test 완료";
	}
	
	
	
	//인터넷 브라우저 요청은 무조건 get요청밖에 할 수 없다.
	//http://localhost:8080/http/get(select)
	//http://localhost:8080/http/get?id=1&username=ssar 같이 물음표 뒤에 querystring 을 통해서 보낼 수 있음
	//@RequestParam int id, @RequestParam String username을 통해서 한개한개씩도 가능하지만  Member(객체)로 한번에도 가능
	@GetMapping("/http/get")
	public String getTest(Member m) {
		//id=1&username=ssar&password=1234&email=ssar@nate.com 이 Member 오브젝트에 넣어주는 역할도해줌. MessageConverter (스프링 부트) 가 해준다.
		return "get 요청: "+m.getId()+","+m.getUsername()+","+m.getPassword()+","+m.getEmail();
	}
		
	//http://localhost:8080/http/post(insert)
	//회원 정보를 추가해서 회원가입을 해보자
	//post는 get 처럼 주소에 붙여서 보내는것이 아니다. x-www-form-urlencoded 방식을 써보자. form태그를 이용하는 것과 같다.
	//raw로 텍스트로 요청해보자.@RequestBody String text 로 받아야 한다. MIME 으로는 text.plain 이다
	//application.json 타입을 보내보자. json 은 키:값 (String("필요") : 뭐든지)
	//Member m 이라고 해놓으면 위에서 처럼 Member 오브젝트에 값을 맵핑 해줌. MessageConverter (스프링 부트) 가 해준다.
	@PostMapping("/http/post")
	public String postTest(@RequestBody Member m) {
		return "post 요청:  "+m.getId()+","+m.getUsername()+","+m.getPassword()+","+m.getEmail();
	}
	
	//http://localhost:8080/http/put(update)
	@PutMapping("/http/put")
	public String putTest(@RequestBody Member m) {
		return "put 요청" +m.getId()+","+m.getUsername()+","+m.getPassword()+","+m.getEmail();
	}
	
	//http://localhost:8080/http/delete(delete)
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		return "delete 요청";
	}
}
