package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//여기서 id username 같은 이름을 바꿔서 저장하면 데이터 데이스에 이름도 바뀐다.
//ORM -> JAVA(다른언어) Object -> 테이블로 매핑해주는 기술
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder	//빌더 패턴
@Entity// User 클래스가  MySQL에 테이블이 생성이 된다. 
//@DynamicInsert	//insert할 때 null 인 필드 제외	//annotation 계속 붙이는건 좋진 않음
public class User {
	
	@Id //Primary Key
	@GeneratedValue(strategy = GenerationType.IDENTITY)	//넘버링 전략. 해당 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
	private int id;	// 시퀀스, auto-increment
	
	@Column(nullable = false, length = 30)	//null값이 될 수 없고, 길이 30이하.
	private String username;	//아이디
	
	@Column(nullable = false, length = 100)	//null값이 될 수 없고, 길이 100이하.  //왜 길게 하느냐? =>해쉬(비밀번호 암호화)할꺼라서
	private String password;	//비밀번호
	
	@Column(nullable = false, length = 50)	//null값이 될 수 없고, 길이 50이하.
	private String email;	//이메일
	
	
	@ColumnDefault(" 'user' ")	//ColumnDefault 에는 '''이 들어가야 한다. column의 default값을 지정.
	//DB는 RoleType이라는 게 없다.
	@Enumerated(EnumType.STRING)
	private RoleType role; 
	// Enum을 쓰는게 좋다. data 의 도메인을 만들어 줄 수 있다. // ADMIN, USER 중에 하나가 들어가게 해줄 수 있다.
	//성별이라면 남,여 초등학년 이라면 1~6같은 것
	
	@CreationTimestamp //시간 자동 입력
	private Timestamp createDate;
	  
}
