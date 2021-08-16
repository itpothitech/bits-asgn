package com.bits.asgn.userpostservice;

import java.util.List;

import com.bits.asgn.userpostservice.dto.Post;

public class UserPost {

    private Integer userId;   
    private String userName;
    private String userEmail;
    private List<Post> posts = null;

    public UserPost(Integer userId, String userName, String userEmail, List<Post> posts) {
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.posts = posts;
    }
    @Override
    public String toString() {
        return "UserPost [posts=" + posts + ", userEmail=" + userEmail + ", userId=" + userId + ", userName=" + userName
                + "]";
    }


}
