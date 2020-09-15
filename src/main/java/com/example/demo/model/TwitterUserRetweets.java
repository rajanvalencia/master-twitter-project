package com.example.demo.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "twitter_users_retweets")
public class TwitterUserRetweets {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;
	
	private Date createdAt;

	@ManyToOne
	private Tweet tweet;
	
	@ManyToOne
	private TwitterUser twitterUser;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Tweet getTweet() {
		return tweet;
	}

	public void setTweet(Tweet tweet) {
		this.tweet = tweet;
	}

	public TwitterUser getTwitterUser() {
		return twitterUser;
	}

	public void setTwitterUser(TwitterUser twitterUser) {
		this.twitterUser = twitterUser;
	}
	
}
