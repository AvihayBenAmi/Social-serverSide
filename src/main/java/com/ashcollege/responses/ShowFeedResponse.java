
package com.ashcollege.responses;
import com.ashcollege.entities.Post;

import java.util.List;

public class ShowFeedResponse extends BasicResponse  {
   private List<Post> feed;

    public ShowFeedResponse(boolean success, Integer errorCode, List<Post> feed) {
        super(success,errorCode);
        this.feed = feed;
    }

    public List<Post> getFeed() {
        return feed;
    }

    public void setFeed(List<Post> feed) {
        this.feed = feed;
    }
}
