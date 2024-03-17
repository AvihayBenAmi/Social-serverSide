
package com.ashcollege.responses;

import com.ashcollege.entities.User;

import java.util.List;

public class UserFoundSearchResponse extends BasicResponse  { ;
   private List<User> found;


//    public UserFoundSearchResponse() {
//
//    }
//
//    public UserFoundSearchResponse(List<User> found) {
//        this.found = found;
//    }

    public UserFoundSearchResponse(boolean success,Integer errorCode,List<User> found) {
        super(success,errorCode);
        this.found = found;
    }

    public List<User> getFound() {
        return found;
    }

    public void setFound(List<User> found) {
        this.found = found;
    }

//    public List<User> isFound() {
//        return found;
//    }
//
//    public void setFound(List<User> found) {
//        this.found = found;
//    }
}
