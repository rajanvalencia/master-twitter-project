package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.TwitterUserRetweets;

@Repository
public interface TwitterUserRetweetsRepository extends JpaRepository<TwitterUserRetweets, Long> {

}
