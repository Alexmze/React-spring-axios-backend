package com.amuniz.yt.sbreact.fullstackbackend.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException (Long id) {
        super("Cannot find user with id " + id);
    }
}
