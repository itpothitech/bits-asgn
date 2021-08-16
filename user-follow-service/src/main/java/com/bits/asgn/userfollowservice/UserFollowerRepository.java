package com.bits.asgn.userfollowservice;

import com.bits.asgn.userfollowservice.dto.UserFollowerReq;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserFollowerRepository extends MongoRepository<UserFollower, Integer> {
    public UserFollowerReq findByUserId(Integer userId);

    public UserFollowerReq findByUserEmailIgnoreCase(String userEmail);
}
