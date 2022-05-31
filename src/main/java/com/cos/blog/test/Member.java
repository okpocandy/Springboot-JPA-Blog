package com.cos.blog.test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



//@Getter
//@Setter
//@Data로 getter setter 둘다 가능.
//final 붙은 애들에 대한 생성자를 만들어줌. @RequiredArgsConstructor
@Data
//@AllArgsConstructor // 모두에 대한 생성자를 만들고 싶으면 
@NoArgsConstructor//빈 생성자
public class Member {
	private  int id;		//final 쓰면은 불변성 유지, 데이터베이스에서 가져온 데이터는 변경할 일 없을경우
	private String username;
	private String password;
	private String email;
	
	//@Builder 를 사용하게 되면 Member.builder().username().password()......build();이런식으로 접근이 가능하다. 
	//예를들어 id를 입력하지 않는 생성자를 만들지 않아도 builder를 사용하여  id 를 넎지 않을 수 있다. 
	@Builder
	public Member(int id, String username, String password, String email) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
	}
}
