package com.example.demo.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "users")
public class TwitterUser {

	@Id
	private Long id;
	
	private Date createdAt;
	
	private Boolean isVerified;
	
	private String name;
	
	@Column(unique = true)
	private String screenName;
	
	private Integer follow;
	
	private Integer follower;
	
	@Column(columnDefinition = "TEXT")
	private String profileImageUrl;
	
	@Column(columnDefinition = "TEXT")
	private String description;
	
	private String location;
	
	@Column(columnDefinition = "TEXT")
	private String url;
	
	@JsonIgnore
	@OneToMany(mappedBy = "twitterUser", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Tweet> tweets;
	
	@JsonIgnore
	@OneToMany(mappedBy = "twitterUser", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<TwitterUserRetweets> retweets;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Boolean getIsVerified() {
		return isVerified;
	}

	public void setIsVerified(Boolean isVerified) {
		this.isVerified = isVerified;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	public Integer getFollow() {
		return follow;
	}

	public void setFollow(Integer follow) {
		this.follow = follow;
	}

	public Integer getFollower() {
		return follower;
	}

	public void setFollower(Integer follower) {
		this.follower = follower;
	}

	public String getProfileImageUrl() {
		return profileImageUrl;
	}

	public void setProfileImageUrl(String profileImageUrl) {
		this.profileImageUrl = profileImageUrl;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public List<Tweet> getTweets() {
		return tweets;
	}

	public void setTweets(List<Tweet> tweets) {
		this.tweets = tweets;
	}

	public List<TwitterUserRetweets> getRetweets() {
		return retweets;
	}

	public void setRetweets(List<TwitterUserRetweets> retweets) {
		this.retweets = retweets;
	}

}
