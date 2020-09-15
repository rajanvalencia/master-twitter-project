package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.TwitterUser;

public interface TwitterUserRepository extends JpaRepository<TwitterUser, Long>{
	
	Optional<TwitterUser> findByScreenName(String userName);
	
	List<TwitterUser> findByRetweets_Id(Long id);

}
