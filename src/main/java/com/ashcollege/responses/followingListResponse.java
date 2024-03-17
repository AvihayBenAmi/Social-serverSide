
package com.ashcollege.responses;

import com.ashcollege.entities.User;

import java.util.List;

public class followingListResponse extends BasicResponse  { ;
   private List<User> followingList;


//    public UserFoundSearchResponse() {
//
//    }
//
//    public UserFoundSearchResponse(List<User> found) {
//        this.found = found;
//    }

    public followingListResponse(boolean success, Integer errorCode, List<User> found) {
        super(success,errorCode);
        this.followingList = followingList;
    }

//    public List<User> getFollowingList() {
//        return followingList;
//    }
//
//    public void setFollowingList(List<User> followingList) {
//        this.followingList = followingList;
//    }


}
