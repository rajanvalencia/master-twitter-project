package com.example.demo.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tweets")
public class Tweet {
	
	@Id
	private Long id;

	@Column(columnDefinition = "TEXT")
	private String content;
	
	private Integer retweetCount;
	
	private Integer favoriteCount;
	
	private Date createdAt;
	
	private Boolean isTracking = false;
	
	@JsonIgnore
	@ManyToOne
	private TwitterUser twitterUser;
	
	@JsonIgnore
	@OneToMany(mappedBy = "tweet", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<TwitterUserRetweets> retweetedUsers;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getRetweetCount() {
		return retweetCount;
	}

	public void setRetweetCount(Integer retweetCount) {
		this.retweetCount = retweetCount;
	}

	public Integer getFavoriteCount() {
		return favoriteCount;
	}

	public void setFavoriteCount(Integer favoriteCount) {
		this.favoriteCount = favoriteCount;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Boolean getIsTracking() {
		return isTracking;
	}

	public void setIsTracking(Boolean isTracking) {
		this.isTracking = isTracking;
	}

	public TwitterUser getTwitterUser() {
		return twitterUser;
	}

	public void setTwitterUser(TwitterUser twitterUser) {
		this.twitterUser = twitterUser;
	}

	public List<TwitterUserRetweets> getRetweetedUsers() {
		return retweetedUsers;
	}

	public void setRetweetedUsers(List<TwitterUserRetweets> retweetedUsers) {
		this.retweetedUsers = retweetedUsers;
	}

}
