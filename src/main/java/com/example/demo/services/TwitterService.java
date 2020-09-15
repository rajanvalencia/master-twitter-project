package com.example.demo.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Tweet;
import com.example.demo.model.TwitterUser;
import com.example.demo.repository.TweetRepository;
import com.example.demo.repository.TwitterUserRepository;

import twitter4j.IDs;
import twitter4j.Paging;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.User;
import twitter4j.api.HelpResources;
import twitter4j.api.TweetsResources;
import twitter4j.api.UsersResources;

@Service
public class TwitterService {
	
	@Autowired
	Twitter twitter;
	
	@Autowired
	TwitterUserRepository twitterUserRepository;
	
	@Autowired
	TweetRepository tweetRepository;
	
    @SuppressWarnings("rawtypes")
	public void getUserTimeLine(String userName, Boolean isTracking) {
		try {
			
			int firstPage = 2;// 探索開始ページ
			int tweetCount = 1;// ツイート番号
			Map helpmap = new HashMap();

			ResponseList<Status> statuses = twitter.getUserTimeline(userName, new Paging(firstPage));// 指定ユーザのタイムライン表示されるtweetIDを取得
			TweetsResources tweets = twitter.tweets();// TweetsResourceオブジェクトを作成
			UsersResources users = twitter.users();// UsersResourcesオブジェクトを作成
			HelpResources helps = twitter.help();// HelpResourcesオブジェクトを作成
			int i = 0;

			// 指定ユーザの Tweet を分析
			for (Status status : statuses) {
				
				TwitterUser user = new TwitterUser();
			    user.setCreatedAt(status.getUser().getCreatedAt());
			    user.setDescription(status.getUser().getDescription());
			    user.setFollow(status.getUser().getFriendsCount());
			    user.setFollower(status.getUser().getFollowersCount());
			    user.setId(status.getUser().getId());
			    user.setIsVerified(status.getUser().isVerified());
			    user.setLocation(status.getUser().getLocation());
			    user.setName(status.getUser().getName());
			    user.setProfileImageUrl(status.getUser().getProfileImageURL());
			    user.setScreenName(status.getUser().getScreenName());
			    user.setUrl(status.getUser().getURL());
			    user = twitterUserRepository.save(user);
			    
			    Tweet tweet = new Tweet();
				tweet.setId(status.getId());
				tweet.setIsTracking(isTracking);
				tweet.setCreatedAt(status.getCreatedAt());
				tweet.setContent(status.getText());
				tweet.setRetweetCount(status.getRetweetCount());
				tweet.setFavoriteCount(status.getFavoriteCount());
				tweet.setTwitterUser(user);
			    tweetRepository.save(tweet);
				System.out.println("RetweetCount: " + status.getRetweetCount());
				// リツイートされたことのある Tweet のみを分析
				
				List<TwitterUser> retweetedUsers = new ArrayList<>();
				
				if (status.getRetweetCount() != 0 && !status.isRetweet()) {
					IDs retweeters = tweets.getRetweeterIds(status.getId(), -1);// リツイートユーザのIDを取得(1)
					long[] retweetIDs = retweeters.getIDs();// リツイートユーザのIDを取得（2）

					ResponseList<User> retweet_users = users.lookupUsers(retweetIDs);// リツイートユーザの情報を取得
					System.out.println("(" + tweetCount + ")@" + status.getUser().getName() + ", tweet: " + status.getText());

					// リツイートユーザ情報表示
					System.out.println("retweet_user_info: ");
					for (User retweet_user : retweet_users) {
						//System.out.println("@" + retweet_user.getName() + ", ID: " + retweet_user.getId());

						TwitterUser retweetedUser = new TwitterUser();
						retweetedUser.setCreatedAt(retweet_user.getCreatedAt());
						retweetedUser.setDescription(retweet_user.getDescription());
						retweetedUser.setFollow(retweet_user.getFriendsCount());
						retweetedUser.setFollower(retweet_user.getFollowersCount());
						retweetedUser.setId(retweet_user.getId());
						retweetedUser.setIsVerified(retweet_user.isVerified());
						retweetedUser.setLocation(retweet_user.getLocation());
						retweetedUser.setName(retweet_user.getName());
						retweetedUser.setProfileImageUrl(retweet_user.get400x400ProfileImageURLHttps());
						retweetedUser.setScreenName(retweet_user.getScreenName());
						retweetedUser.setUrl(retweet_user.getURL());
						retweetedUser = twitterUserRepository.save(retweetedUser);
						retweetedUsers.add(retweetedUser);
					}
					//tweet.setRetweetedUsers(retweetedUsers);
					tweetRepository.save(tweet);
					
					i++;
				}
				if (i == 1)
					break;
				helpmap = helps.getRateLimitStatus();
			}

		} catch (TwitterException e) {// エラー処理
			e.printStackTrace();
			System.out.println("失敗");

		}
	}    
    
   public void searchTweetRetweetedUsersRetweetedUsers() {
	   
   }
}
