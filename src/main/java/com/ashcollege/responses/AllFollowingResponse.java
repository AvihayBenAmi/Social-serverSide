
package com.ashcollege.responses;

import com.ashcollege.entities.User;

import java.util.List;

public class AllFollowingResponse extends BasicResponse  { ;
   private List<User> allFollowing;


//    public UserFoundSearchResponse() {
//
//    }
//
//    public UserFoundSearchResponse(List<User> found) {
//        this.found = found;
//    }

    public AllFollowingResponse(boolean success, Integer errorCode, List<User> allFollowing) {
        super(success,errorCode);
        this.allFollowing = allFollowing;
    }

    public List<User> getAllFollowing() {
        return allFollowing;
    }

    public void setAllFollowing(List<User> allFollowing) {
        this.allFollowing = allFollowing;
    }

//    public List<User> isFound() {
//        return found;
//    }
//
//    public void setFound(List<User> found) {
//        this.found = found;
//    }
}
