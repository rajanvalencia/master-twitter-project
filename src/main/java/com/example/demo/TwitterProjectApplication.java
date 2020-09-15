package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.example.demo.scheduled.TwitterTasks;

import twitter4j.TwitterException;

@SpringBootApplication
@EnableScheduling
public class TwitterProjectApplication {

	@Autowired
	TwitterTasks twitterTasks;
	
	public static void main(String[] args) {
		SpringApplication.run(TwitterProjectApplication.class, args);
	}
	
	/*
	@Scheduled(fixedDelay = 5000)
	public void first() throws TwitterException {
		System.out.println("Running...");
		twitterTasks.getTweetRetweetedUsersRetweetedUsers();
	}
	*/
}
