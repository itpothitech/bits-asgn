package com.bits.asgn.userfollowservice;

import java.util.List;

import com.bits.asgn.userfollowservice.dto.Follower;

import org.springframework.data.annotation.Id;

public class UserFollower {
    
    @Id
    private Integer userId;
    
    private String userName;
    private String userEmail;
    private List<Follower> followers = null;
    public UserFollower(Integer userId, String userName, String userEmail, List<Follower> followers) {
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.followers = followers;
    }
    @Override
    public String toString() {
        return "UserFollower [followers=" + followers + ", userEmail=" + userEmail + ", userId=" + userId
                + ", userName=" + userName + "]";
    }

    
}
