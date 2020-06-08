package com.splitwise.models.user;
import java.util.concurrent.atomic.AtomicInteger;

public class User {
    private String userId;
    private String userName;
    private String email;
    private String phoneNumber;
    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    public User(String userName, String email, String phoneNumber) {
        this.userId = "us00x"+atomicInteger.incrementAndGet();
        this.userName = userName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
