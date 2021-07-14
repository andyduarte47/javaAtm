package com.company;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.security.MessageDigest;


public class User {
    String first_name;
    String last_name;
    String userID;
    String pin;
    Account amount;

    public User(String first_name, String last_name ,String userID,  String pin) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.userID = userID;
        this.pin = pin;
        this.amount = new Account(0);

    }
}

