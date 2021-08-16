package com.bits.asgn.userfeedservice;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public final class UserFeedService {
    private static final Logger logger = LoggerFactory.getLogger(UserFeedService.class);
    private String URI_USERS_ID = "http://localhost:8082/api/follower/";

    @Autowired
    RestTemplate restTemplate;

    public void buildUserFeedForFollowers(String message) {
        logger.info("Pre-computing user feed for followers");
        // Get followers
        JsonObject newPost = new Gson().fromJson(message, JsonObject.class);
        String followeeUserId = "";
        if (newPost.has("userId")) {
            try {
                followeeUserId = newPost.get("userId").toString();
            } catch (NumberFormatException e) {
                logger.error(e.getMessage());
            }
            URI_USERS_ID = URI_USERS_ID.concat(followeeUserId);
            String followerInfo = restTemplate.getForObject(URI_USERS_ID, String.class);
            logger.info("found follower info:" + followerInfo);

            JsonObject followerObj = new Gson().fromJson(followerInfo, JsonObject.class);
            JsonArray followers = followerObj.getAsJsonArray("followers");
            for (int i = 0; i < followers.size(); i++) {
                JsonObject follower = followers.get(i).getAsJsonObject();
                JsonObject newFollwerFeed = buildUserFeed(message, follower);
                logger.info("New user feed generated for follower: [" + follower.get("userName").getAsString()
                        + "] ==>\n" + newFollwerFeed.toString());
            }

        }
    }

    private JsonObject buildUserFeed(String message, JsonObject follower) {
        JsonArray latestPosts = new JsonArray();
        JsonObject feed = new JsonObject();

        feed.addProperty("userId", follower.get("userId").getAsInt());
        feed.addProperty("userName", follower.get("userName").getAsString());
        feed.addProperty("userEmail", follower.get("userEmail").getAsString());
        JsonObject post = new Gson().fromJson(message, JsonObject.class);
        latestPosts.add(post);
        feed.add("latestPosts", latestPosts);

        return feed;
    }

}
