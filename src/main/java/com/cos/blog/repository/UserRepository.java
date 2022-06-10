package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cos.blog.model.User;

//DAO
// 자동으로 bean 등록이 된다.
//@Repository 생략 가능
public interface UserRepository extends JpaRepository<User, Integer>{	//User테이블을 관리하는 repo, PK는 Integer
	// JPA Naming 쿼리
	
	//이렇게도 가능함
		//@Query(value = "SELECT * FROM user WHERE username = ?1  AND password = ?2",nativeQuery=true)
		//User login(String username, String password);
	
	// SELECT * FROM user WHERE username = ?1  AND password = ?2; 로 동작을 하게 됨.
	User findByUsernameAndPassword(String username, String password);
	
	
}
