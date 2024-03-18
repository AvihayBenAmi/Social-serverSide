
package com.ashcollege.responses;
import com.ashcollege.entities.User;

import java.util.List;

public class GetAllPostsResponse extends BasicResponse  {
   private List<String> allPosts;

    public GetAllPostsResponse(boolean success, Integer errorCode, List<String> allPosts) {
        super(success,errorCode);
        this.allPosts = allPosts;
    }

    public List<String> getAllPosts() {
        return allPosts;
    }

    public void setAllPosts(List<String> allPosts) {
        this.allPosts = allPosts;
    }
}
