package com.example.demo.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.TwitterUser;
import com.example.demo.repository.TweetRepository;
import com.example.demo.repository.TwitterUserRepository;
import com.example.demo.services.TwitterService;

@RestController
public class TwitterController {
	
	@Autowired
	TwitterService twitterService;
	
	@Autowired 
	TwitterUserRepository twitterUserRepository;
	
	@Autowired 
	TweetRepository tweetRepository;
	
	@GetMapping("/")
	public ResponseEntity<List<TwitterUser>> getAll(){
		return ResponseEntity.ok(twitterUserRepository.findAll());
	}
	
	@GetMapping("/views/{userName}")
	public ResponseEntity<TwitterUser> getTweets(@PathVariable("userName") String userName) {
		
		TwitterUser twitterUser = twitterUserRepository.findByScreenName(userName)
				.orElseThrow(() -> new ResourceNotFoundException("Twitter user", "screen name", userName));
		
		return ResponseEntity.ok(twitterUser);
	}
	
	@GetMapping("/search/{userName}")
	public void searchTweet(@PathVariable("userName") String userName, @RequestParam("isTracking") Optional<Boolean> isTracking, HttpServletResponse response) throws IOException {
		twitterService.getUserTimeLine("@" + userName, isTracking.orElse(false));
		response.sendRedirect("/views/" + userName);
	}
	
	
	@GetMapping("/tweets/{tweetId}/retweeted-users")
	public List<TwitterUser> searchTweetRetweetedUsers(@PathVariable("tweetId") Long tweetId) {
		return twitterUserRepository.findByRetweets_Id(tweetId);
	}
	
	
}
