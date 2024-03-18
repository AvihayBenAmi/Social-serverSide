
package com.ashcollege.responses;
import com.ashcollege.entities.Post;
import com.ashcollege.entities.User;

import java.util.List;

public class GetAllPostsResponse extends BasicResponse  {
   private List<Post> allPosts;

    public GetAllPostsResponse(boolean success, Integer errorCode, List<Post> allPosts) {
        super(success,errorCode);
        this.allPosts = allPosts;
    }

    public List<Post> getAllPosts() {
        return allPosts;
    }

    public void setAllPosts(List<Post> allPosts) {
        this.allPosts = allPosts;
    }
}
