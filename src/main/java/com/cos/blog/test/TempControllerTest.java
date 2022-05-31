package com.cos.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller  //@Controller는 메서드가 파일을 리턴함.
public class TempControllerTest {
	
	//http://localhost:8000/blog/temp/home
	@GetMapping("/temp/home")
	public String tempHome() {
		System.out.println("tempHome()");
		// 파일리턴 기본경로:/src/main/resources/static
		//리턴명 :/home.html 로 해야한다.
		//aplication.yml에서 경로 설정을 안했을 경우이다.
		return "/home.html";
	}
}
