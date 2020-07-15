package com.example.demo.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.TwitterUser;
import com.example.demo.repository.TweetRepository;
import com.example.demo.repository.TwitterUserRepository;
import com.example.demo.services.TwitterService;
import com.sun.el.stream.Optional;

@RestController
public class IndexController {
	
	@Autowired
	TwitterService twitterService;
	
	@Autowired 
	TwitterUserRepository twitterUserRepository;
	
	@Autowired 
	TweetRepository tweetRepository;
	
	@GetMapping("/")
	public ResponseEntity<TwitterUser> getTweets() {
		
		TwitterUser twitterUser = twitterUserRepository.findById(25073877L)
				.orElseThrow(() -> new ResourceNotFoundException("Twitter user", "id", 25073877L));
		
		return ResponseEntity.ok(twitterUser);
	}
	
	@GetMapping("/search/{userName}")
	public void searchTweet(@PathVariable("userName") String userName, HttpServletResponse response) throws IOException {
		twitterService.getUserTimeLine("@" + userName);
		response.sendRedirect("/");
	}
	
}
