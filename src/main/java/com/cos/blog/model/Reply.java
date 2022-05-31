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
public class Reply {
	@Id	//Primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increament
	private int id;
	
	@Column(nullable = false, length = 200)
	private String content;
	
	@ManyToOne		//연관관계 여러개의 답변은 하나의 게시물에 존재 가능
	@JoinColumn(name="boardId")
	private Board board;	//어느게시물에 달린 댓글인가
	
	@ManyToOne
	@JoinColumn(name="userId")
	private User user;	//누가 달았는가
	
	@CreationTimestamp
	private Timestamp createDate;
	
}
