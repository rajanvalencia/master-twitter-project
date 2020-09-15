package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Tweet;
import com.example.demo.model.TwitterUser;
import com.example.demo.repository.TweetRepository;
import com.example.demo.repository.TwitterUserRepository;

@RestController
public class ViewController {
	
	@Autowired
	TwitterUserRepository twitterUserRepository;
	
	@Autowired
	TweetRepository tweetRepository;
	
	@GetMapping("/users")
	public ModelAndView users(Model model) {
		model.addAttribute("users", twitterUserRepository.findAll());
		return new ModelAndView("users");
	}
	
	@GetMapping("/users/{userName}")
	public ModelAndView user(Model model, @PathVariable("userName") String userName) {
		TwitterUser user = twitterUserRepository.findByScreenName(userName)
				.orElseThrow(() -> new ResourceNotFoundException("Twitter user", "screen name", userName));
		model.addAttribute("user", user);
		return new ModelAndView("user");
	}
	
	@GetMapping("/users/{userName}/tweets")
	public ModelAndView userTweets(Model model, @PathVariable("userName") String userName) {
		/*
		List<Tweet> tweets = tweetRepository.findByTwitterUser_ScreenName(userName);
		model.addAttribute("tweets", tweets);
		*/
		return new ModelAndView("tweets");
	}
	
	@GetMapping("/tweets")
	public ModelAndView tweets(Model model) {
		model.addAttribute("tweets", tweetRepository.findAll());
		return new ModelAndView("tweets");
	}
	
	@GetMapping("/tweets/{tweetId}/retweetedUsers")
	public ModelAndView tweetRetweetedUsers(Model model, @PathVariable("tweetId") Long tweetId) {
		List<TwitterUser> users = twitterUserRepository.findByRetweets_Id(tweetId);
		model.addAttribute("users", users);
		return new ModelAndView("users");
	}

}
