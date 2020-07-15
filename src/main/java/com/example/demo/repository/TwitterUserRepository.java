package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.TwitterUser;

public interface TwitterUserRepository extends JpaRepository<TwitterUser, Long>{
	
	TwitterUser findByScreenName(String userName);

}
