package com.bugscript.internetinteract;

/**
 * Created by syamsundark on 28/11/17.
 */

public class Github {

    private String followerName;
    private String followerNumber;

    public Github(String followerName, String followerNumber) {
        this.followerName = followerName;
        this.followerNumber = followerNumber;
    }

    public String getName(){
        return followerName;
    }

    public String getNumber(){
        return followerNumber;
    }

}
