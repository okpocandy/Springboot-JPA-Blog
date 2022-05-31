package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder	//빌더 패턴
@Entity
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increament
	private int id;
	
	
	@Column(nullable = false, length = 100)
	private String title;	//제목
	
	
	@Lob	//대용량 데이터
	private String content;	//내용 , 섬머노트 라이브러리 사용 //우리가 쓴 글이 <html>태그가 섞여서 디자인 되어서 들어감 
	
	
	@ColumnDefault("0") //default 값을 0으로. int 기 때문에 " 만 있어도 됨.
	private int count; //조회수
	
	@ManyToOne //Many = Board, User = One 한명의 유저는 여러개의 게시물을 쓸 수 있다. 
								//OneToOne이면 한명의 유저가 한개의 게시물
	@JoinColumn(name="userId")	//userId로 저장됨. 
	private User user; //DB는 오브젝트를 저장할 수 없다. 그래서 FK를 사용하는데, 자바는 오브젝트를 저장할 수 있다.
	
	
	@CreationTimestamp //insert 시와 update시에 자동으로 현자 시간이 값으로 들어감
	private Timestamp createDate;
	
	
}
