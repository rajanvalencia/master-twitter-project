package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

@Configuration
public class TwitterConfiguration {

	@Bean
	public Twitter configureTwitter() {
		Twitter twitter = new TwitterFactory().getInstance();
        String consumerKey = "KeD1RfDAAxVXSsE6LoxBOYytz";
        String consumerSecret = "AmbkugpF6EmE793jIlAiBVDHZP699Kz8FccKxnNwSQ1cSjNTMc";
        String accessToken = "3248636774-00fxucF4CurJmuW4SS5eeXQ1fHsjlQ3uNrDf11k";
        String accessTokenSecret = "WzPGZCHPcfBAqow4BlpqdXXJkGfkCIvh7leUfNZn2W21h";

        twitter.setOAuthConsumer(consumerKey, consumerSecret);
        twitter.setOAuthAccessToken(new AccessToken(accessToken,accessTokenSecret));
        return twitter;
	} 
}
