package com.bits.asgn.userpostservice;

import java.util.List;
import java.util.logging.Logger;

import com.bits.asgn.userpostservice.dto.UserPostReq;
import com.google.gson.Gson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/post")
public class UserPostController {
    protected Logger logger = Logger.getLogger(UserPostController.class.getName());
    protected UserPostRepository userPostRepository;

    @Autowired
    public UserPostController(UserPostRepository userPostRepository) {
        this.userPostRepository = userPostRepository;

    }

    @Autowired
    ProducerService postProducer;

    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<UserPostReq> byUserId(@PathVariable Integer userId) {

        logger.info("user-post-service byUserId() invoked: " + userId);
        return userPostRepository.findByUserId(userId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public boolean createPost(@RequestBody UserPostReq userPostReq) {

        logger.info(userPostReq.toString());
        UserPost newPost = new UserPost(userPostReq.getUserId(), userPostReq.getUserName(), 
                    userPostReq.getUserEmail(), userPostReq.getPosts());
        userPostRepository.save(newPost);

        Gson gson = new Gson();
        String postMessage = gson.toJson(newPost);
        postProducer.sendMessage(postMessage);
        return true;

    }
}
