package com.bits.asgn.userpostservice;

import java.util.List;

import com.bits.asgn.userpostservice.dto.UserPostReq;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserPostRepository extends MongoRepository<UserPost, Integer> {
	public List<UserPostReq> findByUserId(Integer userId);

	public List<UserPostReq> findByUserEmailIgnoreCase(String userEmail);

}
