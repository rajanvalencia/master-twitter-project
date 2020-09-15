package com.example.demo.scheduled;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Tweet;
import com.example.demo.model.TwitterUser;
import com.example.demo.repository.TweetRepository;
import com.example.demo.repository.TwitterUserRepository;
import com.example.demo.services.TwitterService;

import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

@Service
public class TwitterTasks {

	@Autowired
	Twitter twitter;

	@Autowired
	TwitterService twitterService;

	@Autowired
	TwitterUserRepository twitterUserRepository;

	@Autowired
	TweetRepository tweetRepository;

	public void getTweetRetweetedUsersRetweetedUsers() throws TwitterException {

		List<Tweet> trackTweets = tweetRepository.findByIsTrackingTrue();
		for (Tweet trackTweet : trackTweets) {
			List<TwitterUser> retweetedUsers = twitterUserRepository.findByRetweets_Id(trackTweet.getId());
			for (TwitterUser retweetedUser : retweetedUsers) {
				ResponseList<Status> statuses = twitter.getUserTimeline(retweetedUser.getScreenName());
				for (Status status : statuses) {
					System.out.format("%s %d %s\n", retweetedUser.getScreenName(), status.getId(), status.getText());
					if (trackTweet.getId() == status.getId()) {
						Status retweetedStatus = twitter.showStatus(status.getCurrentUserRetweetId());
						System.out.println(retweetedStatus.getId() + " " + retweetedStatus.getText() + " "
								+ retweetedStatus.getCreatedAt() + " " + retweetedStatus.getRetweetCount());
					}
				}
			}
		}

	}
}
