package com.bits.asgn.userfollowservice;

import java.util.List;

import com.bits.asgn.userfollowservice.dto.Follower;
import com.bits.asgn.userfollowservice.dto.UserFollowerReq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("api/follower")
public class UserFollowerController {
    private static final Logger logger = LoggerFactory.getLogger(UserFollowerController.class.getName());

    @Autowired
    protected UserFollowerRepository userFollowerRepository;

    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public UserFollowerReq byUserId(@PathVariable Integer userId) {
        logger.info("user-follower-service byUserId() invoked: " + userId);
        return userFollowerRepository.findByUserId(userId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public boolean createFollower(@RequestBody UserFollowerReq userFollowerReq) {
        logger.info("user-follower-service createFollower() invoked: " +userFollowerReq.toString());
        UserFollowerReq existing = userFollowerRepository.findByUserId(userFollowerReq.getUserId());
        if (existing != null) {
            logger.info("user-follower-service: found an existing record");
            List<Follower> existingFollowers = existing.getFollowers();
            List<Follower> newFollower = userFollowerReq.getFollowers();

            for (int i = 0; i < newFollower.size(); i++) {
                existingFollowers.add(newFollower.get(i));
            }

            UserFollower userFollower = new UserFollower(userFollowerReq.getUserId(), userFollowerReq.getUserName(),
                    userFollowerReq.getUserEmail(), existingFollowers);

            userFollowerRepository.save(userFollower);

        } else {
            logger.info("user-follower-service: couldn't find any existing record. going to create one");
            UserFollower userFollower = new UserFollower(userFollowerReq.getUserId(), userFollowerReq.getUserName(),
                    userFollowerReq.getUserEmail(), userFollowerReq.getFollowers());

            userFollowerRepository.save(userFollower);
        }
        return true;
    }

}
